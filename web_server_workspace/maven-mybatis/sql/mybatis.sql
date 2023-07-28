-- @Web계정
create table student(
    id number,
    name varchar2(100) not null,
    tel char(11) not null,
    created_at date default sysdate,
    constraints pk_student_id primary key(id)
);

create sequence seq_student_id;

insert into student values(seq_student_id.nextval, '홍길동', '01012341234', default);
insert into student values(seq_student_id.nextval, '신사임당', '01033333334', default);
insert into student values(seq_student_id.nextval, '강감찬', '01023441233', default);

select * from student order by id desc;
update student set name = '김대원'  where id = 8;

-- 페이징을 위해 행 추가
insert all
into student values (seq_student_id.nextval, name, tel, default)
select * from student;

commit; 

grant select on sh.employee to web;
grant select on sh.department to web;
grant select on sh.job to web;
grant select on sh.sal_grade to web;

-- web 계정 -> sh 계정
-- sh / system 계정에서 web 계정에게 다음 테이블에 대한 권한을 부여해야 함
select * from sh.employee;
select * from sh.job;
select * from sh.department;
select * from sh.sal_grade;

-- synonym 동의어 객체 생성
-- create synonym 권한 부여 필요함
-- 테이블 별칭 객체
create synonym emp for sh.employee;

