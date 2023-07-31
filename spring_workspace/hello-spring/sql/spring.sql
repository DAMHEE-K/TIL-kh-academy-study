--================================
-- 관리자 계정 - spring 계정 생성
--================================
alter session set "_oracle_script" = true;

create user spring
identified by spring
default tablespace users;

alter user spring quota unlimited on users;

grant connect, resource to spring;

--================================
-- SPRING 계정
--================================
create table dev(
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
