-- student 계정 생성 @system
alter session set "_oracle_script" = true;
create user student
identified by student
default tablespace users;

grant connect, resource to student;

alter user student quota unlimited on users;

-- member테이블 생성 @student

create table member (
    id varchar2(20),
    name varchar2(100) not null,
    gender char(1),
    birthday date,
    email varchar2(500) not null,
    point number default 1000,
    reg_date timestamp default systimestamp,
    constraints pk_member_id primary key(id),
    constraints uq_member_emial unique(email),
    constraints ck_member_gender check(gender in ('M','F'))
);

insert into
    member
values(
    'honggd', '홍길동', 'M', '1999-09-09', 'honggd@naver.com', default, default
);
insert into
    member
values(
    'gogd', '고길동', 'M', '1980-02-15', 'gogd@naver.com', default, default
);
insert into
    member
values(
    'sinsa', '신사임당', 'F', '1995-05-05', 'sinsa@naver.com', default, default
);
insert into
    member
values(
    'leess', '이순신', null, null, 'leess@naver.com', default, default
);
insert into
    member
values(
    'qwerty', '쿼티', 'F', null, 'qwerty@naver.com', default, default
);

commit;

--삭제트리거 생성
--resource 롤에 create trigger권한이 있기때문에 별도의 DCL없이 진행할 수 있음.
create or replace trigger trig_delete_member
    before delete on member
    for each row
begin
    insert into 
        member_del
    values(
        :old.id, 
        :old.name, 
        :old.gender, 
        :old.birthday, 
        :old.email, 
        :old.point,
        :old.reg_date, 
        default);
end;
/

--데이터확인
select * from member_del;
select * from member;


-- 회원 주소록 관리
-- 한명의 member는 여러개의 address를 가질 수 있다.
-- member : address = 1 : N

create table address (
    no number,
    member_id varchar2(20),
    address varchar2(4000),
    default_addr number(1) default 1,
    reg_date date default sysdate,
    constraints pk_address_no primary key(no),
    constraints fk_address_member_id foreign key(member_id) references member(id),
    constraints ck_address_default_addr check (default_addr in (0,1))
);

create sequence seq_address_no;

insert into
    address
values(
    seq_address_no.nextval, 'honggd', '서울시 강남구 테헤란로 123', 1, default
);
insert into
    address
values(
    seq_address_no.nextval, 'honggd', '서울시 서초구 방배동 7777', 0, default
);
insert into
    address
values(
    seq_address_no.nextval, 'gogd', '경기도 구리시 소동 1000', 1, default
);
insert into
    address
values(
    seq_address_no.nextval, 'sinsa', '경상남도 함양시 안의면 1234', 1, default
);
insert into
    address
values(
    seq_address_no.nextval, 'qwerty', '서울시 종로구 풍동 123', 1, default
);

select * from address;

-- 회원 | 주소 정보 조회

select
    *
from
    member m left join address a
      on m.id = a.member_id;
      
commit;