
 CREATE DATABASE LibraryManagementSystem;

USE LibraryManagementSystem;

CREATE TABLE book (
    book_id VARCHAR(10) PRIMARY KEY,
    name VARCHAR(40),
    isbn VARCHAR(20),
    publisher VARCHAR(30),
    edition VARCHAR(10),
    price DECIMAL(10, 2),
    pages INT
);

CREATE TABLE student (
    student_id VARCHAR(10) PRIMARY KEY,
    name VARCHAR(25),
    father VARCHAR(25),
    course VARCHAR(10),
    branch VARCHAR(10),
    year INT,
    semester INT
);

CREATE TABLE issueBook (
    book_id VARCHAR(10),
    student_id VARCHAR(10),
    bname VARCHAR(40),
    sname VARCHAR(40),
    course VARCHAR(20),
    branch VARCHAR(10),
    dateOfIssue DATE,
    PRIMARY KEY (book_id, student_id),
    FOREIGN KEY (book_id) REFERENCES book(book_id),
    FOREIGN KEY (student_id) REFERENCES student(student_id)
);

CREATE TABLE returnBook (
    book_id VARCHAR(10),
    student_id VARCHAR(10),
    bname VARCHAR(40),
    sname VARCHAR(40),
    course VARCHAR(20),
    branch VARCHAR(10),
    dateOfIssue DATE,
    dateOfReturn DATE,
    PRIMARY KEY (book_id, student_id),
    FOREIGN KEY (book_id) REFERENCES book(book_id),
    FOREIGN KEY (student_id) REFERENCES student(student_id)
);

CREATE TABLE account (
    username VARCHAR(20) PRIMARY KEY,
    name VARCHAR(25),
    password VARCHAR(25),
    sec_q VARCHAR(25),
    sec_ans VARCHAR(25)
);