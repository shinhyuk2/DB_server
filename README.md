# DB_server
인하대학교 데이터베이스 프로젝트 백엔드
# SSG Landers Talk

## 프로젝트 소개
Baseball Mate는 야구 직관을 함께할 동행자를 찾고, 경기 관련 이야기를 피드에 공유하며, 매칭된 사용자 간 메시지를 주고받을 수 있는 서비스입니다.

본 레포지토리는 해당 서비스의 백엔드 서버를 위한 프로젝트이며, 사용자 관리, 피드 게시물, 해시태그, 댓글, 좋아요, 경기 일정, 직관 동행 매칭, 채팅 기능을 담당합니다.

---

## 주요 기능

### 1. 홈 피드
사용자가 야구 직관 및 관련 이야기를 자유롭게 공유할 수 있는 공간입니다.

* 직관 인증, 야구장 리뷰 등 실시간 텍스트 및 이미지 포스팅
* 해시태그 기반 게시물 필터링 및 검색
* 무한 뎁스(Depth) 확장이 가능한 대댓글(순환 관계 모델링 적용) 기능

### 2. 직관 동행 매칭
사용자가 특정 경기 일정을 선택하고, 매칭을 신청하여 직관 동행자를 찾을 수 있는 기능입니다.

* 구단 및 경기 일정 데이터 제공
* 사용자 정보(성별, 나이, MBTI) 기반 조건부 동행 매칭
* 다대다(N:M) 관계인 유저와 경기 일정을 `매칭_신청` 테이블로 분리하여 안정적인 매칭 트랜잭션 처리

### 3. 다이렉트 메시지

* 매칭이 성사된 사용자 간의 1:1 또는 그룹 채팅방 자동 생성
* 메시지 송수신 및 읽음 여부 상태 관리

---

## 백엔드 담당 범위

- 회원 정보 저장 및 관리
- 게시물, 댓글, 좋아요, 해시태그 관리
- 경기 및 구단 정보 관리
- 직관 동행 신청 및 매칭 처리
- 매칭 구성원 관리
- 채팅방 생성 및 참여자 관리
- 메시지 저장 및 조회
- REST API 제공 및 데이터베이스 연동

---

## 기술 스택

### Backend
- **Java 17**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **Hibernate**

### Database
- **PostgreSQL**

### Build / DevOps
- **Gradle**
- **GitHub**

### Tools
- **IntelliJ IDEA**
- **ERDCloud**
- **Figma**
- **Postman** 

---

## DB 연동 방식

본 프로젝트는 **Spring Boot + Spring Data JPA + PostgreSQL** 구조로 데이터베이스를 연동합니다.

- **Spring Data JPA**를 사용하여 엔티티와 데이터베이스 테이블을 매핑합니다.
- **Hibernate**를 JPA 구현체로 사용하여 ORM 기반으로 데이터를 관리합니다.
- 각 도메인별로 `Entity`, `Repository`, `Service`, `Controller` 계층을 분리하여 구현합니다.
- MySQL에 저장된 사용자, 게시물, 경기, 매칭, 채팅 데이터를 JPA Repository를 통해 조회/저장/수정합니다.
- 향후 필요 시 `@Query`, JPQL, Query Method를 활용하여 복잡한 조회 기능을 확장할 수 있습니다.

---

## 주요 기능별 도메인 설명

### 사용자 도메인
회원 가입 및 로그인 이후 서비스 전반에서 사용되는 기본 사용자 정보를 관리합니다.

### 피드 도메인
게시물, 댓글, 좋아요, 해시태그, 이미지 기능을 통해 사용자 간 야구 관련 소통을 지원합니다.

### 경기 및 매칭 도메인
구단 정보와 경기 일정 데이터를 기반으로 직관 동행 신청, 매칭 생성, 매칭 구성원 관리를 수행합니다.

### 채팅 도메인
매칭이 완료된 사용자들 사이에서 채팅방을 생성하고, 메시지를 송수신할 수 있도록 지원합니다.

---

## 주요 API 예시

### User
- `POST /users/signup` : 회원가입
- `POST /users/login` : 로그인
- `GET /users/{userId}` : 사용자 정보 조회

### Feed
- `GET /posts` : 게시물 목록 조회
- `POST /posts` : 게시물 작성
- `GET /posts/{postId}` : 게시물 상세 조회
- `POST /posts/{postId}/comments` : 댓글 작성
- `POST /posts/{postId}/likes` : 좋아요 등록

### Hashtag
- `POST /hashtags` : 해시태그 생성
- `GET /hashtags/{hashtagId}` : 해시태그 조회

### Game / Team
- `GET /teams` : 구단 목록 조회
- `GET /games` : 경기 일정 조회
- `GET /games/{gameId}` : 경기 상세 조회

### Match
- `POST /match-applications` : 매칭 신청
- `GET /match-applications/{userId}` : 내 신청 내역 조회
- `POST /matches` : 매칭 생성
- `GET /matches/{matchId}` : 매칭 상세 조회

### Chat
- `GET /chat-rooms` : 채팅방 목록 조회
- `POST /chat-rooms` : 채팅방 생성
- `GET /chat-rooms/{chatRoomId}/messages` : 채팅 메시지 조회
- `POST /chat-rooms/{chatRoomId}/messages` : 메시지 전송

---
## Commit Convention
| 타입 | 설명 |
|-|-|
| **feat** | 새로운 기능 추가 |
| **fix** | 버그 수정 |
| **docs** | 문서 수정 (README, 주석, 가이드 등) |
| **style** | 코드 포맷팅, 세미콜론 누락, 오타 수정 (로직 변경 없음) |
| **refactor** | 코드 리팩토링 (기능 변경 없이 구조만 개선) |
| **test** | 테스트 코드 추가/수정 |
| **chore** | 빌드 설정, 패키지 매니저 설정, 라이브러리 추가 (코드 변경 없음) |

