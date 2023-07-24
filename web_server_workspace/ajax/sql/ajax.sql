--===========================================
-- ajax
--===========================================

create table celeb (
    no number,
    name varchar2(100) not null,
    profile varchar2(200) default 'default.png', -- 저장된 프로필 사진의 경로
    celeb_type varchar2(50),
    constraints pk_celeb_no primary key(no)
);
select * from celeb order by no;
create sequence seq_celeb_no;

select * from celeb;
        commit;
insert into  celeb values( seq_celeb_no.nextval, '박보검', 'parkBogum.jpg', 'ACTOR');
insert into  celeb values( seq_celeb_no.nextval, '쥴리아 로버츠', 'juliaRoberts.jpg', 'ACTOR');
insert into  celeb values( seq_celeb_no.nextval, '맷 데이먼', 'matt_Damon.jpg', 'ACTOR');
insert into  celeb values( seq_celeb_no.nextval, '차은우', '차은우.png', 'SINGER');
insert into  celeb values( seq_celeb_no.nextval, '춘리', '춘리.png', 'MODEL');
insert into  celeb values( seq_celeb_no.nextval, '카리나', '카리나.png', 'SINGER');
insert into  celeb values( seq_celeb_no.nextval, '정재영', '정재영.jpg', 'ACTOR');
insert into  celeb values( seq_celeb_no.nextval, '아이유', '아이유.jpg', 'ACTOR');
insert into  celeb values( seq_celeb_no.nextval, '오킹', '오킹.jpg', 'ENTERTAINER');
insert into  celeb values( seq_celeb_no.nextval, '김고은', '김고은.jpg', 'ACTOR');
