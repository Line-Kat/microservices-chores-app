## Instructions on how to build and run the application
### Build
First package the services using Maven </br>
Run `mvn clean package -DskipTests` in the root folder of the project </br>
I have used 'docker build' to build the images </br></br>
To build all the images run `./build_docker.sh` in the root folder of the project </br></br>
**A table with tags for the different services** </br>

| Service         | Tag                                              |
|-----------------|--------------------------------------------------|
| gateway         | gateway:0.0.1                                    |
| consul-importer | consul-importer:1.15.0 (folder: ./docker/consul) |
| user            | user:0.0.1                                       |
| reward          | reward:0.0.1                                     |
| childChore      | childchore:0.0.1                                 |
| chores          | chores:0.0.1                                     |



### Run
`docker-compose -f docker-compose-service4.yml up`

## Description of the app
This app is meant to motivate children to do their chores at home and gain experience with 
earning their own money. Chores can be vacuuming, cleaning their room, taking out the trash, 
walking the dog, etc. A parent creates an account and can create several child profiles. It’s 
the parent that manages which chores are on the child's profile. A child has its own profile 
where today’s chores and balance is shown. When a chore is done, the child can mark it as 
completed. When all the chores for a day are marked as done, an amount of money is added to 
the child’s balance. In addition, the child can create a saving goal and as they earn money, 
they will see the remaining amount needed to reach their goal decrease. This might help keep the 
child’s motivation up to continue doing chores in addition to learning about economics.

### The services
- ### gateway
lb (user, childChore, reward)

- ### consul-importer
service that closes after importing configuration-files to Consul

- ### user
The service has the responsibility for managing the user profiles.

- ### reward
The service has the responsibility for creating, storing and updating a child’s balance
and saving goals. Rewards listens to rabbitMQ for messages with a child's completed 
chores, and then updates the balance and saving goal.

- ### chores
The service has the responsibility for retrieving chores from a database. Initial chores are 
added to the database using migration scripts.

- ### childChore
The service has the responsibility for managing the child’s chores. When adding chores 
to a child additional data is added, like when the chore is to be completed and the 
value of completion. This is stored in the ChildChore service, linking to the child 
and chore using id’s. The service also has the responsibility to check the status of 
the child’s chores of the day. If all the chores of the day are completed, the 
ChildChore service sends a message to RabbitMQ containing the child’s chores of the 
day.

## User stories
### MVP
- As a parent user, I can create a child profile and add chores to it, so that I can manage the 
child’s chores
- As a parent user, I can create a saving goal on a child profile, so that the child can gain 
experience with economics
- As a child user, I can access my profile, so that I complete the chores of the day, see my 
balance and the progress towards my saving goal

### Prototype
- As a parent user, I want to select a chore from a predefined list and customize it with a 
specific date and value, so that I can add it to my child's list of chores.
- As a parent user, I want to create a new chore, so that I can add it to my child later 
- As a child user, I get a positive feedback when I completed all the chores of that day, so 
that I know I’m done with that day’s chores
- As a parent user, I can create several child profiles, so that I can manage several children
- As a parent user, I can remove chores from my child profiles, so that I can adjust the list 
to changes happening that day
- As a parent user, I can move the completion date of a chore, so that I can postpone a task 
that cannot be completed that day
- As a parent user, I can change the value of the completion of a child's chore, so that I can motivate the child
to complete the chore
- As a parent user, I can update my child’s balance when they are using their earnings, 
so that the balance is correct

## Architecture diagram
- services
  - domains
  - functions
- async/sync communication
