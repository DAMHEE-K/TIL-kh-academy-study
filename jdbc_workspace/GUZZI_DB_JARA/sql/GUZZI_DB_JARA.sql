CREATE USER shop
IDENTIFIED BY shop1234 
    DEFAULT TABLESPACE users; 

ALTER SESSION SET "_oracle_script" = true;
GRANT connect, resource TO shop; 
ALTER USER shop
    QUOTA UNLIMITED ON users;

desc tbl_orderList;
desc tbl_salesHistory;
desc tbl_product;
desc tbl_salesHistory_3m;



CREATE TABLE tbl_orderList (
	orderNum	varchar2(20)		NOT NULL,
	id	varchar2(20)		NULL,
	itemNum	varchar2(20)		NOT NULL,
	name	varchar2(20)		NULL,
	location	varchar2(100)		NULL,
	amount	number		NULL,
	sale_date date default sysdate
);


-- drop table tbl_salesHistory;

CREATE TABLE tbl_salesHistory (
	itemNum	varchar2(20)		NOT NULL,
	itemName	varchar2(50)		NULL,
	total_amount	number		NULL,
	sale_date	date default sysdate
);
insert into tbl_salesHistory values ('A736','트렌치코트',10,'2023-03-17');
insert into tbl_salesHistory values ('A736','트렌치코트',6,'2023-03-25');
insert into tbl_salesHistory values('A651','티셔츠',10,'2023-03-23');
insert into tbl_salesHistory values ('B065', '모자', 3, '2023-04-05');
insert into tbl_salesHistory values ('C327','카고팬츠',10,'2023-04-20');
insert into tbl_salesHistory values ('C960','와이드팬츠',7,'2023-04-28');
insert into tbl_salesHistory values ('B432','양말',26,'2023-04-21');
insert into tbl_salesHistory values ('B065', '모자', 2, '2023-03-10');
insert into tbl_salesHistory values ('A736','트렌치코트',10,'2023-05-02');
insert into tbl_salesHistory values ('A651','티셔츠',20,'2023-05-10');
insert into tbl_salesHistory values ('B432','양말',15,'2023-04-4');
insert into tbl_salesHistory values ('C327','카고팬츠', 5,'2023-03-27');
insert into tbl_salesHistory values ('A736','트렌치코트',1,'2023-04-18');
insert into tbl_salesHistory values('A651','티셔츠',9,'2023-04-16');
insert into tbl_salesHistory values ('B432','양말',6,'2023-04-25');


select * from tbl_salesHistory;

-- 상품테이블
CREATE TABLE tbl_product (
	itemNum	varchar2(20)		NOT NULL,
	itemName	varchar2(50)		NULL,
	stock	number		NULL,
	price	number		NULL
);
insert into tbl_product values ('A736','트렌치코트',10,73000);
insert into tbl_product values ('A651','티셔츠',3,13000);
insert into tbl_product values ('B065', '모자', 13, 14500);
insert into tbl_product values ('C327','카고팬츠',40,170000);
insert into tbl_product values ('C960','와이드팬츠',70,73900);
insert into tbl_product values ('B432','양말',95,12000);

select * from tbl_product;



--
CREATE TABLE tbl_salesHistory_3m (
	itemNum	varchar2(20)		NOT NULL,
	itemName	varchar2(50)		NULL,
	total_amount	number		NULL,
	total_sales	number		NULL
);


-- 멤버
CREATE TABLE tbl_member (
	id	varchar2(20)		NULL,
	password	varchar2(20)		NULL,
	name	varchar2(20)		NULL,
	location	varchar2(100)		NULL
);

select * from tbl_member;

insert into tbl_member values ('sangwoon_babo', '1111', '박상윤', '서울역 옆 공원 벤치');
insert into tbl_member values('kjh425','1111','김준한','경기도과천');
insert into tbl_member values('ieidadami','damibabo','담비','수지구청역 3번 출구 앞 상가');
insert into tbl_member values('hong_dragon','1111','홍지용','서울시 용산구 한남동');





ALTER TABLE tbl_orderList ADD CONSTRAINT PK_TBL_ORDERLIST PRIMARY KEY (
	orderNum,
	id,
	itemNum
);

ALTER TABLE tbl_salesHistory ADD CONSTRAINT PK_TBL_SALESHISTORY PRIMARY KEY (
	itemNum
);

ALTER TABLE tbl_product ADD CONSTRAINT PK_TBL_PRODUCT PRIMARY KEY (
	itemNum
);

ALTER TABLE tbl_salesHistory_3m ADD CONSTRAINT PK_TBL_SALESHISTORY_3M PRIMARY KEY (
	itemNum
);

ALTER TABLE tbl_member ADD CONSTRAINT PK_TBL_MEMBER PRIMARY KEY (
	id
);

ALTER TABLE tbl_orderList ADD CONSTRAINT FK_tbl_product_TO_tbl_orderList_1 FOREIGN KEY (
	itemNum
)
REFERENCES tbl_product (
	itemNum
);

ALTER TABLE tbl_salesHistory ADD CONSTRAINT FK_tbl_product_TO_tbl_salesHistory_1 FOREIGN KEY (
	itemNum
)
REFERENCES tbl_product (
	itemNum
);

ALTER TABLE tbl_salesHistory_3m ADD CONSTRAINT FK_tbl_product_TO_tbl_salesHistory_3m_1 FOREIGN KEY (
	itemNum
)
REFERENCES tbl_product (
	itemNum
);

ALTER TABLE tbl_orderList ADD CONSTRAINT FK_tbl_orderList_TO_tbl_member_1 FOREIGN KEY (
	id
)
REFERENCES tbl_member (
	id
);



