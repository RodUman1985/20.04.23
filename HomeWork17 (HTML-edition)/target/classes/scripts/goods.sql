create table goods
(
    id     INT AUTO_INCREMENT,
    tittle VARCHAR(50),
    price  DOUBLE
);
create table users
(
    id       int AUTO_INCREMENT,
    userName VARCHAR(50),
    password VARCHAR(50)
);

create table order_good
(
    id      INT AUTO_INCREMENT,
    order_id  INT,
     good_id INT
 );
create table orders
(
    id      INT AUTO_INCREMENT,
    user_id  INT,
    total_prise DOUBLE
);

insert into goods (tittle, price)
values ('book', 50);
insert into goods (tittle, price)
values ('game', 20);
insert into goods (tittle, price)
values ('pen', 40);
insert into goods (tittle, price)
values ('phone', 50);





