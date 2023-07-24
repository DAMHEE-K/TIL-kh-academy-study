CREATE USER shop
IDENTIFIED BY shop1234 
    DEFAULT TABLESPACE users; 
ALTER SESSION SET "_oracle_script" = true;
GRANT connect, resource TO shop; 
ALTER USER shop
    QUOTA UNLIMITED ON users;
/*    
 drop table tbl_member;
 drop table tbl_product cascade CONSTRAINTS;
 drop table tbl_salesHistory;
 delete from tbl_orderList where orderNum = 'jaza23-05-07';
*/    
-- delete from tbl_member where name = '' constraints cascade;
select * from tbl_member;
select * from tbl_product;
select * from tbl_salesHistory;
select * from tbl_orderList;
delete from tbl_orderList where id = 'dogkim';
commit;
-- 총 매출액 조회 커리
select 
    p.itemNum,
    p.itemName,
    sum(price)
from 
    tbl_salesHistory s join tbl_product p on s.itemNum = p.itemNum
group by
    p.itemNum,p.itemName;

select * from tbl_salesHistory;

delete from  tbl_orderList where id = 'kim';
-- 총 판매액 조회 쿼리
select p.itemNum, p.itemName, sum(price) from tbl_salesHistory s join tbl_product p on s.itemNum = p.itemNum group by p.itemNum,p.itemName;

-- 지난달
select p.itemNum, p.itemName, sum(price) from tbl_salesHistory s join tbl_product p on s.itemNum = p.itemNum where to_char(sale_date, 'yyyy-mm') = to_char(add_months(sysdate, -1), 'yyyy-mm') group by p.itemNum,p.itemName;

select * from tbl_orderList;

desc tbl_orderList;
desc tbl_salesHistory;
desc tbl_product;
desc tbl_salesHistory_3m;

update tbl_member set money = 0 where id = 'aaa';
commit;
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

insert into tbl_orderList valus(?,?,?,?,?,?,default);

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
-- 
CREATE TABLE tbl_member (
	id	varchar2(20)		NULL,
	password	varchar2(20)		NULL,
	name	varchar2(20)		NULL,
	location	varchar2(100)		NULL,
    money number default 0
);

select * from tbl_member;

insert into tbl_member values ('sangwoon_babo', '1111', '박상윤', '서울역 옆 공원 벤치',default);
insert into tbl_member values('kjh425','1111','김준한','경기도과천',default);
insert into tbl_member values('ieidadami','damibabo','담비','수지구청역 3번 출구 앞 상가',default);
insert into tbl_member values('hong_dragon','1111','홍지용','서울시 용산구 한남동',default);

-- 캐시충전
update tbl_member set money = 0 where name = '홍지용';

update tbl_member set location = ? where = ?

select * from tbl_member;


ALTER TABLE tbl_orderList drop CONSTRAINT PK_TBL_ORDERLIST;
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

alter table tbl_orderList drop constraints FK_tbl_product_TO_tbl_orderList_1 cascade;

ALTER TABLE tbl_orderList ADD CONSTRAINT FK_tbl_product_TO_tbl_orderList_1 FOREIGN KEY (
	itemNum
)
REFERENCES tbl_product (
	itemNum
);
commit;
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



ALTER TABLE tbl_orderList drop CONSTRAINT FK_tbl_orderList_TO_tbl_member_1 FOREIGN KEY (
	id
)
REFERENCES tbl_member (
	id
);

ALTER TABLE tbl_orderList ADD CONSTRAINT FK_tbl_orderList_TO_tbl_member_1 FOREIGN KEY (
	id
)
REFERENCES tbl_member (
	id
);

--DROP TRIGGER TRIG_PRODUCT_STOCK;
-- 주문리스트에 들어가면 재고에 삭제되는 트리거
CREATE OR REPLACE TRIGGER trg_update_stock
    AFTER INSERT ON tbl_orderList 
    FOR EACH ROW
BEGIN    
    UPDATE tbl_product p
    SET
        stock = stock - 1
    WHERE
        itemnum = :new.itemnum;
    UPDATE tbl_member 
    SET
        money = money - (select price from tbl_product where itemnum = :new.itemnum)
    WHERE
        id = :new.id;
END;
/

commit;



--

update tbl_member set money = '0';
commit;
select * from tbl_member;
select * from tbl_product;
--A651
delete from tbl_orderList where name = '김준한';
insert into tbl_orderList values('jaza23-05-07','kjh425','A651','김준한','강남구 역삼동 호산빌딩',1,sysdate);
select * from tbl_product;
select * from tbl_orderList where name = '김준한';