## Docker Network
- docker network create --attachable -d bridge techbankNet

## Run Apache Kafka using docker
- Check video

## Run MongoDB using docker
- sudo docker run -it -d --name mongo-container -p 27017:27017 --network techbankNet --restart always -v mongodb_data_container:/data/db mongo:latest

## Run MySQL using docker
- sudo docker run -it -d --name mysql-container -p 3306:3306 --network techbankNet -e MYSQL_ROOT_PASSWORD=root --restart always -v mysql_data_container:/var/lib/mysql mysql:latest

## Java Microservices: CQRS & EventSourcing with Kafka
- __What is Command:__ A command is an object that is sent to the domain for a state change which is handled by a command handler.