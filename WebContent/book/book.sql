create table book (
	bookNumber int not null auto_increment primary key,
    bookName varchar(100) not null,
    author varchar(40) not null,
    publishingHouse varchar(40),
	category varchar(25),
    symbol varchar(40),
    status varchar(20)
);

drop table book;

insert into book (bookName, author, publishingHouse, category, symbol, status) values("마케터의 여행법 : 먹거리에서 라이프스타일까지, 파리 사는 마케터의 유럽 마트 관찰기", "김석현", "북스톤", "경제/경영", "G 325.5-김53마", "대여가능");

select * from book;