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

-- Roles
INSERT INTO roles (roleName) VALUES ('Admin');
INSERT INTO roles (roleName) VALUES ('User');

-- Users
INSERT INTO users (email, userPassword, userRole, username, roleId) VALUES ('admin@example.com', 'adminpass', 'Admin', 'AdminUser', 1);
INSERT INTO users (email, userPassword, userRole, username, roleId) VALUES ('user1@example.com', 'userpass1', 'User', 'User1', 2);
INSERT INTO users (email, userPassword, userRole, username, roleId) VALUES ('user2@example.com', 'userpass2', 'User', 'User2', 2);
INSERT INTO users (email, userPassword, userRole, username, roleId) VALUES ('editor1@example.com', 'editorpass1', 'Editor', 'Editor1', 3);
INSERT INTO users (email, userPassword, userRole, username, roleId) VALUES ('reader1@example.com', 'readerpass1', 'Reader', 'Reader1', 4);
INSERT INTO users (email, userPassword, userRole, username, roleId) VALUES ('author1@example.com', 'authorpass1', 'Author', 'Author1', 5);
INSERT INTO users (email, userPassword, userRole, username, roleId) VALUES ('librarian1@example.com', 'librarianpass1', 'Librarian', 'Librarian1', 6);
INSERT INTO users (email, userPassword, userRole, username, roleId) VALUES ('guest1@example.com', 'guestpass1', 'Guest', 'Guest1', 7);
INSERT INTO users (email, userPassword, userRole, username, roleId) VALUES ('contributor1@example.com', 'contributorpass1', 'Contributor', 'Contributor1', 8);
INSERT INTO users (email, userPassword, userRole, username, roleId) VALUES ('subscriber1@example.com', 'subscriberpass1', 'Subscriber', 'Subscriber1', 9);
INSERT INTO users (email, userPassword, userRole, username, roleId) VALUES ('moderator1@example.com', 'moderatorpass1', 'Moderator', 'Moderator1', 10);
INSERT INTO users (email, userPassword, userRole, username, roleId) VALUES ('chief1@example.com', 'chiefpass1', 'Editor-in-chief', 'Chief1', 11);
INSERT INTO users (email, userPassword, userRole, username, roleId) VALUES ('translator1@example.com', 'translatorpass1', 'Translator', 'Translator1', 12);
INSERT INTO users (email, userPassword, userRole, username, roleId) VALUES ('reviewer1@example.com', 'reviewerpass1', 'Reviewer', 'Reviewer1', 13);
INSERT INTO users (email, userPassword, userRole, username, roleId) VALUES ('artist1@example.com', 'artistpass1', 'Artist', 'Artist1', 14);
INSERT INTO users (email, userPassword, userRole, username, roleId) VALUES ('designer1@example.com', 'designerpass1', 'Designer', 'Designer1', 15);

-- Editorials
INSERT INTO editorials (editorialName) VALUES ('Editorial A');
INSERT INTO editorials (editorialName) VALUES ('Editorial B');
INSERT INTO editorials (editorialName) VALUES ('Editorial C');
INSERT INTO editorials (editorialName) VALUES ('Editorial D');
INSERT INTO editorials (editorialName) VALUES ('Editorial E');
INSERT INTO editorials (editorialName) VALUES ('Editorial F');
INSERT INTO editorials (editorialName) VALUES ('Editorial G');
INSERT INTO editorials (editorialName) VALUES ('Editorial H');
INSERT INTO editorials (editorialName) VALUES ('Editorial I');
INSERT INTO editorials (editorialName) VALUES ('Editorial J');
INSERT INTO editorials (editorialName) VALUES ('Editorial K');
INSERT INTO editorials (editorialName) VALUES ('Editorial L');
INSERT INTO editorials (editorialName) VALUES ('Editorial M');
INSERT INTO editorials (editorialName) VALUES ('Editorial N');
INSERT INTO editorials (editorialName) VALUES ('Editorial O');

-- Books
INSERT INTO books (author, bookingStatus, reservationDate, reservationDuration, reserved, title, editorialName, ownerId) VALUES ('Author1', 'Available', '2023-11-24 12:00:00', '2023-11-30 12:00:00', 0, 'Book1', 1, 2);
INSERT INTO books (author, bookingStatus, reservationDate, reservationDuration, reserved, title, editorialName, ownerId) VALUES ('Author2', 'Reserved', '2023-11-25 14:00:00', '2023-12-01 14:00:00', 1, 'Book2', 2, 3);
INSERT INTO books (author, bookingStatus, reservationDate, reservationDuration, reserved, title, editorialName, ownerId) VALUES ('Author3', 'Available', '2023-11-26 10:30:00', '2023-12-02 10:30:00', 0, 'Book3', 3, 4);
INSERT INTO books (author, bookingStatus, reservationDate, reservationDuration, reserved, title, editorialName, ownerId) VALUES ('Author4', 'Reserved', '2023-11-27 15:45:00', '2023-12-03 15:45:00', 1, 'Book4', 4, 5);
INSERT INTO books (author, bookingStatus, reservationDate, reservationDuration, reserved, title, editorialName, ownerId) VALUES ('Author5', 'Available', '2023-11-28 08:20:00', '2023-12-04 08:20:00', 0, 'Book5', 5, 6);
INSERT INTO books (author, bookingStatus, reservationDate, reservationDuration, reserved, title, editorialName, ownerId) VALUES ('Author6', 'Reserved', '2023-11-29 16:00:00', '2023-12-05 16:00:00', 1, 'Book6', 6, 7);
INSERT INTO books (author, bookingStatus, reservationDate, reservationDuration, reserved, title, editorialName, ownerId) VALUES ('Author7', 'Available', '2023-11-30 09:45:00', '2023-12-06 09:45:00', 0, 'Book7', 7, 8);
INSERT INTO books (author, bookingStatus, reservationDate, reservationDuration, reserved, title, editorialName, ownerId) VALUES ('Author8', 'Reserved', '2023-12-01 14:30:00', '2023-12-07 14:30:00', 1, 'Book8', 8, 9);
INSERT INTO books (author, bookingStatus, reservationDate, reservationDuration, reserved, title, editorialName, ownerId) VALUES ('Author9', 'Available', '2023-12-02 11:10:00', '2023-12-08 11:10:00', 0, 'Book9', 9, 10);
INSERT INTO books (author, bookingStatus, reservationDate, reservationDuration, reserved, title, editorialName, ownerId) VALUES ('Author10', 'Reserved', '2023-12-03 17:20:00', '2023-12-09 17:20:00', 1, 'Book10', 10, 11);
INSERT INTO books (author, bookingStatus, reservationDate, reservationDuration, reserved, title, editorialName, ownerId) VALUES ('Author11', 'Available', '2023-12-04 12:40:00', '2023-12-10 12:40:00', 0, 'Book11', 11, 12);
INSERT INTO books (author, bookingStatus, reservationDate, reservationDuration, reserved, title, editorialName, ownerId) VALUES ('Author12', 'Reserved', '2023-12-05 15:15:00', '2023-12-11 15:15:00', 1, 'Book12', 12, 13);
INSERT INTO books (author, bookingStatus, reservationDate, reservationDuration, reserved, title, editorialName, ownerId) VALUES ('Author13', 'Available', '2023-12-06 09:00:00', '2023-12-12 09:00:00', 0, 'Book13', 13, 14);
INSERT INTO books (author, bookingStatus, reservationDate, reservationDuration, reserved, title, editorialName, ownerId) VALUES ('Author14', 'Reserved', '2023-12-07 13:25:00', '2023-12-13 13:25:00', 1, 'Book14', 14, 15);
INSERT INTO books (author, bookingStatus, reservationDate, reservationDuration, reserved, title, editorialName, ownerId) VALUES ('Author15', 'Available', '2023-12-08 10:55:00', '2023-12-14 10:55:00', 0, 'Book15', 15, 16);
INSERT INTO books (author, bookingStatus, reservationDate, reservationDuration, reserved, title, editorialName, ownerId) VALUES ('Author16', 'Reserved', '2023-12-09 16:30:00', '2023-12-15 16:30:00', 1, 'Book16', 16, 17);
INSERT INTO books (author, bookingStatus, reservationDate, reservationDuration, reserved, title, editorialName, ownerId) VALUES ('Author17', 'Available', '2023-12-10 11:15:00', '2023-12-16 11:15:00', 0, 'Book17', 17, 18);

-- Reservations
INSERT INTO reservations (requestDate, returnDate, bookId, userId) VALUES ('2023-11-26 10:00:00', '2023-11-28 10:00:00', 1, 2);
INSERT INTO reservations (requestDate, returnDate, bookId, userId) VALUES ('2023-11-27 15:00:00', '2023-12-02 15:00:00', 2, 3);
INSERT INTO reservations (requestDate, returnDate, bookId, userId) VALUES ('2023-11-28 12:30:00', '2023-12-04 12:30:00', 3, 4);
INSERT INTO reservations (requestDate, returnDate, bookId, userId) VALUES ('2023-11-29 14:45:00', '2023-12-06 14:45:00', 4, 5);
INSERT INTO reservations (requestDate, returnDate, bookId, userId) VALUES ('2023-11-30 09:15:00', '2023-12-07 09:15:00', 5, 6);
INSERT INTO reservations (requestDate, returnDate, bookId, userId) VALUES ('2023-12-01 13:00:00', '2023-12-08 13:00:00', 6, 7);
INSERT INTO reservations (requestDate, returnDate, bookId, userId) VALUES ('2023-12-02 10:30:00', '2023-12-09 10:30:00', 7, 8);
INSERT INTO reservations (requestDate, returnDate, bookId, userId) VALUES ('2023-12-03 16:20:00', '2023-12-10 16:20:00', 8, 9);
INSERT INTO reservations (requestDate, returnDate, bookId, userId) VALUES ('2023-12-04 11:45:00', '2023-12-11 11:45:00', 9, 10);
INSERT INTO reservations (requestDate, returnDate, bookId, userId) VALUES ('2023-12-05 14:10:00', '2023-12-12 14:10:00', 10, 11);
INSERT INTO reservations (requestDate, returnDate, bookId, userId) VALUES ('2023-12-06 08:55:00', '2023-12-13 08:55:00', 11, 12);
INSERT INTO reservations (requestDate, returnDate, bookId, userId) VALUES ('2023-12-07 12:25:00', '2023-12-14 12:25:00', 12, 13);
INSERT INTO reservations (requestDate, returnDate, bookId, userId) VALUES ('2023-12-08 11:00:00', '2023-12-15 11:00:00', 13, 14);
INSERT INTO reservations (requestDate, returnDate, bookId, userId) VALUES ('2023-12-09 15:30:00', '2023-12-16 15:30:00', 14, 15);
INSERT INTO reservations (requestDate, returnDate, bookId, userId) VALUES ('2023-12-10 10:15:00', '2023-12-17 10:15:00', 15, 16);

-- Ratings
INSERT INTO ratings (comment, score, reservationId, userId) VALUES ('Great book!', 5, 1, 2);
INSERT INTO ratings (comment, score, reservationId, userId) VALUES ('Enjoyed reading it.', 4, 2, 3);
INSERT INTO ratings (comment, score, reservationId, userId) VALUES ('Could be better.', 3, 3, 4);
INSERT INTO ratings (comment, score, reservationId, userId) VALUES ('Excellent!', 5, 4, 5);
INSERT INTO ratings (comment, score, reservationId, userId) VALUES ('Not my type.', 2, 5, 6);
INSERT INTO ratings (comment, score, reservationId, userId) VALUES ('Intriguing plot.', 4, 6, 7);
INSERT INTO ratings (comment, score, reservationId, userId) VALUES ('A bit boring.', 2, 7, 8);
INSERT INTO ratings (comment, score, reservationId, userId) VALUES ('Must-read!', 5, 8, 9);
INSERT INTO ratings (comment, score, reservationId, userId) VALUES ('Disappointing ending.', 3, 9, 10);
INSERT INTO ratings (comment, score, reservationId, userId) VALUES ('Captivating story.', 4, 10, 11);
INSERT INTO ratings (comment, score, reservationId, userId) VALUES ('Couldn''t put it down.', 5, 11, 12);
INSERT INTO ratings (comment, score, reservationId, userId) VALUES ('Mediocre at best.', 2, 12, 13);
INSERT INTO ratings (comment, score, reservationId, userId) VALUES ('Thrilling twists.', 4, 13, 14);
INSERT INTO ratings (comment, score, reservationId, userId) VALUES ('Unimpressed.', 2, 14, 15);
INSERT INTO ratings (comment, score, reservationId, userId) VALUES ('Recommended!', 4, 15, 16);

