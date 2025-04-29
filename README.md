
# Ringle API 문서

## ✅ 실행 방법

```bash
com.example.ringle.RingleApplication -> 실행
```

- 브라우저에서 Swagger 접속: [http://localhost:8080/swagger-ui/index.html]

## ⚙️ 환경 설정 (application.yml)
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/ringle_db
    username: root
    password: 비밀번호
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
  sql:
    init:
      mode: always
```

## 실행환경
- mac

## 🛠️ 사용 기술
- Java 17
- Spring Boot 
- MySQL
- Swagger

## 📌 설계 개요
- 수업은 30분 또는 60분 단위로 운영되고, 정각 또는 30분에 시작
- 튜터는 AvailableTime으로 강의 가능 시간을 제공하고, 학생은 수업 가능한 시간/튜터를 조회함
- DTO는 Request/Response로 분리하여 역할 명확화 및 validation 처리
- Controller에 Swagger ExampleObject 적용하여 문서화

## 🔗 주요 API 목록
### Tutor
- POST `/api/tutors` - 튜터 등록
- GET `/api/tutors` - 튜터 전체 조회
- GET `/api/tutors/{id}` - 특정 튜터 조회

### Student
- POST `/api/students` - 학생 등록
- GET `/api/students` - 전체 학생 조회

### Available Time
- POST `/api/available-times` - 강의 시간 등록
- GET `/api/available-times/tutor/{tutorId}` - 특정 튜터의 시간 조회
- GET `/api/available-times/search` - 기간, 길이로 가능한 시간 조회

### Available Tutor
- POST `/api/available-tutors` - 수업 가능한 튜터 조회

### Lesson
- POST `/api/lessons` - 수업 등록
- GET `/api/lessons/student/{studentId}` - 특정 학생의 수업 목록 조회

## 🧪 테스트 시나리오 (Swagger 기준)

- POST API는 해당 API클릭후 try it out 클릭 -> JSON데이터 수정가능 or excute 바로 누르기 -> Response Code 확인

1. 튜터 등록: POST `/api/tutors` → `{ "name": "홍길동" }`
2. 학생 등록: POST `/api/students` → `{ "name": "최현빈" }`
3. 가용 시간 등록: POST `/api/available-times`
   ```json
   {
     "tutorId": 1,
     "startTime": "2025-05-01T10:00:00",
     "endTime": "2025-05-01T12:00:00"
   }
   ```
4. 수업 가능한 시간 검색: GET `/api/available-times/search` + query
5. 수업 가능한 튜터 검색: POST `/api/available-tutors`
   ```json
   {
     "studentId": 1,
     "startTime": "2025-05-01T10:00:00",
     "endTime": "2025-05-01T12:00:00",
     "duration": 30
   }
   ```
6. 수업 등록: POST `/api/lessons`
7. 학생 수업 목록 조회: GET `/api/lessons/student/{studentId}`
