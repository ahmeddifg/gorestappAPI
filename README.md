Hello,

Application Create using idea premium,

Run instructions:

++ please read my comments on each API in controllers folder


1- clone the project into local machine

2- open project root directory and locate docker-compose.yaml file contain a mongo database image

3- execute:  docker-compose up 

4- run the project as spring boot project.

5- open postman and start the flow, please find the postman collection at postmanfolder  

    - Register account: 'please use an email already exists at gorest to avoid 304 gorest issue '
      your registered information will be saved in local mongo DB, and this account record is linked to gorest user information.

    - login with email and password

    - send a new Post. ++Bonus

    - review my post.

    - review comments.
