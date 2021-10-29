create table employee(
    id int not null auto_increment primary key,
    name varchar(255) not null,
    age int,
    salary double,
    company_id int,
    foreign key (company_id) references company (company_id)
)