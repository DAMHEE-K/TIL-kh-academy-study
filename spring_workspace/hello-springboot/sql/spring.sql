--==============================
-- 관리자계정 - spring계정 생성
--==============================
alter session set "_oracle_script" = true;

create user spring
identified by spring
default tablespace users;

alter user spring quota unlimited on users;

grant connect, resource to spring;

--================================
-- SPRING 계정
--================================
create table dev (
    id number,
    name varchar2(50) not null,
    career number not null,
    email varchar2(200) not null,
    gender char(1),
    lang varchar2(100) not null,
    created_at date default sysdate,
    constraints pk_dev_id primary key(id),
    constraints ck_dev_gender check(gender in ('M', 'F'))
);

create sequence seq_dev_id;

select * from dev;

-- 
create table member (
    member_id varchar2(50),
    password varchar2(300) not null,
    name varchar2(256) not null,
    birthday date,
    email varchar2(300),
    created_at date default sysdate,
    constraints pk_member_id primary key(member_id)
);

INSERT INTO SPRING.MEMBER 
    VALUES ('abcde','1234','아무개',to_date('88-01-25','rr-mm-dd'),'abcde@naver.com',DEFAULT);
    INSERT INTO SPRING.MEMBER 
    VALUES ('qwerty','1234','김말년',to_date('78-02-25','rr-mm-dd'),'qwerty@naver.com',DEFAULT);
    INSERT INTO SPRING.MEMBER 
    VALUES ('admin','1234','관리자',to_date('90-12-25','rr-mm-dd'),'admin@naver.com',DEFAULT);
    COMMIT;

delete from member where member_id = 'honggd';
commit;
select * from member;

update member set password= '$2a$10$WnQvobAo7HuY3tDBcMspy.CVhN6V277vfSpbGGOik.UB7pRPOsC9a'
where member_id = 'qwerty';

create table authority(
    member_id varchar2(20),
    auth varchar2(50),
    constraints pk_authority primary key(member_id, auth),
    constraints fk_authority_member_id foreign key(member_id) references member(member_id) on delete cascade
);

insert into authority values ('abcde', 'ROLE_USER');
insert into authority values ('qwerty', 'ROLE_USER');
insert into authority values ('admin', 'ROLE_USER');
insert into authority values ('admin', 'ROLE_ADMIN');
insert into authority values ('honggd', 'ROLE_USER');

select * from member where member_id = 'admin';
select * from authority where member_id = 'admin';

-- Todo 할일관리
create table todo (
    id number,
    member_id varchar2(20),
    todo varchar2(4000),
    created_at date default sysdate,
    completed_at date,
    constraints pk_todo_id primary key(id),
    constraints fk_todo_member_id foreign key(member_id) references member(member_id) on delete cascade
);

create sequence seq_todo_id;

insert into todo values (seq_todo_id.nextval, 'honggd', '형광등 교체하기', default, null);
insert into todo values (seq_todo_id.nextval, 'honggd', '디자인패턴 공부하기', default, null);
insert into todo values (seq_todo_id.nextval, 'honggd', '장보기', default, sysdate);
insert into todo values (seq_todo_id.nextval, 'qwerty', '빨래하기', default, null);
insert into todo values (seq_todo_id.nextval, 'qwerty', '조깅', default, null);
insert into todo values (seq_todo_id.nextval, 'honggd', '키보드 구매하기', default, sysdate);

commit;
select * from todo;
-- update todo set completed_at = ? where member_id =?


-- security remember me
create table persistent_logins (
    username varchar(64) not null,
    series varchar(64) primary key, -- pk
    token varchar(64) not null,
    last_used timestamp not null
);

select * from persistent_logins;