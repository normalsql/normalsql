drop table automobiles;

create table automobiles
(
    id int primary key,
    make nvarchar(100) not null,
    model nvarchar(100) not null,
    style nvarchar(100) not null,
    year int not null,
    odometer int not null
);

