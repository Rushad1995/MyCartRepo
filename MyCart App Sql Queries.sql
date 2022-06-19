-- Queries for all the tables which are required for the myCart App
-- Database
create database MyCart;
use MyCart;

 -- Admin Login Table 
create table Adminlogin( AdminId varchar(25), AdminPass varchar(25));
insert into Adminlogin values ('Admin','Admin123');

 -- User Login Table (All authorized users)
create table Userlogin( UserId varchar(25), Password varchar(25));
insert into Userlogin values ('Ajay','Ajay123');
insert into Userlogin values ('Rahul','Rahul123');
insert into Userlogin values ('Seema','Seema123');
insert into Userlogin values ('Rajesh','Rajesh123');
insert into Userlogin values ('Pooja','Pooja123');

-- All product table
create table ProductList( prodId int(4), prodName varchar(25), prodCategory varchar(255),prodPrice varchar(10)) 
engine=InnoDB default charset=utf8 collate=utf8_general_ci;
insert into ProductList values (1,'T Shirt','Clothing','199');
insert into ProductList values (2,'Jeans','Clothing','799');
insert into ProductList values (3,'Shirt','Clothing','499');
insert into ProductList values (4,'Blutooth speaker','Electronics','750');
insert into ProductList values (5,'Wallet','Accessories','350');
insert into ProductList values (6,'Voltas Washing machine','Electronics','11005');

-- User Product Cart table
create table userCart ( prodId int(4), prodName varchar(25), prodCategory varchar(255),prodPrice varchar(10));

-- User Bill Backup table
create table billing_backup ( sr_no int primary key auto_increment , User varchar(80),billing_items varchar(7000) ,toatl_bill double );

