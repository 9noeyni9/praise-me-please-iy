# 스프링 심화 코드 개선 과제

- 코드 개선 개인 과제 기간 : 2024.03.07~
- Controller Advice 로 예외 공통화 처리하기
- Service 인터페이스와 구현체 분리하여 추상화 하기
- CustomException 정의
- Spring AOP 적용
- QueryDSL 을 사용하여 검색 기능 만들기
- Pageable 을 사용하여 페이징 및 정렬 기능 만들기
- _~~AWS EC2~~_ __리눅스 우분투__ 이용해 애플리케이션 .jar 파일 배포하기

---

- dev branch : 배포 안 한 버전
- release : 우분투 서버 배포 버전

---

# 개요

- 프로젝트 이름: 칭찬해주세요
- 프로젝트 설명: 모든 내용에 칭찬만 해야 하는 사이트입니다.
    - 주요 기능
        - 회원가입 및 로그인/로그아웃
        - 커뮤니티 기능(게시글, 댓글)
        - 관리자 페이지
        - 관리자 등록 및 로그인
- 프로젝트 기간: 2024.02.23 - 2024.02.29
- 팀명 : I조
- 멤버 : 1조 (팀장 임현태, 김한솔, 구인영, 김용태)

## 기술 스택

- Spring Boot
- Spring Security
- DATA JPA
- Github Actions

## 구현 목표

- 필수 기능 구현
- 관리자 기능 구현
- 테스트코드 작성
- Refresh Token

# 프로젝트 설계

## 와이어 프레임

!![Pasted image 20240229093249](https://github.com/nbc-ijo/nbc-ijo/assets/135244018/c0f4b1e7-fbc9-47f1-98da-c04e5f57c9ce)

## API 명세

[API 명세 링크](https://teamsparta.notion.site/I-6f97fc62876f42efaba6b433419fc477)

## ERD

![Pasted image 20240229093736](https://github.com/nbc-ijo/nbc-ijo/assets/135244018/c0949346-598c-403e-ad56-22a9e5d47abe)

## 환경 변수

```
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.show_sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.use_sql_comments=true

spring.datasource.url={databaseurl}
spring.datasource.username={username}
spring.datasource.password={password}

jwt.secret.key={value}
admin.token={value}
```

