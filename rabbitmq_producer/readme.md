## [ RabbitMQ 메시징 시스템 ]

### 1. RabbitMQ 설치 (Docker)

````
# 첫 번째 -p : 사용자가 주고받을 포트
# 두 번째 -p : 관리자 페이지 포트 (http://localhost:15672)

docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 --restart=unless-stopped -e RABBITMQ_DEFAULT_USER=root -e RABBITMQ_DEFAULT_PASS=root rabbitmq:management
````

### 2. 테스트를 위한 Exchange & Queue 생성

````
# 관리자 페이지 접속
http://localhost:15672 

# Exchange 생성 (myExchange)
Exchanges > Add a new exchange > Name 과 Type 지정 

# Queue 생성 (mail_1, mail_2)
Queues> Add a new Queue > Name 등록

# Bining
Exchange > 연결할 Exchange 선택 > Bindings 
to queue : mail_1
Routing key : mail_1_key
````
