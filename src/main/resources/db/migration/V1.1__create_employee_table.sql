 create table employee(
     id int not null auto_increment primary key,
     name varchar(255) not null,
     age int,
     salary int,
     company_id int not null,
     foreign key (company_id) references company(id)
 );