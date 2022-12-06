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
insert into book(`id`,`name`,`publisher_id`,`deleted`,`status`) values(1,'bookName',1,false,100);
insert into book(`id`,`name`,`publisher_id`,`deleted`,`status`) values(2,'bookName2',1,false,200);
insert into book(`id`,`name`,`publisher_id`,`deleted`,`status`) values(3,'bookName3',1,true,100);
--insert into book(`id`,`name`,`publisher_id`,`deleted`,`created_at`,`updated_at`) values(1,'bookName',1,false,now(),now());
--insert into book(`id`,`name`,`publisher_id`,`deleted`,`created_at`,`updated_at`) values(2,'nameBook',1,false,now(),now());
--insert into book(`id`,`name`,`publisher_id`,`deleted`,`created_at`,`updated_at`) values(3,'name3',1,true,now(),now());
insert into review(`id`,`title`,`content`,`score`,`user_id`,`book_id`) values(1,'내 인생을 바꾼 책','최고에여',5.0,1,1);
insert into review(`id`,`title`,`content`,`score`,`user_id`,`book_id`) values(2,'진도가 빨라여','걍 그럼',2.0,2,2);

insert into comment(`id`,`comment`,`review_id`) values (1,'저도여',1);
insert into comment(`id`,`comment`,`review_id`) values (2,'아닌데?',1);
insert into comment(`id`,`comment`,`review_id`) values (3,'ㅇㅇㅂㄹ',2);