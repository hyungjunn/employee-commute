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
- [x] 회사에 있는 팀을 등록
  - [x] `팀이름`(필수 정보)

### 직원 등록 기능
- [x] 직원을 등록
  - [x] `직원 이름`
  - [x] `팀의 매니저인지 매니저가 아닌지 여부`
  - [x] `회사에 들어온 일자`
  - [x] `생일`

### 팀 조회 기능
- [x] 모든 직원의 정보를 한 번에 조회할 수 있어야 한다.
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
- [x] 모든 직원의 정보를 한 번에 조회할 수 있어야 한다.
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

---

## 프로젝트 2단계

### 직원 출근 api
- [x] 직원의 출근을 직원의 `id`기준으로 처리

### 직원 퇴근 api
- [x] 출근한 직원을 `id`를 기준으로 처리

### 특정 직원의 날짜별 근무시간 조회 api
- [x] 특정 직원 `id`와 `2024-01`과 같이 연/월을 받으면, 날짜별 근무 시간과 총 합을 반환(근무 시간은 분단위로 계산)
```
{
  "detail": [
    {
      "data": "2024-01-01",
      "workingMinutes": 480
    },
    {
      "date": "2024-01-02",
      "workingMinutes": 490
    },
    ... // 2024년 1월 31일까지 존재할 수 있다.
  ],
  "sum": 10560
}
```

---

## API 설계

### 직원 출근 api

- HTTP Method: POST
- HTTP path: /commute/arrive
- HTTP body (JSON)
- 결과 반환 X (HTTP status 200 ok)

### 직원 퇴근 api

- HTTP Method: PUT
- HTTP path: /commute/leave
- HTTP body (JSON)
- 결과 반환 X (HTTP status 200 ok)

### 특정 직원의 날짜별 근무시간 조회 api

- HTTP Method: GET
- HTTP path: /commute/history/month
- 쿼리: 직원 id, 연/월
- 결과 반환 (JSON)

---

## 프로젝트 3단계

### 연차 등록 api

- [x] 직원의 연차를 등록하는 api
  - 연차는 무조건 하루 단위로만 사용이 가능
  - 올해 입사 직원은 11개, 그 외는 15개 가능
  - 연차를 사용하려면 연차 사용일 기준 며칠전 `연차 등록`을 해야한다. 연차를 등록하기만 하면, 매니적의 허가 없이 연차가 바로 적용된다.
    - 단, 며칠 전에 연차를 등록해야 하는지는 팀 마다 다르게 적용된다.
    - 예를 들어 A 팀은 하루 전에만 등록하면 연차를 사용할 수 있지만, B 팀은 7일 전에 등록해야 연차를 사용할 수 있다.

### 연차 조회 api

- [x] 각 직원은 `id`를 이용해 올해 사용하지 않고 남은 연차를 확인할 수 있다.

### 특정 직원의 날짜별 근무시간 조회 api V2

- 연차를 신청할 수 있게 되며, <프로젝트 2단계>에서 개발했던 기능도 조금 변경해야 한다.
- [x] 만약 연차를 사용했다면, `usingDayOff : true`가 반환되어야 한다.
```
{
  "detail": [
    {
      "data": "2024-01-01",
      "workingMinutes": 480
      "usingDayOff": false, // 연차를 사용하지 않았으니 false가 반환
    },
    {
      "date": "2024-01-02",
      "workingMinutes": 490
        "usingDayOff": true, // 연차를 사용할 날은 true가 반환
    },
    ... // 2024년 1월 31일까지 존재할 수 있다.
  ],
  "sum": 10560
}
```

---

## API 설계

### 연차 등록 api
- HTTP Method: POST
- HTTP path: /leave/annual
- HTTP body (JSON)
- 결과 반환 X (HTTP status 200 ok)

### 남은 연차 조회 api
- HTTP Method: GET
- HTTP path: /leave/annual
- 쿼리: employeeId
- 결과 반환 (JSON)

---

## 프로젝트 4단계

### 초과 근무 계산 api
- 모든 직원은 하루 근무 8시간을 기준으로 근로 계약을 맺고 있다.
- 근무 시간은 주말과 법정 공휴일은 제외하고 계산되어야 한다.
- ex) 2024-01은 전체 근무일은 22일로, 토/일 8일과 1월 1일이 빠진 수치다. (31 - 8 - 1 = 22) 따라서, 176 시간이 기준 근무 시간이 된다.
- 초과 근무 계산 api는 `2024-01`과 같이 연/월을 받아 모든 직원의 초과 근무 시간을 분단위로 계산해주어야 한다.
```
[
  {
    "id": 1,
    "name":"홍길동",
    "overtimeMinutes": 120 // 2024년 1월에 2시간을 초과 근무한 직원 
  }, 
  {
    "id": 2,
    "name": "임꺽정"
    "overtimeMinutes": 0 // 2024년 1월에는 초과 근무를 하지 않은 직원
  }
]
```
#### 추가 요구 사항
- 회사 사장님께서 매달 1일 오전, 이전달의 초과 근무 계산 결과를 csv 혹은 엑셀 형태로 받아 보고 싶다 한다. 해당 기능을 만들어라.

### 시스템에 배포
- 지금까지 개발한 시스템을 배포해보도록 한다.
- free tier RDS를 적용해도 된다.

#### 추가 요구 사항
- 배포를 할 때 
  - EC2에 접속
  - 변경된 코드 내용을 `git pull`명령어로 가져온 다음
  - jar 파일을 직접 빌드하고 
  - 기존 자바 프로세스를 제거하고
  - 새로 빌드한 jar파일을 실행시키는
  
  과정을 거치고 있다. 
  이 과정을 자동화 하라.
- 키워드: CI/CD

