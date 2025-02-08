
 CREATE DATABASE LibraryManagementSystems;
USE LibraryManagementSystems;


CREATE TABLE book (
    book_id VARCHAR(10) PRIMARY KEY,
    name VARCHAR(40),
    isbn VARCHAR(20),
    publisher VARCHAR(30),
    editor VARCHAR(10),
    print_version VARCHAR(10),  
    pages VARCHAR(10)
);


CREATE TABLE student (
    student_id VARCHAR(10) PRIMARY KEY,
    name VARCHAR(25),
    father VARCHAR(30),
    course VARCHAR(10),
    branch VARCHAR(10),
    year VARCHAR(10),
    semester VARCHAR(10)
);


CREATE TABLE issueBook (
    issue_id INT AUTO_INCREMENT PRIMARY KEY,
    book_id VARCHAR(10),
    student_id VARCHAR(10),
    bname VARCHAR(40),
    sname VARCHAR(40),
    course VARCHAR(20),
    branch VARCHAR(10),
    dateofissue DATE,
    FOREIGN KEY (book_id) REFERENCES book(book_id),
    FOREIGN KEY (student_id) REFERENCES student(student_id)
);


CREATE TABLE returnBook (
    return_id INT AUTO_INCREMENT PRIMARY KEY,
    book_id VARCHAR(10),
    student_id VARCHAR(10),
    bname VARCHAR(40),
    sname VARCHAR(40),
    course VARCHAR(20),
    branch VARCHAR(20),
    dateofissue DATE,  
    dateofReturn DATE, 
    FOREIGN KEY (book_id) REFERENCES book(book_id),
    FOREIGN KEY (student_id) REFERENCES student(student_id)
);


CREATE TABLE Account (
    username VARCHAR(20) PRIMARY KEY,
    name VARCHAR(25),
    password VARCHAR(25),
    sec_q VARCHAR(50),  
    sec_ans VARCHAR(50) 
);

