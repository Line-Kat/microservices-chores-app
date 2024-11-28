Start the application

Start up Consul in CMD
- consul.exe
- consul agent -dev -node chores
- localhost:8500

Start up rabbitMQ in Ubuntu
- sudo dockers
- open a new terminal window
- docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.13-management
- localhost:15672

Start servers with mvn spring-boot:run
- Gateway service
- User service
- Chores service
- Reward service
- (ChildChore service)

To run several instances of a service (because of a bug in IntelliJ)
- select edit configuration of running through intelliJ
- select add new configuration
- enter name
- module java 21
- module the service I want to run
- choose modify options: add VM options: -Dserver.port=<port number>

When using Postman for testing, remember to check which port the Gateway is running on

I have used docker build to create the images (NB! Double check before delivery!)
A list of tags for the different services
- user: user:0.0.1
- consul-importer: consul-importer:1.15.0
- gateway: gateway:0.0.1
- rabbitmq: rabbitmq:3.13-management
- consul: consul:1.15.0

After I have ran docker-compose down, I need to create the network "docker_micro_chores" before
running docker-compose up (docker network create docker_micro_chores)
