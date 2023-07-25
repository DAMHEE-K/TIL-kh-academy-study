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
commit;