# Workshelf - Book Loan Platform
## Introduction
Workshelf is a web platform developed to facilitate book lending and exchange among T-Systems employees. The application aims to promote a reading culture within the organization by allowing users to register, share, and borrow books.

## Technologies Used
### Backend (Spring Framework):
- Programming Language: Java 17
- Framework: Spring Framework 3.0.6
- Dependency Management: Maven 4.0.0
- Database: MySQL 8.1
- Dependencies:
spring-boot-starter-data-jpa
spring-boot-starter-security
spring-boot-starter-web
spring-boot-devtools
spring-boot-starter-tomcat
spring-boot-starter-test
spring-security-test
springdoc-openapi-starter-webmvc-ui
jjwt-api
jjwt-impl
jjwt-jackson
json
mysql-connector-j
lombok
hibernate-validator

### Frontend (Angular):
- Framework: Angular (Version)
- Dependency Management: npm
- Styling: CSS, HTML, Bootstrap

### Deployment
- Backend: Railway
- Frontend: AWS Amplify

### Continuous Integration/Continuous Deployment
- Heroku

### IDE
- Eclipse
- Visual Studio Code

### Repository
- GitHub

## Features

- User Registration and Authentication:
Users can register and authenticate using a username and password.

- Book Management:
Registered users can create, update, and delete books.
Users can make their books available for reservation.

- Search Functionality:
Users can search for books based on title, author, or ISBN.

- User Interaction:
Users can view the contact details of book owners.
Registered users can log out of the web application.

- Book Rating and Comments:
Registered users can rate and comment on the books they have reserved.

## Project Links
- Frontend Repository
- Backend Repository
- Database

## Data Model
The application's data model is designed to organize and relate information efficiently within the MySQL database. It includes tables for editorials, users, books, ratings, and reservations, with relationships established between them.

## Reservation and Return Management
The application includes a reservation and return management system where users can reserve available books, and the status of each book's reservation is indicated.

## User and Role Management
The application categorizes users into different roles, each with specific capabilities. The roles include:

- Unregistered User
- User (USER_ROLE)
- Administrator (ADMIN_ROLE)

Users with administrative roles have additional privileges, including modifying and deleting all books and managing comments within the application.

Note:
This README provides a brief overview of the Workshelf application. For more detailed information, refer to the full documentation in the respective repositories.