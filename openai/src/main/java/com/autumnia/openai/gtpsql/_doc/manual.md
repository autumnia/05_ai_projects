# Manual

## 예제 주소
```url
https://platform.openai.com/docs/overview

```

## 키발급
```txt

```

## dependencies 추가
```gradle
	implementation 'org.springframework.boot:spring-boot-starter-data-jdbc'
	runtimeOnly 'com.mysql:mysql-connector-j'
```

## resources
```resource
resources/templates/sql.html
resources/sql-prompt-template.st
resources/movies.sql
resources/insert.sql
```

## yml database 설정
```yaml
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/movies
    username: root
    password: 12345
```

## 질문
```txt
송강호 배우가 출연한 영화와 해당 영화의 감독 정보를 알려줘
```