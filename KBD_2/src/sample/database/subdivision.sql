CREATE TABLE subdivision
(
    number     INT PRIMARY KEY,
    full_name  VARCHAR(30),
    short_name VARCHAR(10)
);

insert into subdivision (number, full_name, short_name)
values (1,'Klevcevich Alex', 'Alex');