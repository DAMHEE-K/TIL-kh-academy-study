-- menu@spring
select * from menu;
select * from user_sequences;

-- truncate table menu;

Insert into SPRING.MENU (ID,RESTAURANT,NAME,PRICE,TYPE,TASTE, CREATED_AT) values (seq_menu_id.nextval,'두리순대국','순대국',7000,'kr','mild', systimestamp);
Insert into SPRING.MENU (ID,RESTAURANT,NAME,PRICE,TYPE,TASTE, CREATED_AT) values (seq_menu_id.nextval,'두리순대국','순대국',7000,'kr','hot', systimestamp);
Insert into SPRING.MENU (ID,RESTAURANT,NAME,PRICE,TYPE,TASTE, CREATED_AT) values (seq_menu_id.nextval,'장터','뚝배기 김치찌게',7000,'kr','hot', systimestamp);
Insert into SPRING.MENU (ID,RESTAURANT,NAME,PRICE,TYPE,TASTE, CREATED_AT) values (seq_menu_id.nextval,'만리향','간짜장',5000,'ch','mild', systimestamp);
Insert into SPRING.MENU (ID,RESTAURANT,NAME,PRICE,TYPE,TASTE, CREATED_AT) values (seq_menu_id.nextval,'만리향','짬뽕',6000,'ch','hot', systimestamp);
Insert into SPRING.MENU (ID,RESTAURANT,NAME,PRICE,TYPE,TASTE, CREATED_AT) values (seq_menu_id.nextval,'짬뽕지존','짬뽕',9000,'ch','mild', systimestamp);
Insert into SPRING.MENU (ID,RESTAURANT,NAME,PRICE,TYPE,TASTE, CREATED_AT) values (seq_menu_id.nextval,'김남완초밥집','완초밥',13000,'jp','mild', systimestamp);
Insert into SPRING.MENU (ID,RESTAURANT,NAME,PRICE,TYPE,TASTE, CREATED_AT) values (seq_menu_id.nextval,'김남완초밥집','런치초밥',10000,'jp','mild', systimestamp);
Insert into SPRING.MENU (ID,RESTAURANT,NAME,PRICE,TYPE,TASTE, CREATED_AT) values (seq_menu_id.nextval,'김남완초밥집','참치도로초밥',33000,'jp','mild', systimestamp);
Insert into SPRING.MENU (ID,RESTAURANT,NAME,PRICE,TYPE,TASTE, CREATED_AT) values (seq_menu_id.nextval,'진가와','자루소면',8000,'jp','mild', systimestamp);
Insert into SPRING.MENU (ID,RESTAURANT,NAME,PRICE,TYPE,TASTE, CREATED_AT) values (seq_menu_id.nextval,'진가와','자루소바',9000,'jp','mild', systimestamp);
Insert into SPRING.MENU (ID,RESTAURANT,NAME,PRICE,TYPE,TASTE, CREATED_AT) values (seq_menu_id.nextval,'백운봉','막국수',9000,'kr','hot', systimestamp);
Insert into SPRING.MENU (ID,RESTAURANT,NAME,PRICE,TYPE,TASTE, CREATED_AT) values (seq_menu_id.nextval,'대우부대찌게','부대지게',10000,'kr','hot', systimestamp);
Insert into SPRING.MENU (ID,RESTAURANT,NAME,PRICE,TYPE,TASTE, CREATED_AT) values (seq_menu_id.nextval,'봉된장','열무비빔밥+우렁된장',7000,'kr','hot', systimestamp);
Insert into SPRING.MENU (ID,RESTAURANT,NAME,PRICE,TYPE,TASTE, CREATED_AT) values (seq_menu_id.nextval,'대우부대찌게','부대찌게',10000,'kr','hot', systimestamp);
Insert into SPRING.MENU (ID,RESTAURANT,NAME,PRICE,TYPE,TASTE, CREATED_AT) values (seq_menu_id.nextval,'산들애','딸기',500,'kr','hot', systimestamp);
Insert into SPRING.MENU (ID,RESTAURANT,NAME,PRICE,TYPE,TASTE, CREATED_AT) values (seq_menu_id.nextval,'대우부대찌게','청국장',13000,'kr','mild', systimestamp);
Insert into SPRING.MENU (ID,RESTAURANT,NAME,PRICE,TYPE,TASTE, CREATED_AT) values (seq_menu_id.nextval,'스타동','사케동',8400,'jp','mild', systimestamp);
Insert into SPRING.MENU (ID,RESTAURANT,NAME,PRICE,TYPE,TASTE, CREATED_AT) values (seq_menu_id.nextval,'진씨화로','돌솥비빔밥',7000,'kr','mild', systimestamp);


-- 전체 조회
select * from menu;
-- 한건 조회
select * from menu where id = 1;
-- 한식만 조회
select * from menu where type = 'kr';
-- 중식만 조회
select * from menu where type = 'ch';
-- 일식만 조회
select * from menu where type = 'jp';
-- 순한맛만 조회
select * from menu where taste = 'mild';
-- 매운맛만 조회
select * from menu where taste = 'hot';


-- 메뉴 등록
insert into menu (id, restaurant, name, price, type, taste, created_at)
values (seq_menu_id.nextval, '은희네 해장국', '한치물회', 15000, 'kr', 'hot');

-- 메뉴 수정 (name, restaurant, price, type, taste)
update menu
set name = '한치얼음물회', restaurant = '사카모토네 해장국', price = 20000, type = 'jp', type = 'mild'
where id = 21;
-- 메뉴 삭제
delete from menu where id = 123;