# DB_server
인하대학교 데이터베이스 프로젝트 백엔드
# Baseball Mate Backend

## 프로젝트 소개
Baseball Mate는 야구 직관을 함께할 동행자를 찾고, 경기 관련 이야기를 피드에 공유하며, 매칭된 사용자 간 메시지를 주고받을 수 있는 서비스입니다.

본 레포지토리는 해당 서비스의 백엔드 서버를 위한 프로젝트이며, 사용자 관리, 피드 게시물, 해시태그, 댓글, 좋아요, 경기 일정, 직관 동행 매칭, 채팅 기능을 담당합니다.

---

## 주요 기능

### 1. 홈 피드
사용자가 야구 직관 및 관련 이야기를 자유롭게 공유할 수 있는 공간입니다.

- 게시물 작성 및 조회
- 해시태그 등록 및 연결
- 댓글 작성 및 조회
- 좋아요 기능
- 게시물 이미지 첨부

### 2. 직관 동행 매칭
사용자가 특정 경기 일정을 선택하고, 매칭을 신청하여 직관 동행자를 찾을 수 있는 기능입니다.

- 구단 정보 조회
- 경기 일정 조회
- 경기별 매칭 신청
- 신청 상태 관리
- 경기 기준 매칭 생성
- 매칭 구성원 관리

### 3. 다이렉트 메시지
매칭된 사용자 간 자유롭게 소통할 수 있는 채팅 기능입니다.

- 채팅방 생성
- 채팅방 참여자 관리
- 메시지 전송 및 조회
- 시스템 메시지 지원
- 이미지 메시지 지원
- 읽음 여부 관리

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
- **MySQL**

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

본 프로젝트는 **Spring Boot + Spring Data JPA + MySQL** 구조로 데이터베이스를 연동합니다.

- **Spring Data JPA**를 사용하여 엔티티와 데이터베이스 테이블을 매핑합니다.
- **Hibernate**를 JPA 구현체로 사용하여 ORM 기반으로 데이터를 관리합니다.
- 각 도메인별로 `Entity`, `Repository`, `Service`, `Controller` 계층을 분리하여 구현합니다.
- MySQL에 저장된 사용자, 게시물, 경기, 매칭, 채팅 데이터를 JPA Repository를 통해 조회/저장/수정합니다.
- 향후 필요 시 `@Query`, JPQL, Query Method를 활용하여 복잡한 조회 기능을 확장할 수 있습니다.

### 계층 구조 예시
- **Controller**: 클라이언트 요청 처리
- **Service**: 비즈니스 로직 처리
- **Repository**: DB 접근
- **Entity**: 테이블 매핑 객체
- **DTO**: 요청/응답 데이터 전달 객체

---

## ERD 기반 주요 테이블

### 1. Users
사용자 정보를 저장하는 테이블입니다.

- `user_id`
- `email`
- `password`
- `nickname`
- `gender`
- `birth_year`
- `mbti`
- `favorite_team`
- `profile_image_url`
- `created_at`
- `updated_at`

### 2. team information
구단 정보를 저장하는 테이블입니다.

- `team_id`
- `team_name`
- `region`
- `stadium`
- `team_emblem`

### 3. games
경기 일정을 저장하는 테이블입니다.

- `game_id`
- `home_team_id`
- `away_team_id`
- `game_date`

### 4. match_applications
사용자의 직관 동행 신청 정보를 저장하는 테이블입니다.

- `application`
- `game_id`
- `user_id`
- `application_status`
- `applied_at`

### 5. matches
매칭 결과 정보를 저장하는 테이블입니다.

- `match_id`
- `game_id`
- `matched_at`
- `match_status`

### 6. match_member
매칭에 포함된 사용자 정보를 저장하는 테이블입니다.

- `member_id`
- `match_id`
- `user_id`

### 7. Posts
피드 게시물 정보를 저장하는 테이블입니다.

- `post_id`
- `user_id`
- `post_title`
- `content`
- `created_at`
- `updated_at`

### 8. Hashtags
해시태그 정보를 저장하는 테이블입니다.

- `hashtag_id`
- `tag_name`

### 9. post_hashtags
게시물과 해시태그의 다대다 관계를 저장하는 테이블입니다.

- `post_hashtag_id`
- `post_id`
- `hashtag_id`

### 10. Comment
게시물 댓글 정보를 저장하는 테이블입니다.

- `comment_id`
- `post_id`
- `post_user_id`
- `comment_users`
- `comment`
- `write_date`
- `delete_date`

### 11. Like
게시물 좋아요 정보를 저장하는 테이블입니다.

- `like_id`
- `post_id`
- `click_person_id`
- `create_date`
- `delete_date`

### 12. Image_comment
게시물 이미지 정보를 저장하는 테이블입니다.

- `post_image_id`
- `post_id`
- `post_user_id`
- `image`
- `write_date`
- `delete_date`

### 13. chat_rooms
채팅방 정보를 저장하는 테이블입니다.

- `chat_room_id`
- `match_id`
- `created_at`

### 14. chat_room_members
채팅방 참여자 정보를 저장하는 테이블입니다.

- `chat_room_member_id`
- `chat_room_id`
- `user_id`
- `joined_at`

### 15. messages
채팅 메시지를 저장하는 테이블입니다.

- `message_id`
- `chat_room_id`
- `sender_id`
- `message_type`
- `content`
- `image_url`
- `is_read`
- `sent_at`

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

## 프로젝트 구조

```bash
src
 ┣ main
 ┃ ┣ java
 ┃ ┃ ┗ com.example.baseballmate
 ┃ ┃   ┣ controller
 ┃ ┃   ┣ service
 ┃ ┃   ┣ repository
 ┃ ┃   ┣ domain
 ┃ ┃   ┣ dto
 ┃ ┃   ┗ config
 ┃ ┗ resources
 ┃   ┣ application.yml
 ┃   ┗ static
 ┗ test
