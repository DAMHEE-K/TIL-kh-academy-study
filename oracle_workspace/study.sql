-- @실습문제 - CREATE
-- 테이블을 적절히 생성하고, 테이블, 컬럼주석을 추가하세요.

/*
1. 첫번째 테이블 명 : EX_MEMBER
* MEMBER_CODE(NUMBER) - 기본키                        -- 회원전용코드 
* MEMBER_ID (varchar2(20) ) - 중복금지                    -- 회원 아이디
* MEMBER_PWD (char(20)) - NULL 값 허용금지                    -- 회원 비밀번호
* MEMBER_NAME(varchar2(30))                             -- 회원 이름
* MEMBER_ADDR (varchar2(100)) - NULL값 허용금지                    -- 회원 거주지
* GENDER (char(3)) - '남' 혹은 '여'로만 입력 가능                -- 성별
* PHONE(char(11)) - NULL 값 허용금지                     -- 회원 연락처
*/
create table EX_MEMBER (
    MEMBER_CODE number,
    MEMBER_ID varchar2(20),
    MEMBER_PWD char(20) not null,
    MEMBER_NAME varchar2(30),
    MEMBER_ADDR varchar2(100) not null,
    GENDER char(3),
    PHONE char(11) not null,
    constraints pk_ex_member_code primary key(MEMBER_CODE),
    constraints ck_ex_member_gender check(GENDER in('여','남')),
    constraints uq_ex_member_member_id unique(MEMBER_ID)
);

comment on table ex_member is '회원관리테이블';
comment on column ex_member.member_code is '회원전용코드';
comment on column ex_member.member_id is '회원 아이디';
comment on column ex_member.member_pwd is '회원 비밀번호';
comment on column ex_member.member_code is '회원 비밀번호';
comment on column ex_member.member_name is '회원 이름';
comment on column ex_member.member_addr is '회원 거주지';
comment on column ex_member.gender is '전화번호';
comment on column ex_member.phone is '회원 연락처';

-- 2. EX_MEMBER_NICKNAME 테이블을 생성하자. (제약조건 이름 지정할것)
-- (참조키를 다시 기본키로 사용할 것.)
/*
* MEMBER_CODE(NUMBER) - 외래키(EX_MEMBER의 기본키를 참조), 중복금지        -- 회원전용코드
* MEMBER_NICKNAME(varchar2(100)) - 필수                         -- 회원 이름
*/
create table EX_MEMBER_NICKNAME (
    MEMBER_CODE number not null,
    MEMBER__NICKNAME varchar2(100),
    constraints fk_EX_MEMBER_NICKNAME_CODE foreign key(MEMBER_CODE) references EX_MEMBER(MEMBER_CODE)
);

select
    ucc.column_name,
    uc.*
from
    user_constraints uc join user_cons_columns ucc
    on uc.constraint_name = ucc.constraint_name
where
    uc.table_name = 'EX_MEMBER_NICKNAME'; 
    

-- @실습문제 - ALTER
-- 다음테이블을 우선 생성하고, 각각 제약조건을 추가하기
/*
1. tbl_parent 테이블
  * id : number
  * name : varchar2(20)
2. tbl_child 테이블
  * parent_id : number

tbl_parent 제약조건 추가 
    * id컬럼 pk 지정, 제약조건명 : pk_tbl_parent_id
    * name컬럼 not null 제약 조건 추가
tbl_child 제약조건 추가 
    * parent_id컬럼 fk 지정, 제약조건명: fk_tbl_child_parent_id, tbl_parent의 id컬럼 참조.
*/

create table tbl_parent (
    id number,
    name varchar2(20) not null,
    constraints pk_tbl_parent_id primary key(id)
);


--기본 테이블 생성

CREATE TABLE tbl_parent (
    id number,
    name varchar(20)
  );
  -- drop table tbl_parent;
  desc tbl_parent;

CREATE TABLE tbl_child (
    parent_id number,
    constraints fk_tbl_child_parent_id foreign key (parent_id) references tbl_parent (id)
  );
  -- drop table tbl_child;


-- @실습문제  trigger
-- 1. EMPLOYEE 테이블에서 퇴사자를 별도의 테이블 TBL_EMP_QUIT에서 관리하려고 한다.
-- 다음과 같이 TBL_EMP_JOIN, TBL_EMP_QUIT테이블을 생성하고, TBL_EMP_JOIN에서 DELETE시 자동으로 퇴사자 데이터가 TBL_EMP_QUIT에 INSERT되도록 
-- 트리거를 생성하라.
-- TBL_EMP_JOIN 테이블 생성 : EX_EMPLOYEE테이블에서 QUIT_DATE, QUIT_YN 컬럼제외하고 복사

CREATE OR REPLACE TRIGGER tbl_emp_quit BEFORE
    DELETE ON tbl_emp_join
    FOR EACH ROW
BEGIN
    INSERT INTO tbl_emp_quit VALUES (
        :old.emp_id,
        :old.emp_name,
        :old.emp_no,
        :old.email,
        :old.phone,
        :old.dept_code,
        :old.job_code,
        :old.sal_level,
        :old.salary,
        :old.bonus,
        :old.manager_id,
        :old.hire_date,
        sysdate
    );
END;
/
    





CREATE SEQUENCE seq_tbl_emp_quit_no;

CREATE TABLE 
    TBL_EMP_JOIN
AS
SELECT 
    EMP_ID, EMP_NAME, EMP_NO, EMAIL, PHONE, DEPT_CODE, JOB_CODE, SAL_LEVEL, SALARY, BONUS, MANAGER_ID, HIRE_DATE
FROM 
    EMPLOYEE_EX
WHERE 
    QUIT_YN = 'N';

    SELECT * FROM TBL_EMP_JOIN;
delete from TBL_EMP_JOIN where emp_id = '208';    

--TBL_EMP_QUIT : EX_EMPLOYEE테이블에서 QUIT_YN 컬럼제외하고 복사

    CREATE TABLE TBL_EMP_QUIT
    AS
    SELECT 
        EMP_ID, EMP_NAME, EMP_NO, EMAIL, PHONE, DEPT_CODE, JOB_CODE, SAL_LEVEL, SALARY, BONUS, MANAGER_ID, HIRE_DATE, QUIT_DATE
    FROM 
        EMPLOYEE
    WHERE 
        QUIT_YN = 'Y';

    SELECT * FROM TBL_EMP_QUIT;
  
  
  

SELECT
    emp_name,
    emp_no,
    dept_code,
    salary
FROM
    employee
WHERE
    ( dept_code = 'D9'OR dept_code = 'D6' )
    AND salary >= 3000000
    AND email LIKE '___#_%' ESCAPE '#'
    AND bonus IS NOT NULL
    AND substr(emp_no, 8, 1) IN ( '1', '3' );
  

-- OR에 DEPT_CODE ()
-- SALARY >=
-- 성별
-- ___#_% 로 작성
-- BONUS IS NOT NULL (보너스가 있음)

SELECT * FROM EMPLOYEE WHERE BONUS is NULL AND MANAGER_ID is not NULL;


-- 아래의 SQL구문은 부서 별 평균 월급이 2800000을 초과하는 부서를 조회한 것이다.
SELECT 
    DEPT, 
    SUM(SALARY) 합계, 
    FLOOR(AVG(SALARY)) 평균, 
    COUNT(*) 인원수

FROM EMP

GROUP BY DEPT -- having

HAVING SALARY > 2800000

ORDER BY DEPT ASC;



SELECT 
    DEPT_code, 
    SUM(SALARY) 합계, 
    FLOOR(AVG(SALARY)) 평균, 
    COUNT(*) 인원수
FROM 
    employee
GROUP BY 
    DEPT_code -- having
HAVING 
    FLOOR(AVG(SALARY)) > 2800000
ORDER BY DEPT_code ASC;


SELECT 
    ROWNUM, 
    emp_name, 
    salary
FROM 
    (select * from employee order by salary desc)
WHERE 
ROWNUM <= 3;



SELECT 
    ROWNUM, 
    EMPNAME, 
    SAL
FROM 
    (SELECT * FROM EMP ORDER BY SAL DESC)
WHERE 
    ROWNUM <= 3;


---------------------



CREATE TABLE DEPARTMENT1(
    DEPTCODE NUMBER PRIMARY KEY,
    DEPTNAME NVARCHAR2(10) NOT NULL
);



CREATE TABLE EMPLOYEE1(
    EMPNO NUMBER PRIMARY KEY,
    EMPNAME VARCHAR2(10) NOT NULL,
    DEPTNO NUMBER REFERENCES DEPARTMENT1(DEPTCODE)
);

SELECT 
    emp_no,
    emp_name
FROM 
    EMPLOYEE E
JOIN 
DEPARTMENT D ON E.dept_code = D.dept_id;