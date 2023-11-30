CREATE DATABASE IF NOT EXISTS bookProject;
USE bookProject;

CREATE TABLE roles (
    roleId BIGINT NOT NULL AUTO_INCREMENT,
    roleName VARCHAR(255),
    PRIMARY KEY (roleId)
);

CREATE TABLE users (
    userId BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(255),
    userPassword VARCHAR(255),
    userRole VARCHAR(255),
    username VARCHAR(255),
    roleId BIGINT,
    PRIMARY KEY (userId)
);

CREATE TABLE editorials (
    id BIGINT NOT NULL AUTO_INCREMENT,
    editorialName VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE books (
    bookId BIGINT NOT NULL AUTO_INCREMENT,
    author VARCHAR(255),
    bookingStatus VARCHAR(255),
    reservationDate DATETIME(6),
    reservationDuration DATETIME(6),
    reserved INT NOT NULL,
    title VARCHAR(255),
    editorialName BIGINT,
    ownerId BIGINT,
    PRIMARY KEY (bookId)
);

CREATE TABLE reservations (
    id BIGINT NOT NULL AUTO_INCREMENT,
    requestDate DATETIME(6),
    returnDate DATETIME(6),
    bookId BIGINT,
    userId BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE ratings (
    id BIGINT NOT NULL AUTO_INCREMENT,
    comment VARCHAR(255),
    score INT NOT NULL,
    reservationId BIGINT,
    userId BIGINT,
    PRIMARY KEY (id)
);

INSERT INTO roles (roleName) VALUES ('Admin');
INSERT INTO roles (roleName) VALUES ('User');
INSERT INTO roles (roleName) VALUES ('Editor');

INSERT INTO users (email, userPassword, userRole, username, roleId) VALUES ('admin@example.com', 'adminpass', 'Admin', 'AdminUser', 1);
INSERT INTO users (email, userPassword, userRole, username, roleId) VALUES ('user1@example.com', 'userpass1', 'User', 'User1', 2);
INSERT INTO users (email, userPassword, userRole, username, roleId) VALUES ('user2@example.com', 'userpass2', 'User', 'User2', 2);

INSERT INTO editorials (editorialName) VALUES ('Editorial A');
INSERT INTO editorials (editorialName) VALUES ('Editorial B');

INSERT INTO books (author, bookingStatus, reservationDate, reservationDuration, reserved, title, editorialName, ownerId) VALUES ('Author1', 'Available', '2023-11-24 12:00:00', '2023-11-30 12:00:00', 0, 'Book1', 1, 2);
INSERT INTO books (author, bookingStatus, reservationDate, reservationDuration, reserved, title, editorialName, ownerId) VALUES ('Author2', 'Reserved', '2023-11-25 14:00:00', '2023-12-01 14:00:00', 1, 'Book2', 2, 3);

INSERT INTO reservations (requestDate, returnDate, bookId, userId) VALUES ('2023-11-26 10:00:00', '2023-11-28 10:00:00', 1, 2);
INSERT INTO reservations (requestDate, returnDate, bookId, userId) VALUES ('2023-11-27 15:00:00', '2023-12-02 15:00:00', 2, 3);

INSERT INTO ratings (comment, score, reservationId, userId) VALUES ('Great book!', 5, 1, 2);
INSERT INTO ratings (comment, score, reservationId, userId) VALUES ('Very great book!', 5, 2, 1);
INSERT INTO ratings (comment, score, reservationId, userId) VALUES ('Enjoyed reading it.', 4, 2, 3);