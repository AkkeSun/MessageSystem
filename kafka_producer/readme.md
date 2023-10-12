
## [ Kafka 메시징 시스템 ]

### 설치하기 (Docker)
```
docker compose up -d
```



### 기본 명령어

````
# kafka 접속
docker exec -it kafka bash

# 토픽 리스트 조회
kafka-topics.sh --bootstrap-server localhost:9092 --list

# 토픽 정보 조회
kafka-topics.sh --bootstrap-server localhost:9092 --topic new-topic --describe

# 토픽 생성 (replication-factor : 복제본 수, 주의! 파티션은 한 번 생성하면 줄일 수 없음) 
kafka-topics.sh --bootstrap-server localhost:9092 --replication-factor 1 --partitions 2 --topic new-topic --create

# 토픽 삭제
kafka-topics.sh --bootstrap-server localhost:9092 --topic new-topic --delete
````