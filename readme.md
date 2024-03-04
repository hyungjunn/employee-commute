# 출퇴근 시스템

[인프런 워밍업 클럽 - 스터디 0기](https://www.inflearn.com/course/inflearn-warmup-club-study-0)에서 진행하는 마지막 과제입니다.

참고 강의: [자바와 스프링 부트로 생애 최초 서버 만들기, 누구나 쉽게 개발부터 배포까지! [서버 개발 올인원 패키지]](https://www.inflearn.com/course/%EC%9E%90%EB%B0%94-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-%EC%84%9C%EB%B2%84%EA%B0%9C%EB%B0%9C-%EC%98%AC%EC%9D%B8%EC%9B%90)

## 사용될 기술 스택

- Java 17
- Spring Boot 3.2.3
- JPA
- MySQL

## 프로젝트 1단계

- [x] 프로젝트 최초 설정

### 팀 등록 기능
- [ ] 회사에 있는 팀을 등록
  - [ ] `팀이름`(필수 정보)

### 직원 등록 기능
- [ ] 직원을 등록
  - [ ] `직원 이름`
  - [ ] `팀의 매니저인지 매니저가 아닌지 여부`
  - [ ] `회사에 들어온 일자`
  - [ ] `생일`

### 팀 조회 기능
- [ ] 모든 직원의 정보를 한 번에 조회할 수 있어야 한다.
``` 
[
  {
    "name": "팀이름",
    "manager": "팀 매니저 이름" // 없을 경우 null
    "memberCount": 팀 인원 수 [숫자]
  }, ...
]
```

### 직원 조회 기능
- [ ] 모든 직원의 정보를 한 번에 조회할 수 있어야 한다.
``` 
[
  {
    "name": "직원 이름",
    "teamName": "소속 팀 이름",
    "role": "MANAGER" or "MEMBER", // 팀의 매니저인지 멤버인지
    "birthday": "1989-01-01,
    "workStartDate": "2024-01-01",
  }, ...
]
```

---

## API 설계

### 팀 등록 api

- HTTP Method: POST
- HTTP Path: /team
- HTTP Body(JSON)
- 결과 반환 X (HTTP status 200 ok)

### 직원 등록 api

- HTTP Method: POST
- HTTP Path: /employee
- HTTP Body(JSON)
- 결과 반환 X (HTTP status 200 ok)

### 팀 조회 api 

- HTTP Method: GET
- HTTP Path: /team
- 쿼리 없음
- 결과 반환 JSON

### 직원 조회 api

- HTTP Method: GET
- HTTP Path: /employee
- 쿼리 없음
- 결과 반환 JSON