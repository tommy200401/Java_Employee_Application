 create table employee(
     id int not null auto_increment primary key,
     name varchar(255) not null,
     age int,
     salary int,
     companyId int not null,
     foreign key (companyId) references company(companyId)
 );