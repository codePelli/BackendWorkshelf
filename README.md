# Workshelf - Book Lending Platform

## Introduction

In response to the growing interest in reading among T-Systems collaborators, the web platform "Workshelf" has been created. This application aims to facilitate book lending and exchange among employees to encourage a reading habit. Workshelf allows users to register, exchange, and reserve books, as well as leave ratings and comments on works.

## Technologies Used

### Backend (Spring Framework)

- **Programming Language:** Java 17
- **Framework:** Spring Framework 3.0.6
- **Dependency Manager:** Maven 4.0.0
- **Database:** MySQL 8.1
- **Dependencies:** (among others)
  - `spring-boot-starter-data-jpa`
  - `spring-boot-starter-security`
  - `spring-boot-starter-web`
  - `spring-boot-devtools`
  - `spring-boot-starter-tomcat`
  - `spring-boot-starter-test`
  - `spring-security-test`
  - `springdoc-openapi-starter-webmvc-ui`
  - `jwt-api`, `jwt-impl`, `jwt-jackson`
  - `json`, `mysql-connector-j`, `lombok`, `hibernate-validator`

### Frontend (Angular)

- **Framework:** Angular 17
- **Dependency Management:** npm
- **Styling and Layout:** CSS, HTML, Bootstrap

### Deploy

- **Backend:** Railway
- **Frontend:** AWS Amplify

### Continuous Integration/Continuous Deployment

- Heroku

### IDE

- Eclipse
- Visual Studio Code

### Repository

- GitHub

## Features

1. **Unregistered Users:** Can search for books, view the list of books, and see details of each book. They can also register in the application.

2. **Registered Users (USER_ROLE):** Have full access to the application, including CRUD of their profile, searching for books and users, book reservations, rating and commenting on reserved books, and CRUD of their own books.

3. **Administrators (ADMIN_ROLE):** In addition to the functions of registered users, administrators can modify and delete all books, as well as manage the deletion of comments and users.

## Project Links

- **Frontend Repository:**
  [GitHub - Workshelf Frontend](https://github.com/sergial273/WorkshelfFrontend/)

- **Backend Repository:**
  [GitHub - BackendWorkshelf](https://github.com/codePelli/BackendWorkshelf)

## Database

The MySQL database of "Workshelf" is essential for managing information related to books, users, editorials, ratings, and reservations.

- **Database:** [MySQL Workshelf](mysql://root:b2E1bE1B5a223B14H55AG5ba4BBFgFBd@mysql.railway.internal:3306/railway)

## Data Model

The data model defines the structure of the application, organizing information into tables such as Editorials, Users, Books, Ratings, and Reservations. These tables are interrelated to efficiently capture key information.

## Reservation and Return Management

The reservation and return management allows registered users to reserve books, with details such as reservation duration and the ability to mark books as "recovered" once returned.

## User and Role Management

The application has three user roles: Unregistered User, Registered User (USER_ROLE), and Administrator (ADMIN_ROLE). Each role has specific functions to ensure a personalized and secure experience.

For detailed information on functionalities and roles for each user, refer to the corresponding section in the project's full documentation.
