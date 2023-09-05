# create database if not exists library;
# USE library;

create table user
(
    Id int primary key auto_increment,
    Name varchar(15) not null,
    Email varchar(20) not null unique,
    Password varchar(15) not null,
    IsAdmin bool default false
);

create table book
(
    ISBN int key unique,
    Name varchar(25) not null,
    Author varchar(25) not null,
    Year int not null
);

CREATE TABLE print(
    Id INT KEY auto_increment,
    ISBN INT,
    Status VARCHAR(20) DEFAULT 'Available',
    Archived BOOL DEFAULT FALSE,
    FOREIGN KEY (ISBN) REFERENCES book(ISBN)
);

create table client(
    Id int key auto_increment,
    Name varchar(30) not null,
    Email varchar(30) not null unique
);

create table service (
    Id int key auto_increment,
    PrintId int,
    BorrowDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ReturnDate DATE,
    ClientId int,
    UserId int,
    Returned BOOL DEFAULT FALSE,
    foreign key (PrintId) references print(Id),
    foreign key (ClientId) references client(Id) ,
    foreign key (UserId) references user(Id)
);

insert into user (Name,Email,Password,IsAdmin) values ('Sidati','sidati@gmail.com','sidati@123',true);
insert into book (ISBN, Name, Author, Year) values (111100, '1984', 'Jorge ORWELL', 1949),
                                                   (111101, 'Animal farm', 'Jorge ORWELL', 1940),
                                                   (236789, 'The social contract', 'J.J Rousseau', 2016);
insert into client (Name, Email) values ('Hassan','hassan@gmail.com');
INSERT INTO service (PrintId, ReturnDate, UsersId, ClientId)  values (111100, '2023-09-19', 1, 1);