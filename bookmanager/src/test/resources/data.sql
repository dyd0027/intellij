-- primaryKey에 다음값을 넣는다는 뜻
--call next value for hibernate_sequence;
insert into users(id,name,email,created_at,updated_at) values(1,'yh','self0027@naver.com',now(),now());

--call next value for hibernate_sequence;
insert into users(id,name,email,created_at,updated_at) values(2,'yong','yong0027@naver.com',now(),now());

--call next value for hibernate_sequence;
insert into users(id,name,email,created_at,updated_at) values(3,'gwon','gwon0027@gmail.com',now(),now());

--call next value for hibernate_sequence;
insert into users(id,name,email,created_at,updated_at) values(4,'hwi','hwi0027@gmail.com',now(),now());

--call next value for hibernate_sequence;
insert into users(id,name,email,created_at,updated_at) values(5,'yh','yh0027@another.com',now(),now());

insert into publisher(`id`,`name`) values(1,'용휘회사');
insert into book(`id`,`name`,`publisher_id`,`deleted`) values(1,'bookName',1,false);
insert into book(`id`,`name`,`publisher_id`,`deleted`) values(2,'nameBook',1,false);
insert into book(`id`,`name`,`publisher_id`,`deleted`) values(3,'name3',1,true);
