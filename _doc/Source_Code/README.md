```
create table user_credentials
(
    user_t_id       bigint   null,
    hashed_password char(60) not null,
    constraint user_t_id
        unique (user_t_id)
);


create table user
(
    t_id       bigint auto_increment
        primary key,
    name       varchar(100)                        not null,
    created_at timestamp default CURRENT_TIMESTAMP null
);


create table chat
(
    t_id       bigint auto_increment
        primary key,
    sender     varchar(100) null,
    receiver   varchar(100) null,
    message    varchar(500) null,
    created_at timestamp    null
);

```

---

<h1> TODO </h1>

1. 대화한 이력이 있는 목록 List에 대한 추가적인 스키마 설계

2. 최근 대화 이력, API 쿼리 수정

3. WSS 통신에 대해서, 지속적인 요청을 DB에 항상 INSERT 하는 형태가 올바른지에 대한 고민

- 요청 하나하나마다 DB에 Save하는 것이 옳바를까?? 트래픽이 터진다면 어떻게 구현을 해야 할까?? 에 대한 고찰

4. Redis 적용 방법

- 3번 항목에 대한 가장 대표적인 답 == Redis 도입

5. ZeroDownTime Deploy는 어떻게 구현할까??

- 기본적으로 HTTP는 Connection이 유지가 되지 않기 떄문에, 단순히 롤링을 통해 업데이트가 되어서 무방합니다.
- 하지만 WebSocket는 통신이 끊김에 따라, 연결된 인스턴스에 대한 배포 과정을 처리해야 합니다.
- 해당 부분에 대해서 고민해보고 학습하시는 시간이 있으시면 좋겠다고 생각합니다.
- 실제로 이런 부분에 대해서 강의를 따로 다루기도 하였으니 질문도 좋고 해당 강의를 참고하셔도 좋습니다.
- <a link="https://www.inflearn.com/course/%EB%8C%80%EC%9A%A9%EB%9F%89-%EC%B1%84%ED%8C%85-%EC%84%9C%EB%B2%84-%EC%B2%98%EB%A6%AC-%EC%9B%B9%EC%86%8C%EC%BC%93-%ED%86%B5%EC%8B%A0-2"> 강의 링크 </a>

---

<h1> 구동 방법 </h1>

서버는 `./start.sh` 또는 원하는 구동방법을 도입

클라이언트는 `npm run dev` 를 통해 구동
