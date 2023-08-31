create database library;
USE library;

create table user
(
Id int primary key auto_increment,
Name varchar(15) not null,
email varchar(20) not null unique,
password varchar(15) not null
);

create table book
(
Id int primary key auto_increment,
ISBN int,
Name varchar(25) not null,
Year int not null,
Status varchar(20) default 'Available',
Archived bool default false
);

create table service (
Id int key auto_increment,
BookId int,
BorrowDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
ReturnDate Date,
foreign key (bookId) references book(Id)
);

create table client(
Id int key auto_increment,
Name varchar(30) not null,
Email varchar(30) not null unique
);

insert into buyer (buyerName,email,password,location) values ('David','david@gmail.com','david@123','Sydney');
insert into seller (sellerName,email,password,location) values ('Sujit','sujit@gmail.com','sujit@123','Delhi');


create table category
(
categoryId int primary key auto_increment,
categoryName varchar(20)
);
alter table category auto_increment=1001;
insert into category (categoryName) values ("Bikes");
insert into category (categoryName) values ("Cars");
insert into category (categoryName) values ("Watches");
insert into category (categoryName) values ("Shoes");
insert into category (categoryName) values ("Jewellery");
insert into category (categoryName) values ("Paintings");
insert into category (categoryName) values ("Alcohols");
insert into category (categoryName) values ("Swords");




create table products
(
productId int primary key auto_increment,
productName varchar(20) not null,
sellerId int,
categoryId int,
price int,
status varchar(20) default 'available',
buyerId int,
date Date,
foreign key (sellerId) references seller(sellerId),
foreign key (categoryId) references category(categoryId),
foreign key (buyerId) references buyer(buyerId)
);

alter table products add buyerId int;
alter table products add Date date;
alter table products add foreign key (buyerId) references buyer(buyerId);
insert into products (productName,sellerId,categoryId,price,Date) values('Katana',1,null,30000,'2022-12-15');


select p.productId,p.productName,s.sellerName,c.categoryName,p.price,p.status from products p
Inner Join category c Inner Join seller s
On p.categoryId=c.categoryId and p.sellerId=s.sellerId
where status='available' and c.categoryName='Swords';



select b.buyerId,b.buyerName,b.email,p.productName,c.categoryName,s.sellerName,p.price from products p
Inner Join category c Inner Join seller s Inner Join buyer b
On p.categoryId=c.categoryId and p.sellerId=s.sellerId and p.buyerId=b.buyerId
where p.date='2022-12-18';

select * from products where Date='2022-12-15' and categoryId=Null;