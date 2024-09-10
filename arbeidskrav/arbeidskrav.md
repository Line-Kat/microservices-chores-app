# Arbeidskrav

## A general description of the program

This app is meant to motivate children to do their chores at home. 
A parent can log into the app and chose what chores the child is supposed to do.
The child gets its own profile that shows a list of chores and how much money is earned.
When a chore is done, the child can mark the chore as completed.
When the chores of a day are done, the child gets a positive feedback and an amount of money.

## User stories

The user stories are divided into what a parent can do and what a child can do.

### Parent

- As a parent, I can log into an account with username and password, so that my information can be saved safely
- As a parent, I can create a profile for my child, so that chores and balance on that profile belongs to only that child
- As a parent, I can add and remove chores to my child's profile, so that I have control over what chores are on the child's profile
- As a parent, I can create, save and modify a week plan of chores, so that it's easier to add chores to a child's profile
- As a parent, I can see the chores the child has marked as done to confirm that a chore is done, so that I can make sure that the child has actually done the chore
- As a parent, I can handle the child's balance, so that I can add to the balance when chores are done and subtract from the balance when money is used
- As a parent, I can create a saving goal for my child, so that the child in motivated to complete the chores
- As a parent, I can see the statistics for the chores done or not done for each child, so that I can modify the chores to help my child staying motivated

### Child

- As a child, I can access my profile by using the app on my parent's phone, so that I can see my balance and the list of chores
- As a child, I can use a QR code generated by the app on my parent's phone, so that I can get access to my profile on my own phone
- As a child, I can see the list of chores of the day, so that I know what I need to do that day
- As a child, I can mark a chore as done, so that I can see the progress of the day's chores  
- As a child, I can unmark a chore I marked as done, so that I can correct it if I marked a chore as done by a mistake 
- As a child, I can see if a chore I marked as done is confirmed my parent, so that I know if I need to keep working on the chore
- As a child, I get a positive feedback when all the chores of a day is done and confirmed by my parent, so that I know I have completed the chores of the day and keeps me motivated to keep completing the chores given to me
- As a child, I can see a week view of chores, so that I can get an overview of all the chores of the week to make it more predictable
- As a child, I can see my balance, so that I can see how much money I have earned
- As a child, I can see the progress towards my saving goal, so that I get motivated to complete the chores
- As a child, I can earn addition money by completing all the chores of a day a number of days in a row, so that I get more motivated to complete the chores

## General structure

### Log in
- handles a parent logging in
- handles a child getting access to their profile on their own phone (QR code)

### User
- parent account
- child profile

### Chores
- parent add chores to child profile
- parent remove chores from child profile 
- accept chore as completed by a child
- reset a chore as not completed
- confirm chores as actually completed by a parent
- create a week plan with chores (can be saved, modified and reused)
- show the chores to the user
- show the state of a chore as completed or not completed
- feedback message to the child if all the chores of the day is completed (after a parent has confirmed)

### Reward
- add to the balance
- subtract from the balance
- keeping track of the saving goal
- show the balance to the user

## Requirements

### Minimal product

- As a parent, I can create a profile for my child, so that chores and balance on that profile belongs to only that child
- As a parent, I can add and remove chores to my child's profile, so that I have control over what chores are on the child's profile
- As a parent, I can handle the child's balance, so that I can add to the balance when chores are done and subtract from the balance when money is used

- As a child, I can access my profile by using the app on my parent's phone, so that I can see my balance and the list of chores
- As a child, I can see the list of chores of the day, so that I know what I need to do that day
- As a child, I can mark a chore as done, so that I can see the progress of the day's chores
- As a child, I can see my balance, so that I can see how much money I have earned

### Desirable features

- As a parent, I can log into an account with username and password, so that my information can be saved safely
- As a parent, I can see the chores the child has marked as done and confirm that a chore is done, so that I can make sure that the child has actually done the chore
- As a parent, I can create a saving goal for my child, so that the child in motivated to complete the chores

- As a child, I can see if a chore I marked as done is confirmed my parent, so that I know if I need to keep working on the chore
- As a child, I can unmark a chore I marked as done, so that I can correct it if I marked a chore as done by a mistake
- As a child, I get a positive feedback when all the chores of a day is done and confirmed by my parent, so that I know I have completed the chores of the day and keeps me motivated to keep completing the chores given to me
- As a child, I can see the progress towards my saving goal, so that I get motivated to complete the chores

### Optimistic requirements

- As a parent, I can create, save and modify a week plan of chores, so that it's easier to add chores to a child's profile
- As a parent, I can see the statistics for the chores done or not done for each child, so that I can modify the chores to help my child staying motivated

- As a child, I can use a QR code generated by the app on my parent's phone, so that I can get access to my profile on my own phone
- As a child, I can see a week view of chores, so that I can get an overview of all the chores of the week to make it more predictable
- As a child, I can earn addition money by completing all the chores of a day a number of days in a row, so that I get more motivated to complete the chores