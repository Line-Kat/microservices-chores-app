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