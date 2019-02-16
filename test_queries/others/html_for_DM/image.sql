--drop table
drop table img;

--関係の定義
CREATE TABLE img
(
    id      varchar,
  	name    varchar,
	pic		varchar
);

--タプルの挿入
insert into img values ('1', 'p1', 'r1.jpg');
insert into img values ('2', 'p2', 'r2.jpg');
insert into img values ('3', 'p3', 'r3.jpg');
insert into img values ('4', 'p4', 'r4.jpg');
insert into img values ('5', 'p5', 'r5.jpg');
insert into img values ('6', 'p6', 'r6.jpg');
insert into img values ('7', 'p7', 'r7.jpg');

--select
select * from img ;
