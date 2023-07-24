-- .sql 한 줄 주석
/*
.sql 여러 줄 주석
*/

-- sql 실행은 코드 작성 후 ctrl + Enter로 가능하다. 서버로  sql문을 전송해서 응답결과를 출력한다.
-- 어떤 계정으로 접속해서 어떤 쿼리를 실행하는지가 중요하다. 같은 쿼리라도 접속한 계정에 따라 결과가 달라질 수 있다.
-- 쿼리는 저장된 데이터를 제외하고는, 대소문자를 구분하지 않는다.
-- 이클립스, 다른 개발툴과 단축키가 다른 경우가 있으므로 주의해야 한다.

----------------------------------------------------------------------------------------
-- sh 계정 생성 @system
----------------------------------------------------------------------------------------
-- oracle을 설치하면 자동으로 sys(슈퍼관리자), system(일반관리자) 관리자 계정이 만들어진다.
-- sys는 일반관리자 기능 + db 생성권한을 가지고 있다. (특별한 경우가 아니면 사용 금지)
-- system은 계정 생성, 타 계정 관리, 기타 db 객체를 관리할 수 있음

CREATE USER sh -- 계정명

IDENTIFIED BY sh -- 비밀번호(대소문자 구분)
    DEFAULT TABLESPACE users; -- tablespace는 실제 데이터가 저장된 db 내의 공간 이름. system / users / ...

-- 65096. 00000 -  "invalid common user or role name"
-- 12c  이후에 일반사용자는 c## 접두사 사용을 강제하고 있다. c##sh
-- 우회설정
ALTER SESSION SET "_oracle_script" = true; -- session은 현재 계정의 접속을 가리킨다.

-- 상태: 실패 -테스트 실패: ORA-01045: 사용자 SH는 CREATE SESSION 권한을 가지고있지 않음; 로그온이 거절되었습니다
-- 계정이 존재하도 접속 권한(create session)이 없다면 db에 접근할 수 없다.
-- grant create session,create table to sh;
GRANT connect, resource TO sh; -- connect롤 (권한 묶음)

ALTER USER sh
    QUOTA UNLIMITED ON users; -- users tablespace 데이터 저장할 용량을 무한으로 설정

-- 테이블 생성 시도 @sh
-- 01031.00000 - "insufficient privileges" 권한이 불충분합니다.
CREATE TABLE abc (
    id NUMBER
);

-- drop table abc; -- 테이블 삭제 (삭제 명령어는 주석으로 처리해두고 필요시 사용해야 함)

--===================================================--
-- 전사 테이블 설정 @sh
--===================================================--
-- SQL (Structured Query Language) 구조화 질의 언어
-- DB server의 데이터 및 객체에 대한 조회/생성/수정/삭제 처리하는 언어

-- sh 계정이 가진 모든 테이블 조회
-- ( * : ALL )
SELECT
    *
FROM
    tab; -- tab 테이블에서 * 모든 컬럼을 조회
SELECT
    *
FROM
    employee; -- 사원정보
SELECT
    *
FROM
    department; -- 부서정보
SELECT
    *
FROM
    job; -- 직급정보
SELECT
    *
FROM
    location; -- 지역정보
SELECT
    *
FROM
    nation; -- 국가정보
SELECT
    *
FROM
    sal_grade; -- 급여등급

-- table/entity/relation 테이블. 데이터를 보관하는 db 객체. 행과 열로 구분되어 있다.
-- column/attribute/field 열. 테이블의 구조. 각 컬럼마다 자료형을 지정
-- row/tuple/record 행. 테이블 하위에 보관하는 데이터의 단위. 사원 한명 정보는 employee의 한 행이 된다.
-- domain 하나의 컬럼이 취할 수 있는 원자값의 범위. 성별 컬럼의 도메인은 남/여 2개의 값이 도메인 범위가 된다.

--===================================================--
-- DATA TYPE
--===================================================--
-- 1. 문자형
-- 2. 숫자형
-- 3. 날짜형
------------------------------------
-- 1. 문자형
------------------------------------
-- char 고정형 문자 타입. 최대 2000byte
-- varchar2 가변형 문자타입. 최대 4000byte
-- nchar 유니코드 고정형 문자타입. 최대 2000byte
-- nvarchar2 유니코드 가변형 문자타입. 최대 4000byte
-- long 가변형 문자타입. 최대 2GB (deprecated)
-- clob 가변형 문자타입. 최대 4GB

-- char(10) 고정형 10byte. 
--      'korea' 저장 시, 실제 값은 5byte지만, 저장된 데이터는 10byte.
--      '안녕' 저장 시, 실제 값은 6byte지만, 저장된 데이터는 10byte. 
--      '잘가잘가' 저장 시, 실제 값이 12byte라서 저장 실패!

-- varchar2(10) 가변형 10byte.
--      'korea' 저장 시, 실제 값은 5byte이고, 저장된 데이터는 5byte.
--      '안녕' 저장 시, 실제 값은 6byte이고, 저장된 데이터는 6byte. 
--      '잘가잘가' 저장 시, 실제 값이 12byte라서 저장 실패!

-- 한글 저장 시 xe버전은 글자당 3byte지만, 상업용 버전은 글자당 2byte씩 처리
-- sql은 대소문자를 구분하지 않으므로, tblDatatypeChar가 아닌 tbl_datatype_char와 같이 snake casing 처리한다
CREATE TABLE tbl_datatype_char (
    a CHAR(10),
    b VARCHAR2(10)
);

SELECT
    *
FROM
    tbl_datatype_char;

SELECT
    a,
    b
FROM
    tbl_datatype_char;

SELECT
    a,
    b,
    lengthb(a),
    lengthb(b)
FROM
    tbl_datatype_char;

-- 데이터 추가(행 단위)
INSERT INTO tbl_datatype_char VALUES (
    'hello',
    'hello'
);

INSERT INTO tbl_datatype_char VALUES (
    '안녕',
    '안녕'
);

INSERT INTO tbl_datatype_char VALUES (
    '대한민국',
    'hello'
); -- ORA-12899: "SH"."TBL_DATATYPE_CHAR"."A" 열에 대한 값이 너무 큼(실제: 12, 최대값: 10)

------------------------------------
-- 2. 숫자형
------------------------------------
-- 정수/실수를 구분하지 않는다
-- number[(precision, scale)]
-- precision : 표현 가능한 전체 자리수 (1 ~ 38)
-- scale : 소수점 이하 자리수(-84 ~ 127)
-- 1234.567 값에 대한 처리
-- number 1234.567 저장
-- number(10, 0) 12345 저장 (반올림 처리)
-- number(10, 1) 1234.6 저장
-- number(10, -2) 1200 저장

CREATE TABLE tbl_datatype_number (
    a NUMBER,
    b NUMBER(10),
    c NUMBER(10, 1),
    d NUMBER(10, - 2)
);

INSERT INTO tbl_datatype_number VALUES (
    1234.567,
    1234.567,
    1234.567,
    1234.567
);

SELECT
    *
FROM
    tbl_datatype_number;

------------------------------------
-- 3. 날짜형
------------------------------------
-- date 년/월/일/시/분/초
-- timestamp 년/월/일/시/분/초+밀리초 (지역대 설정 가능)

SELECT
    sysdate,
    to_char(
        sysdate, 'yyyy-mm-dd hh:mi:ss'
    ),
    systimestamp
FROM
    dual; -- dual 1행짜리 가상 테이블
CREATE TABLE tbl_datatype_date (
    a DATE,
    b TIMESTAMP
);

-- DML : db 테이블에 직접 추가가 아니라, memory 상의 작업이 우선 발생한다.
-- commit 실제 db 적용 | rollback 마지막 commit 시점 이후 작업이 모두 취소됨
INSERT INTO tbl_datatype_date VALUES (
    sysdate,
    systimestamp
);

SELECT
    to_char(
        a, 'yy/mm/dd hh:mi:ss'
    ),
    b
FROM
    tbl_datatype_date;

COMMIT;

ROLLBACK;

-- 날짜형끼리의 산술 연산을 지원 (단위 : 1일)
-- 날짜 +/- 숫자 -> 날짜형
-- 날짜 - 날짜 -> 숫자형(날짜의 차이)

SELECT
    to_char(
        sysdate - 1, 'yy/mm/dd hh:mi:ss'
    ), -- 현재 시각에서 하루 전(-1일)
    to_char(
        sysdate + 100, 'yy/mm/dd hh:mi:ss'
    ), -- 현재 시각에서 백일 후 (+100일)
    TO_DATE('23/09/05', 'yy/mm/dd') - sysdate -- 문자열을 날짜로 변환하여 현재 날짜를 뺀 값
FROM
    dual;

-- 테이블 구조 확인 (컬럼 - 자료형)
desc employee;

--===================================================--
-- SQL
--===================================================--
-- 데이터 정의어 : DDL (Data Definition Language) db객체에 생성 create / 수정 alter / 삭제 drop
-- 데이터 조작어 : DML (Data Manipulation Language) 테이블 행을 생성 insert / 수정 update / 삭제 delete
--                        -  DQL (Data Query Language) 데이터 추출을 포함한 개념. 테이블 행 조회 select
-- 데이터 제어어 : DCL (Data Control Language) 권한 부여 grant / 회수 revoke
--                        -  TCL (Transaction Control Language) 트랜젝션 제어어. 변경 사항을 적용 commit / 취소 rollback


--===================================================--
-- DQL 1
--===================================================--
/* 순서
    (5) select (필수) 조회할 컬럼 나열 
    (1) from (필수) 대상 테이블 
    (2) where 행에 대한 조건절 
    (3) group by 행을 그룹핑 
    (4) having 구룹핑된 행에 대한 조건절 
    (6) order by 정렬 기준 컬럼 나열 
*/

SELECT
    emp_name,
    dept_code
FROM
    employee
WHERE
    dept_code = 'D5'
ORDER BY
    emp_name ASC;
    
--                              @ 연습문제 @
--1. JOB테이블에서 JOB_NAME의 정보만 출력되도록 하시오
SELECT
    job_name
FROM
    job; 

--2. DEPARTMENT테이블의 내용 전체를 출력하는 SELECT문을 작성하시오
SELECT
    *
FROM
    department; 

--3. EMPLOYEE 테이블에서 이름(EMP_NAME),이메일(EMAIL),전화번호(PHONE),고용일(HIRE_DATE)만 출력하시오
SELECT
    emp_name,
    email,
    phone,
    hire_date
FROM
    employee;

--4. EMPLOYEE 테이블에서 고용일(HIRE_DATE) 이름(EMP_NAME),월급(SALARY)를 출력하시오
SELECT
    hire_date,
    emp_name,
    salary
FROM
    employee
ORDER BY
    hire_date ASC;

--5. EMPLOYEE 테이블에서 월급(SALARY)이 2,500,000원이상인 사람의 EMP_NAME 과 SAL_LEVEL을 출력하시오 
--    (힌트 : >(크다) , <(작다) 를 이용)
SELECT
    emp_name,
    sal_level
FROM
    employee
WHERE
    salary >= 2500000
ORDER BY
    sal_level DESC;

--6. EMPLOYEE 테이블에서 월급(SALARY)이 350만원 이상이면서 
--    JOB_CODE가 'J3' 인 사람의 이름(EMP_NAME)과 전화번호(PHONE)를 출력하시오
--    (힌트 : AND(그리고) , OR (또는))
SELECT
    emp_name,
    phone
FROM
    employee
WHERE
    job_code = 'J3'
    AND salary >= 3500000;
    

------------------------------------
-- SELECT
------------------------------------
-- 조회할 컬럼 나열
-- 가상 컬럼값 출력 - 연산 결과, 컬럼 값 이어붙이기, 리터럴

-- 연봉  salary * 12

SELECT
    emp_name 이름, salary 월급, bonus 보너스, (salary + (salary * nvl(bonus,0)))*12 연봉, '원' 단위
FROM
    employee;
    
-- null 과는 산술연산(+ - * /) 할 수 없다. 결과가 무조건 null이다.
SELECT 
    1 + null,
    1 * null,
    1 - null,
    1 / null
FROM
    dual;
    
-- null 처리 함수
-- nvl(col, null_value) : col이 null이 아니면 col 사용, col이 null이면 null_value를 사용

SELECT
    nvl('안녕', '잘가'),
    nvl(null, '잘가')
FROM
    dual;
    
-- 별칭 alias
-- 컬럼의 이름을 변경 as "별칭"
-- as 키워드, ""는 생략 가능(숫자로 시작 또는 특수 문자가 포함된 별칭인 경우는 ""는 생략할 수 없음)
SELECT
    emp_name as"이름",
    salary as"급여",
    phone 전화번호
FROM
    employee;
    
-- distinct 중복 제거 기능
-- select 절에 단 한번만 사용할 수 있다.
-- 여러개의 컬럼에 적용하면, 여러개의 컬럼을 합쳐서 유일하게 처리함
SELECT DISTINCT
    dept_code,
    job_code
FROM
    employee;

-- 문자열 연결 연산
-- 오라클에서는 +는 산술연산에만 사용 가능하다.
-- || 문자열의 연결연산
SELECT
    emp_name 이름,
    salary || '원' "급여(단위)"
FROM
    employee;
-- 더하기(+) 산술연산시 좌/우항의 값을 모두 숫자로 간주하여 처리한다 (자동 형변환)

SELECT
    1+2,
    '1'+'2'
FROM
    dual;
    
------------------------------------
-- WHERE
------------------------------------
-- 결과 집합 result_set에 포함될 행을 필터링하는 조건절

SELECT
    *
FROM
    employee
WHERE
    dept_code='D5';
    
-- AND OR 연산자
-- (조건식) AND (조건식)
-- (조건식) OR (조건식)
-- 부서코드가 D6이고, 급여를 2000000원보다 많이 받는 사원 조회
SELECT 
    *
FROM 
    employee 
WHERE 
    (dept_code='D6') 
    AND 
    (salary>=2000000);
    
-- 직급 코드가 J3, J4인 사원 조회
SELECT 
    *
FROM 
    employee 
WHERE 
    (job_code='J3') 
    OR 
    (job_code='J4');

-- 부서코드가 D5가 아닌 사원 조회
-- != <> ^=
-- not()
SELECT
    *
FROM
    employee
WHERE
    dept_code != 'D5';
--  dept_code <> 'D5';
--  dept_code ^= 'D5';
--  not(dept_code='D5');

-- 범위 연산자 between 값1 and 값2
-- (숫자) 값1 이상 값2 이하를 조회
-- (날짜) 값1 부터 값2 까지
-- 급여 250만원 이상 350만원 이하인 사원 조회
SELECT
    *
FROM
    employee
WHERE
--    salary between 2500000 and 3500000;
    (salary>=2500000) and (salary<=3500000);
    
    
-- 입사일이 90년 1월 1일 ~ 2000년 1월 1일인 사원 조회
SELECT
    *
FROM
    employee
WHERE
--    hire_date between '90/01/01' and '00/01/01'; -- 2000년 1월 1일 자정
    hire_date not between '95/01/01' and '00/01/01';
    
-- 날짜형식의 문자열은 자동으로 날짜형 변환처리 된다. 되도록 to_date 함수 사용할 것!

-- 문자열 패턴비교
-- like '비교 패턴'
-- _ 임의의 글자 하나
-- % 임의의 글자 0개 이상
-- 이름이 세글자이고 가운데 글자가 옹인 사원 조회
SELECT
    *
FROM
    employee
WHERE
    emp_name LIKE '_옹_';

-- 이름이 '식'으로 끝나는 사원 조회
SELECT
    *
FROM
    employee
WHERE
    emp_name LIKE '%식';

-- 이름에 '이'가 들어가는 사원 조회
SELECT
    *
FROM
    employee
WHERE
    emp_name LIKE '%이%';
    
-- escaping
-- 이메일 _앞의 글자수가 3개인 사원 조회
SELECT
    *
FROM
    employee
WHERE
    email LIKE '___\_%' ESCAPE '\'; -- escape문자 지정 가능 \ 추천
    
-- null 비교
-- null은  비교연산/산술연산 불가.
SELECT
    *
FROM
    employee
WHERE
--  dept_code = null; -- Not working
--  dept_code is null;
    dept_code IS NOT NULL;
    
    
-- 비교연산자 in
-- 값목록 포함여부
-- D5, D9 부서원 조회
SELECT
    *
FROM
    employee
WHERE
    dept_code in ('D5', 'D9');
--  dept_code not in ('D5', 'D9'); --D5와 D9가 아닌 놈들


------------------------------------
-- ORDER BY
------------------------------------   
-- 컬럼별 정렬기능
-- 컬럼명. 별칭. select절의 컬럼 순서 등으로 지정
-- asc(기본값) | desc
-- 오름차순 ascending 내림차순 descending
-- 숫자, 문자, 날짜(과거-미래) 모두 지원

SELECT
    emp_name "사원명",
    dept_code "부서코드"
FROM
    employee
ORDER BY
 --   dept_code asc, emp_name desc;
 --   부서코드, 사원명;
    2, 1; -- 필드의 순서 사용하여 정렬할 수 있음


--1. EMPLOYEE 테이블에서 이름 끝이 연으로 끝나는 사원의 이름을 출력하시오
select * from employee where (emp_name like '%연');
--2. EMPLOYEE 테이블에서 전화번호 처음 3자리가 010이 아닌 사원의 이름, 전화번호를 출력하시오
select * from employee where (phone not like '010%');
--3. EMPLOYEE 테이블에서 메일주소 '_'의 앞이 4자이면서, DEPT_CODE가 D9 또는 D6이고
--고용일이 90/01/01 ~ 00/12/01이면서, 월급이 270만원 이상인 사원의 전체 정보를 출력하시오
select
    *
from
    employee
where
    (email like '____#_%' escape '#')
    and (dept_code='D9' or dept_code='D6')
    and (hire_date between to_date('90/01/01', 'rr/mm/dd') and to_date('00/12/01', 'rr/mm/dd'))
    and salary >= 2700000;
--4. 다음 DDL/DML을 먼저 실행하고 문제를 푸세요.
-- tbl_escape_watch 테이블에서 description 컬럼에 99.99% 라는 글자가 들어있는 행만 추출하세요.
create table tbl_escape_watch(
  watchname   varchar2(40)
  ,description    varchar2(200)
);
--drop table tbl_escape_watch;
insert into tbl_escape_watch values('금시계', '순금 99.99% 함유 고급시계');
insert into tbl_escape_watch values('은시계', '고객 만족도 99.99점를 획득한 고급시계');
commit;
select * from tbl_escape_watch;

select * from tbl_escape_watch where description like '%99.99\%%' escape '\'; 

--===================================================--
-- BUILT-IN FUNCTION
--===================================================--
-- function 함수. 일련의 작업 절차를 모아서 선언 후에 호출. 처리 결과를 기대할 수 있는 프로그램
-- method 객체 안에 포함된 function

-- A. 단일행 처리 함수 : 각 행 별로 처리되는 함수
--   1. 문자함수
--   2. 숫자함수
--   3. 날짜함수
--   4. 형변환함수
--   5. 기타함수
-- B. 그룹함수 : 행을 그룹지어 처리하는 함수

------------------------------------
-- 문자함수
------------------------------------ 
-- length 문자열의 길이
-- lengthb 문자열의 byte수

select
    email,
    length(email),
    lengthb(email),
    emp_name,
    length(emp_name),
    lengthb(emp_name)
from
    employee;
    
-- instr(문자열, 검색 문자열, 시작위치, 발생횟수) : 인덱스
-- 1부터 카운트함, 공백 카운트함
-- 시작 위치를 설정해주지 않았을 경우 가장 첫번째 있는 위치 반환함
-- 시작 위치를 -1로 설정할 경우 뒤에서부터 찾아서 인덱스를 반환함
-- 검색하려는 문자열이 존재하지 않는 경우 0 반환
select
    instr('kh정보교육원 국가정보원 정보문화사', '정보') -- 3
    ,instr('kh정보교육원 국가정보원 정보문화사', '정보',8) -- 11
    ,instr('kh정보교육원 국가정보원 정보문화사', '정보',1,2) -- 11 (1번째에서 시작하는데 2번째 나오는 문자열 인덱스 반환)
    ,instr('kh정보교육원 국가정보원 정보문화사', '정보',-1) -- 15 (뒤에서부터 찾음) 
    ,instr('kh정보교육원 국가정보원 정보문화사', 'ㅋㅋㅋㅋㅋ') -- 0
from
    dual;
        
    
-- substr(문자열, 시작위치, 길이) : 잘라낸 문자열 반환
select
    substr('Show me the money~!', 6, 2) --me
    ,substr('Show me the money~!', 6) -- me the money~!
    ,substr('Show me the money~!', -7) -- money
    ,substr('Show me the money~!', 2,7) -- money
from
    dual;

-- @실습문제 : 이메일에서 아이디를 추출하세요
-- @의 위치를 파악하고, 그 위치를 기준으로 잘라낸다
select
    email,
    instr(email, '@')-1 "아이디의 길이",
    substr(email, 1, instr(email, '@')-1) "아이디"
from
    employee;
    
-- lpad | rpad (문자열, 길이, 패딩문자)
-- 왼쪽 | 오른쪽에 padding 문자(여백)를 작성해 고정 길이의 문자열을 반환

select
    lpad(email, 20, '#') -- 20바이트의 공간에 email을 넣고, 남은 공간에 '#'을 채워라
    ,rpad(email, 20, '#')
from
    employee;
    
-- 주문번호kh-230420-00123 작성
select
    lpad(12, 5, 0)
    ,lpad(123, 5, '0')
    ,'kh-' || to_char(sysdate,'yymmdd') || '-' || lpad(123, 5, '0') 주문번호
from
    dual;
    
-- @실습문제 : 남자사원의 사번, 사원명, 주민번호, 연봉을 조회
-- 주민번호 뒤 6자리는 ******로 표시
select
    emp_id 사번,
    emp_name 사원명,
    rpad(substr(emp_no, 1, 8),14,'*') 주민번호, -- sub(emp)bo, 1, 8) || '******'
    (salary + salary * nvl(bonus , 0))*12 연봉
from
    employee
where
    (substr(emp_no, 8, 1) = '1') OR (substr(emp_no, 8, 1) = '3');
    -- substr(emp_no, 8, 1) int ('1', '3');

------------------------------------
-- 숫자처리함수
------------------------------------ 
-- abs : 절대값 반환
select
    abs(-10), abs(10)
from
    dual;

-- mod : %연산자와 동일, 나머지를 반환
select
    mod(10,3)
from
    dual;

-- 짝수년도 입사자 조회
select
    emp_name,
    hire_date,
    extract(year from hire_date) "hire_year"
from
    employee
where
    mod(extract(year from hire_date), 2) = 0;
    
-- ceil : 소수점 기준 올림수 반환
select
    ceil(123.456)
    ,ceil(123.456*100)/100 "123.46" -- 올림해서 소수점 이하 둘째 짜리까지 표현
from
    dual;

-- floor : 소수점 기준 내림수 반환
-- trunc(숫자, 소수점 이하 자리수) : 버림수 반환
select
    floor(45.67)
    ,floor(45.67*10)/10 "45.6"
    ,trunc(45.67, 1)
from
    dual;

-- round(숫자, 소수점 이하 자리수) : 반올림수 반환
select
    round(123.456)
    ,round(123.456,1)
    ,round(123.456,-1)
from
    dual;
    
------------------------------------
-- 날짜 처리 함수
------------------------------------ 
-- 날짜 관련 키워드
select
    sysdate, -- db 시스템 시간(OS)
    systimestamp, -- db 시스템 시간(OS)
    current_date, -- 접속한 session 기준
    current_timestamp -- 접속한 session 시준
from
    dual;

-- add_months(date, number) : 날짜반환
select
    sysdate,
    add_months(sysdate, 12), -- 12개월 후(1년 후)
    add_months('23/01/31', 1), -- 2/28 해당 달의 말일을 반환
    add_months('23/02/28', 1), -- 3/31
    add_months(sysdate, -1) -- 1개월 전 날짜 반환
from
    dual;
    
-- months_between(미래날짜, 과거날짜) : 개월수 차이 반환
select
    trunc(months_between('23/09/05', sysdate), 1) -- 4.5개월
from
    dual;
    
-- @실습문제 : 이름, 입사일, 근무개월수(n개월)
-- 퇴사자 제외
select
    emp_name,
    hire_date,
    trunc(months_between(sysdate, hire_date)) || '개월' "근무개월수",
    trunc(trunc(months_between(sysdate, hire_date))/12)||'년' || mod(trunc(months_between(sysdate, hire_date)),12) || '개월' "년/개월"
from
    employee    
where
    QUIT_YN = 'N';
    
-- extract : 날짜형에서 형식별로 숫자로 추출
select
    extract(year from sysdate) 년, -- 2023
    extract(month from sysdate) 월, -- 4
    extract(day from sysdate) 일, -- 20
    
    extract(hour from cast(sysdate as timestamp)) 시, -- timestamp로 변환 후 추출
    extract(minute from cast(sysdate as timestamp)) 분,
    extract(second from cast(sysdate as timestamp)) 초
from
    dual;
    
-- 1990년도 입사자만 조회
select
    emp_name, hire_date
from
    employee
where
    extract(year from hire_date) = 1990;
    
-- trunc(sysdate) : 시분초 제거
select
    to_char(sysdate, 'yy/mm/dd hh24:mi:ss'), -- 23/04/20 12:13:26
    to_char(trunc(sysdate), 'yy/mm/dd hh24:mi:ss') -- 23/04/20 00:00:00
from
    dual;

------------------------------------
-- 형변환 함수
------------------------------------
/*
           to_char          to_date
          ---------->       --------->
    number        char        date
         <----------       <---------
        to_number          to_char

*/

-- to_char(date, format)
select
    to_char(sysdate, 'yy/mm/dd hh24:mi:ss day d'),
    to_char(sysdate, 'fmyy/mm/dd hh24:mi:ss'), -- 형식 변환중에 생겨난 공백 또는 0을 제거
    to_char(sysdate, 'yy"년" mm"월" dd"일"') --23년 04월 20일 -- 다른 문자를 쓰고 싶으면 " " 안에 넣어주기
from
    dual;
    
-- to_char(number, format)
-- 세자리 콤마, 소수점 이하 처리
-- 형식 문자는 항상 값보다 자리수가 커야 한다
select
    to_char(1234567, '999,999,999'), -- 9 해당 자리수의 숫자가 없다면 공백으로 치환
    to_char(1234567, 'fm999,999,999'), -- 형식 문자(9)로 생긴 공백을 제거
    to_char(1234567, '999,999,999.00'), -- 소숫점 이하는 0으로 처리
    to_char(1234567, '999,999'), -- ###### 오류
    to_char(1234567, '000,000,000'), -- 0 해당 자리수의 숫자가 없다면 0으로 치환
    to_char(123.4567, '999.99') -- 소수점 이하 반올림 처리
from
    dual;
    
-- to_number(char, format)
select
--   '1,234,567' + 33 -- 01722. 00000 -  "invalid number"
   to_number('1,234,567', '9,999,999') + 33
from
    dual;
    
-- to_date
-- 날짜 형식의 문자열은 자동으로 날짜 타입 변환이 되기도 하지만, 명시적으로 변환해서 사용하자
select
    to_date('2023/04/04', 'yyyy/mm/dd') + 1
from
    dual;

select
    *
from
    employee
where
--    hire_date > '2000/01/01'; -- '2000/01/01'이 자동으로 날짜형변환이 되지만, 명시적으로 변환해주는 것이 좋음
    hire_date > to_date('2000/01/01', 'yyyy/mm/dd');
    
-- @실습문제 : 지정한 특정 시각(2018년 2월 8일 12시 23분 50초)에서 3시간 후의 날짜 정보를
-- yyyy/mm/dd hh24:mi:ss 형식으로 화면에 출력하세요
-- 3시간 -> 3*(1/24) -> 3/24
select
    to_char(
        to_date('2018년 2월 8일 12시 23분 50초', 'yyyy"년" mm"월" dd"일" hh24"시" mi"분" ss"초"') +(3/24),
        'yyyy/mm/dd hh24:mi:ss') 답
from
    dual;

-- @실습문제
--1. 현재시각으로 부터 1일 2시간 3분 4초뒤를 나타내세요.(yyyy-mm-dd hh24:mi:ss)
--3시간 -> 3*(1/24) -> 3/24
select
    to_char(
    sysdate + 1 + (2/24) + (3/(24*60)) + (4/(24*60*60)), 'yyyy/mm/dd hh24:mi:ss') 답
from
    dual;
--2. 수료일로부터 d-day구하기 : 수료일은 ??일 남았습니다. 출력
select
     '수료일은' || ceil(to_date('23/09/05', 'yy/mm/dd') - sysdate) || '일 남았습니다.'
    --  d-day는 올림처리
from
    dual;
    
    
------------------------------------
-- 기타 함수
------------------------------------
-- nvl(값, null일때 값)
-- nvl2(값, null 아닐때 값, null일때 값)
select
    emp_name,
    (salary * nvl(bonus, 0)) bonus,
    nvl2(bonus, '있음', '없음') 보너스여부
from
    employee;

-- 선택함수 decode | case

-- decode(표현식, 값1, 결과1, 값2, 결과2, ... [,기본값])
-- 사원 성별 조회
select
    emp_name,
    substr(emp_no, 8, 1),
    decode(substr(emp_no, 8, 1), '1', '남', 2, '여', 3, '남', 4, '여') 성별,
    decode(substr(emp_no, 8, 1), '1', '남', 3, '남', '여') 성별 -- '여'를 기본값 처리
from
    employee;
    
-- case
/*
-- 문법1(decode와 비슷)
case 표현식
    when 값1 then 결과1
    when 값2 then 결과2
    ...
    [else 기본값]
end 


-- 문법2
case
    when 조건식1 then 결과1
    when 조건식2 then 결과2
    ...
    [else 기본값]
end
*/
select
    emp_name,
    case substr(emp_no, 8, 1)
        when '1' then '남'
        when '2' then '여'
        when '3' then '남'
        when '4' then '여'
    end "성별",
    case
        when substr(emp_no, 8, 1) = '1' then '남'
        when substr(emp_no, 8, 1) = '2' then '여'
        when substr(emp_no, 8, 1) = '3' then '남'
        when substr(emp_no, 8, 1) = '4' then '여'
    end "성별",
        case
        when substr(emp_no, 8, 1) in ( '1', '3' ) then '남'
        when substr(emp_no, 8, 1) in ( '2', '4' ) then '여'
    end "성별"
from 
    employee;
    
-- 직급코드 검사하여 J1 대표, J2/J3 임원, 나머지 평사원으로 출력

    
select
    emp_name,
    case job_code -- case 문법1
        when 'J1' then '대표'
        when 'J2' then '임원'
        when 'J3' then '임원'
        else '평사원'
    end 직급,
    
    case -- case 문법2
        when job_code = 'J1' then '대표'
        when job_code in ('J2', 'J3') then '임원'
        else ' 평사원'
    end 직급,
    
     -- decode 사용
    decode(job_code, 'J1', '대표', 'J2', '임원', 'J3', '임원', '평사원') "직급"
from 
    employee;
    
--===================================================--
-- chun 계정 생성 @system
--===================================================--
alter session set "_oracle_script" = true;
create user chun -- chun 유저를 생성함
identified by chun
default tablespace users;
grant connect, resource to chun;
alter user chun quota unlimited on users;

------------------------------------
-- @ 실습 문제
------------------------------------
CREATE TABLE tbl_files (
    fileno   NUMBER(3),
    filepath VARCHAR2(500)
);

INSERT INTO tbl_files VALUES (
    1,
    'c:\abc\deft\salesinfo.xls'
);

INSERT INTO tbl_files VALUES (
    2,
    'c:\music.mp3'
);

INSERT INTO tbl_files VALUES (
    3,
    'c:\documents\resume.hwp'
);

COMMIT;

SELECT
    *
FROM
    tbl_files;

/*
출력결과 :
--------------------------
파일번호          파일명
---------------------------
1             salesinfo.xls
2             music.mp3
3             resume.hwp
---------------------------
*/
select * from tbl_files;

SELECT
    fileno 파일번호,
    substr(filepath, instr(filepath, '\', - 1) + 1)  AS "파일명"
FROM
    tbl_files;
    
    
------------------------------------
-- 그룹 함수
------------------------------------    
-- group by 등을 이용해서 행 별로 그룹핑한 후에 그룹에 대해서 함수를 실행
-- 그룹별 반환값이 하나이다
-- group by 미정지 시 전체 행이 하나의 그룹으로 간주된다
-- 그룹함수 결과와 일반 컬럼을 동시에 표시할 수 없다... ORA-00937: 단일 그룹의 그룹 함수가 아닙니다 00937. 00000 -  "not a single-group group function"


-- sum(col) **
-- 해당 컬럼값이 null인 경우 계산에서 제외된다.
select
    emp_name,
    sum(salary)
from
    employee;
    
-- 남자 사원의 급여의 총합
select
    sum(salary)
from
    employee
where
    substr(emp_no, 8, 1) in ('1', '3');
--  decode(substr(emp_no,8,1), '1', '남', '3', '남', '여') = '남;


-- avg(col) **
-- 해당 컬럼값이 null인 경우 계산에서 제외된다.
select
    trunc(avg(salary))
from
    employee;
 
    
-- 전 사원의 bonus 금액 평균
-- 평균 = 총합 / 개수
select
    -- 보너스를 받는 사람들의 평균 (9명)
    avg(salary*bonus), -- 80만원 (null인 행은 계산에서 제외하고, 개수에서 제외함, /9) 
    
    -- 전 사원의 보너스 평균 (24명)
    avg(salary*nvl(bonus,0)) -- 30만원(null인 행을 0으로 처리하고, 제외되는 행 없이 총 개수로 계산함, /24)
from
    employee;
   
    
-- count(col)
-- col이 null이 아닌 행의 개수를 반환
-- count(*) 전체 행수
select
    count (*), --24
    count(emp_name), --24
    count(bonus), --9
    count(dept_code) -- 22
from
    employee;
    
-- 퇴사자 수 조회
select
    count(QUIT_DATE)
from
    employee;
    
-- D5 부서원 조회
select
    count(*)
from
    employee
where
    dept_code = 'D5';

-- 인턴사원 수 구하는 여러가지 방법
select * from employee;
select count(*) - count(dept_code) from employee; -- 부서가 지정이 안된 사원들이 인턴이기 때문에 빼줌
select count(*) from employee where dept_code is null;
-- 가상컬럼 (계산결과에 대해 작동)
select
    count(case when dept_code is null then 1 end),
    sum(case when dept_code is null then 1 end)
from
    employee;
    
    
-- max(col) | min(col)
-- 최대값 | 최소값 조회 (최대/최소값을 가진 행을 조회하는 것이 아님)
-- 그룹함수는 where 절에 사용할 수 없음!
-- 숫자, 문자열, 날짜에 대해 모두 작동
select
    max(salary),
    min(salary),
    max(hire_date), -- 가장 입사일이 늦은 값 (날짜는 미래가 더 큼)
    min(hire_date), -- 가장 입사일이 빠른 값
    max(emp_name), -- 사전 등록 순 가장 뒷 값
    min(emp_name) -- 사전 등록 순 가장 빠른 값
from
    employee;
    
    
--===================================================--
-- DQL 2
--===================================================--
-- group by
-- having


------------------------------------
-- GROUP BY
------------------------------------   
-- 행을 구룹핑 할 기준 컬럼을 제시, 1개 이상일 수 있다
-- 컬럼 작성 순서는 상관이 없다
-- 기준 컬럼이 null인 경우도 하나의 그룹으로 처리된다

-- 부서별 급여 평균 조회
select
    dept_code,
    sum(salary),
    count(*),
    avg(salary)
from
    employee
group by
    dept_code;
    
-- 직급별 사원수를 조회 (직급 코드로 정렬 출력)
select
    job_code 직급,
    count(*) 사원수
--    emp_name -- ORA-00979: GROUP BY 표현식이 아닙니다.
from
    employee
group by
    job_code
order by
    1; -- 1번째 컬럼을 기준으로 오름차순 (컬럼 이름, 컬럼 인덱스, 별칭 무엇이나 써도 됨)

-- 가상컬럼에 대해서 그룹핑 가능
-- 입사년도별로 인원수 조회
select
    extract(year from hire_date) hire_year,
    count(*)
from
    employee
group by
    extract(year from hire_date)
order by
    hire_year;

-- 성별 인원수 조회
select
    decode(substr(emp_no, 8, 1), '1', 'M', '3', 'M', 'F') "성별",
    count(*) "인원수"
from
    employee
group by
-- group by 절에 작성한 컬럼만 select 절에 쓸 수 있음
    decode(substr(emp_no, 8, 1), '1', 'M', '3', 'M', 'F')
order by
    1;
    
------------------------------------
-- HAVING
------------------------------------       
-- 그룹핑한 결과에 대한 조건절을 작성
-- group by를 작성한 경우에만 쓸 수 있다
-- 그룹 함수 사용할 수 있다 (where절은 그룹함수 사용 못 함)
    
-- 부서별 급여 평균이 300만원 이상인 부서 조회
select
    dept_code 부서,
    trunc(avg(salary)) 급여평균
from
    employee
group by
    dept_code
having -- 그룹핑에 대한 조건을 설정할때 having절 씀
    avg(salary) >= 3000000;
    
-- 부서별 인원수가 3명보다 많은 부서 조회
select
    dept_code 부서,
    count(dept_code) 인원수
from
    employee
group by
    dept_code
having
    count(dept_code) > 3;

-- 두개 이상의 컬럼에 대한 group by
-- 작성한 컬럼 순서는 중요치 않다. 컬럼 값을 동시에 묶어 비교하기 때문이다.
-- 부서별 직급별 인원 수 조회
select
    dept_code,
    job_code,
    count(*)
from
    employee
group by
    dept_code, job_code;

-- 부서별, 성별, 인원수 조회
select
    dept_code,
    decode(substr(emp_no, 8, 1), '1', 'M', '3', 'M', 'F') 성별,
    count(*) 인원수
from
    employee
group by
    dept_code,
    decode(substr(emp_no, 8, 1), '1', 'M', '3', 'M', 'F');
order by
    1;

-- rollup
-- group by절에서 소계를 함께 제공하는 함수
select
    nvl(job_code, '소계'), -- rollup을 사용할 경우 총 갯수도 세어서 null 그룹으로 나오기 때문에 이름 바꿔줌
    count(*)
from
    employee
group by
    rollup(job_code);

-- null 값이 포함된 컬럼 기준 grouping 소계
-- grouping(col) : 실제 데이터 컬럼은 0을 반환, rollup등에 의해 만들어진 컬럼인 경우 1 반환
select
--     dept_code,
--     grouping(dept_code),
     decode(grouping(dept_code), 0, nvl(dept_code, '인턴'), 1, '총계') dept_code,
     count(*)
from
    employee
group by
    rollup(dept_code);
order by
    1;
    
-- 두 개 이상의 컬럼에 대한 rollup
-- 직급코드 / 부서코드로 그룹핑
select
    dept_code,
    job_code,
    count(*)
from
    employee
group by
    rollup(dept_code, job_code) -- 두 개 이상인 경우 첫 번째 컬럼에 대한 group by 결과 행들이 추가됨
order by
    1;
-- select dept_code, count(*) from employee group by dept_code;  
-- 위 코드가 group by절에 추가된 거랑 똑같음


--1. 직원명과 이메일 , 이메일 길이를 출력하시오
--          이름        이메일        이메일길이
--    ex)     홍길동 , hong@kh.or.kr         13
select
    emp_name 이름,
    email 이메일,
    length(email) 이메일길이
from
    employee;

-- 2. 직원의 이름과 이메일 주소중 아이디 부분만 출력하시오
--    ex) 노옹철    no_hc
--    ex) 정중하    jung_jh
select
    emp_name 이름,
    substr(email, 1, instr(email,'@')-1) 아이디
from
    employee;
    
--3. 60년대에 태어난 직원명과 년생, 보너스 값을 출력하시오 
--   그때 보너스 값이 null인 경우에는 0 이라고 출력 되게 
--        직원명    년생      보너스
--    ex) 선동일    1962    0.3
--    ex) 송은희    1963      0
select
    emp_name 직원명,
    '19' || substr(emp_no, 1, 2) 년생,
    nvl(bonus, 0) 보너스
from
    employee;

-- 4. '010' 핸드폰 번호를 쓰지 않는 사람의 수를 출력하시오 (뒤에 단위는 명을 붙이시오)
--       인원
--    ex) 3명
select
    count(*) 인원
from
    employee
where
    substr(phone,1,3) != '010';


--5. 직원명과 입사년월을 출력하시오 
--    단, 아래와 같이 출력되도록 만들어 보시오
--        직원명        입사년월
--    ex) 전형돈        2012년12월
--    ex) 전지연        1997년 3월
select * from employee;
select
    emp_name 직원명,
    to_char(extract(year from hire_date)) || '년' ||  to_char(extract(month from hire_date)) || '월' 입사년월
from
    employee;

-- 6. 직원명과 주민번호를 조회하시오
--    단, 주민번호 9번째 자리부터 끝까지는 '*' 문자로 채워서출력 하시오
--    ex) 홍길동 771120-1******
select
    -- rpad (문자열, 길이, 패딩문자)
    emp_name 이름,
    rpad(substr(emp_no,1,8),14,'*') 주민번호
from
    employee;

-- 7. 직원명, 직급코드, 연봉(원) 조회
--  단, 연봉은 ￦57,000,000 으로 표시되게 함
--  연봉은 보너스포인트가 적용된 1년치 급여임
select
    emp_name 직원명,
    job_code 직급코드,
    '\' || to_char((salary + (salary * nvl(bonus,0)))*12, '999,999,999') 연봉
from
    employee;

-- 8. 부서코드가 D5, D9인 직원들 중에서 2004년도에 입사한 직원중에 조회함.
--   사번 사원명 부서코드 입사일
select
    emp_no 사번,
    emp_name 사원명,
    dept_code 부서코드,
    hire_date 입사일
from
    employee
where
    (dept_code in ('D5', 'D9')) AND (extract(year from hire_date) = '2004');

-- 9. 직원명, 입사일, 오늘까지의 근무일수 조회 
--    * 주말도 포함 , 소수점 아래는 버림
select
    emp_name 직원명,
    hire_date 입사일,
    trunc(sysdate - hire_date) 근무일수
from
    employee;

--===================================================--
-- JOIN
--===================================================--
-- 두개 이상의 테이블 레코드를 연결해서 가상 테이블을 생성
-- 기준 컬럼값을 가지고, 행 단위로 연결

-- 조인 종류
-- 1. Equi join : 기준 컬럼에 대한 연산이 동등 비교인 경우 (=)
-- 2. Non-equi join : 기준 컬럼에 대한 연산이 동등비교가 아닌 경우(between and, is null, in, !=)

-- 조인 문법
-- 1. Ansi 표준문법 : DBMS 구분 없이 사용 가능 join 키워드, on 조건절 사용
-- 2. DBMS별 전용문법 : 오라클 전용 문법. 최적화, 콤마로 테이블 연결. where 조건절에 조인 조건 명시

-- 송종기 사원의 부서명 조회
-- employee : 사원명, 부서코드
-- department : 부서코드, 부서명
select
    dept_code --D9
from
    employee
where
    emp_name = '송종기';
    
select
    *
from
    department
where
    dept_id = 'D9';
    

SELECT
    *
FROM
    employee
    JOIN department ON employee.dept_code = department.dept_id;

WHERE
    emp_name = '송중기';
    
------------------------------------
-- EQUI-JOIN
------------------------------------
-- 1. inner join : 내부 조인, 조건에 맞는 행만 result set에 포함
-- 2. outer join : 외부 조인, 내부 조인에 좌/우측 테이블 행을 무조건 포함
--   - left outer join
--   - right outer join
--   - full outer join
-- 3. cross join 
-- 4. self join
-- 5. multiple join

--+++++++++++++++++++++++++++++++++++
-- INNER JOIN (교집합)
--+++++++++++++++++++++++++++++++++++
-- 기본조인. inner 키워드는 생략 가능
-- 조인 조건의 기준 컬럼이 null이거나 상대 테이블에 매칭되는 행이 없다면 제외됨
select * from employee; -- 24행
select * from department; -- 9행

-- 22행
-- employee에선 dept_code가 null인 2행이 제외됨
-- departemnt에서는 employee에 매칭되는 행이 없는 3행이 제외됨(D3, D4, D7)
SELECT
    *
FROM
    employee e
    INNER JOIN department d ON e.dept_code = d.dept_id; -- dpet_code가 null인 사원들이 빠져서 출력됨

-- department - employee 는 1:N관계의 테이블
-- department.dept_id 컬럼값을 employee.dept_code에서 여러번 참조할 수 있다.

-- (오라클 전용 문법)
-- 테이블 작성 순서는 무관하다.
select
    *
from
    employee e, department d
where
    e.dept_code = d.dept_id;


--+++++++++++++++++++++++++++++++++++
-- OUTER JOIN (부분 집합)
--+++++++++++++++++++++++++++++++++++
-- 외부조인. outer 키워드는 생략 가능
-- left outer join 좌측 테이블의 모든 행을 포함
-- right outer join 우측 테이블의 모든 행을 포함
-- full outer join 양쪽 테이블의 모든 행을 포함

-- 좌측외부조인 -- 24행
-- 사원/부서정보 조회시 부서지정이 안된 사원도 포함하는 경우
-- 24행 = 22행(inner join) + 2행(dept_code가 null인 사원)
select
    *
from
    employee e left outer join department d
        on e.dept_code = d.dept_id; -- dept_code가 null인 2개가 포함됨, 모든 빈칸은 null로 처리됨
        
-- (오라클 전용 문법)
-- 좌측외부조인인 경우, 우측테이블에 조인조건에 (+)를 추가
select
    *
from
    employee e, department d
where
    e.dept_code = d.dept_id (+);


-- 우측외부조인 -- 25행
-- 사원/부서정보 조회시 사원이 없는 부서도 조회하는 경우
-- 25행 = 22행(inner join) + 3행 (employee에 매칭되는 행이 없던 D3, D4, D7)
select
    *
from
    employee e right outer join department d
        on e.dept_code = d.dept_id;
        
-- (오라클 전용 문법)
select
    *
from
    employee e, department d
where
    e.dept_code(+) = d.dept_id;
        

-- 완전외부조인
-- 27행 = 22행(inner join) + 2행(employee) + 3행(department)
select
    *
from
    employee e full outer join department d
        on e.dept_code = d.dept_id;
        
-- (오라클 전용 문법 없음)


-- ******* 조인하려는 두 테이블 사이에 누락된 행
-- (기준컬럼이 null이거나 또는 매칭되는 행 없음)을 파악하는 것이 중요 *******
-- 조인 시 누락된 행이 필요 없다면 내부 조인이 가장 효율성이 높다

-- 부서/지역 정보를 동시에 조회
-- 기준컬럼명은 동일할 수도 있고, 다를 수도 있다.
select * from employee;
select * from department;
select * from location;

select
    *
from
    location l inner join department d
        on l.local_code = d.location_id;

-- 사원/직급 정보를 동시에 조회
select * from employee;
select * from job;

select
    *
from
    employee e inner join job j
        on e.job_code = j.job_code;
        
-- 기준 컬럼명이 같을 때는 using을 사용할 수 있다.
-- using에 사용된 컬럼명은 별칭으로 접근할 수 없다.
SELECT
-- j.job_code
--    e.*,
    e.emp_name,
    e.emp_no,
    e.dept_code,
    job_code,
    j.job_name -- ORA-25154: USING 절의 열 부분은 식별자를 가질 수 없음 
FROM
    employee e
    JOIN job j USING ( job_code );
--+++++++++++++++++++++++++++++++++++
-- CROSS JOIN
--+++++++++++++++++++++++++++++++++++
-- 모든 경우의 수를 고려해서 조인처리
-- 좌측 테이블의 모든 행과 우측 테이블의 모든 행이 한 번씩 연결
-- Cartesian Product(카테시안의 곱)
-- 216행 = 24헹 * 9행
select
    *
from
    employee e cross join department d;
    
-- 평균 급여와 차이 조회

-- (오라클 전용 문법)
select
    *
from
    employee e, department d;



select
    e.*,
    avg(salary) -- ORA-00937: 단일 그룹의 그룹 함수가 아닙니다
from
    employee e;
    
select trunc(avg(salary)) from employee; -- 1행 1열짜리 가상테이블

select
    e.emp_name,
    e.salary,
    e.salary - a.avg_sal
from
    employee e cross join (select trunc(avg(salary))avg_sal from employee) a;
    
--+++++++++++++++++++++++++++++++++++
-- SELF JOIN
--+++++++++++++++++++++++++++++++++++
-- 같은 테이블을 좌우에 두고 조인하는 경우
-- 컬럼값이 같은 테이블의 다른 컬럼을 참조하는 경우 사용

-- 사원 별 관리자 정보 조회
select * from employee;
-- 사번 | 사원명 | 매니져 사번 | 매니져 명
-- manager_id 컬럼이 null인 사람 제외
-- 관리자가 아닌 행 제외
SELECT
    m.emp_name manager_name
FROM
    employee e
    left JOIN employee m ON e.manager_id = m.emp_id
WHERE
    e.emp_name = '방명수'; -- 방명수를 관리하는 사람을 출력함


-- (오라클 전용문법)
SELECT
    *
FROM
    employee e,
    employee m
WHERE
    e.manager_id = m.emp_id (+)
    AND e.emp_name = '방명수';
    
    
--+++++++++++++++++++++++++++++++++++
-- MULTIPLE JOIN
--+++++++++++++++++++++++++++++++++++
-- 다중조인. 여러테이블을 조인
-- 한 번에 조인할 수 있는 테이블은 2개다.
-- 외부 조인으로 포함된 행은 이후 조인에도 계속 외부 조인을 사용해야 한다.

-- 사원/부서/지역 정보를 동시에 조회
-- employee - department - location 테이블 순으로 연결해야한다
SELECT
    e.emp_name,
    j.job_name,
    d.dept_title,
    l.local_name
FROM
    employee e
    LEFT JOIN department d ON e.dept_code = d.dept_id
    LEFT JOIN location   l ON d.location_id = l.local_code
    LEFT join job        j ON e.job_code = j.job_code;
    
-- (오라클 전용 문법) : 테이블 나열 순서는 중요치 않다.
SELECT
    *
FROM
    employee e, department d, location l, job j
WHERE
    e.dept_code = d.dept_id (+)
    AND d.location_id = l.local_code (+)
    AND e.job_code = j.job_code;

    
    
    
-- 직급이 대리 또는 과장이면서 ASIA지역에 근무하는 사원 조회
-- 사번/이름/직급명/부서명/근무지역명/관리자 이름
select * from job;
select * from employee;
select * from department;
select * from location;

SELECT
    e.emp_id, e.emp_name,
    j.job_name,
    d.dept_title,
    l.local_name,
    m.emp_name
FROM
    employee   e
    LEFT JOIN department d ON e.dept_code = d.dept_id
    LEFT JOIN job        j ON j.job_code = e.job_code
    LEFT JOIN location   l ON d.location_id = l.local_code
    LEFT JOIN employee   m ON e.manager_id = m.emp_id
where
    j.job_name in ('대리', '과장')
    and
    l.local_name like 'ASIA%';

select * from job;
select * from employee;
select * from department;
select * from location;


-- 1. 2020년 12월 25일이 무슨 요일인지 조회하시오.
select
    to_char(to_date('2023년 04월 04일', 'yyyy"년" mm"월" dd"일"'), 'day') 
from 
    dual;
    
    
-- 2. 주민번호가 70년대 생이면서 성별이 여자이고, 성이 전씨인 직원들의 
-- 사원명, 주민번호, 부서명, 직급명을 조회하시오.
SELECT
    e.emp_name   사원명,
    e.emp_no     주민번호,
    d.dept_title 부서명,
    j.job_name   직급명
FROM
    employee e
    JOIN department d ON e.dept_code = d.dept_id
    JOIN job j on j.job_code = e.job_code
where
    e.emp_no like '7%' and
    substr(e.emp_no, 8, 1) in ('2', '4') and
    e.emp_name like '전%';

    
--3. 가장 나이가 적은 직원의 사번, 사원명, 나이, 부서명, 직급명을 조회하시오.
-- min(나이) -> 가장 적은 나이 조회 (가장 나이가 적은 사원 조회가 아니다)
-- 조인처리 해야할 부서명, 직급명

-- 한국 나이 : 현재년도 - 출생년도 + 1
-- 주민번호에서 나이를 추출하는 방법 : yy + (1900 | 2000) => 출생년도 (주민번호 뒤 첫번째 자리를 통해 구해야 함)

-- ((오늘) - (출생일)) / 365
-- months_between(오늘, 출생일) / 12

select
    e.emp_id,
    e.emp_name,
--    trunc(months_between(sysdate,to_date(substr(emp_no, 1, 6)))/12) 나이,
    extract(year from sysdate)-(decode(substr(emp_no,8,1),'1',1900,'2',1900,2000))-substr(emp_no,1,2)+1 나이,    
    d.dept_title,
    j.job_code    
FROM
    employee   e
    LEFT JOIN department d ON e.dept_code = d.dept_id
    LEFT JOIN job        j ON e.job_code = j.job_code
    LEFT JOIN location   l ON d.location_id = l.local_code
        
    
select
    emp_name,
    substr(emp_no, 1, 2) + decode(substr(emp_no, 8, 1), '1', 1900 , '2', 1900, 2000) birth_year,
    extract(year from sysdate) - (substr(emp_no, 1, 2) + decode(substr(emp_no, 8, 1), '1', 1900 , '2', 1900, 2000))+ 1 age,
--    ((오늘) - (출생일)) / 365
--    months_between(오늘, 출생일) / 12
    substr(emp_no, 1, 2) + decode(substr(emp_no, 8, 1), '1', 1900 , '2', 1900, 2000) || substr(emp_no, 3, 4) birthday,
    trunc(months_between(sysdate, to_date(substr(emp_no, 1, 2) + decode(substr(emp_no, 8, 1), '1', 1900 , '2', 1900, 2000) || substr(emp_no, 3, 4), 'yyyymmdd')) / 12) 만나이
from
    employee;

-- 가장 적은 나이 조회
select
    min(extract(year from sysdate) - (substr(emp_no, 1, 2) + decode(substr(emp_no, 8, 1), '1', 1900 , '2', 1900, 2000))+ 1) min_age
from
    employee;

-- cross join 이용해서 그룹함수 결과와 일반 컬럼 동시 조회
select
    e.emp_name, e.emp_no, d.dept_title, j.job_name
from
    employee e 
        cross join (
            select
                min(extract(year from sysdate) - (substr(emp_no, 1, 2) + decode(substr(emp_no, 8, 1), '1', 1900 , '2', 1900, 2000))+ 1) min_age
            from
                employee) m
        left join department d
            on e.dept_code = d.dept_id
        left join job j
            on e.job_code = j.job_code
where
    extract(year from sysdate) - (substr(emp_no, 1, 2) + decode(substr(emp_no, 8, 1), '1', 1900 , '2', 1900, 2000))+ 1 = m.min_age;    


--4. 이름에 '형'자가 들어가는 직원들의 사번, 사원명, 부서명을 조회하시오.
select
    e.emp_id 사번,
    e.emp_name 사원명,
    d.dept_title 부서명
from
    employee e
    join department d on e.dept_code = d.dept_id
where
    e.emp_name like '%형%';

--5. 해외영업팀에 근무하는 사원명, 직급명, 부서코드, 부서명을 조회하시오.
select * from job;
select
    emp_name 사원명,
    job_name 직급명,
    dept_code 부서코드,
    dept_title 부서명
FROM
    employee   e
    JOIN department d ON e.dept_code = d.dept_id
    JOIN job        j ON e.job_code = j.job_code
    JOIN location   l ON d.location_id = l.local_code;

--6. 보너스포인트를 받는 직원들의 사원명, 보너스포인트, 부서명, 근무지역명을 조회하시오.
select * from job;
select * from location;
select * from department;

select
    emp_name 사원명,
    bonus 보너스,
    dept_title 부서명,
    local_name 근무지역명
from
    employee e
    join department d on e.dept_code = d.dept_id
    join location l on d.location_id = l.local_code;


--7. 부서코드가 D2인 직원들의 사원명, 직급명, 부서명, 근무지역명을 조회하시오.
select
    e.emp_name 사원명,
    j.job_name 직급명,
    d.dept_title 부서명,
    l.local_name 근무지역명
from
    employee e
    join department d on e.dept_code = d.dept_id
    join location l on d.location_id = l.local_code
    JOIN job        j ON e.job_code = j.job_code
where
    dept_code = 'D2';
    


-- 8. 급여등급테이블의 등급별 최대급여(MAX_SAL)보다 많이 받는 직원들의 사원명, 직급명, 급여, 연봉을 조회하시오.
-- (사원테이블과 급여등급테이블을 SAL_LEVEL컬럼기준으로 동등 조인할 것)
select distinct
    e.emp_name 사원명,
    j.job_name 직급명,
    e.salary 급여,
    (salary + (salary * nvl(bonus, 0))) * 12 연봉
from
    employee e
    JOIN job        j ON e.job_code = j.job_code
    join sal_grade s on s.sal_level = e.sal_level
where
    e.salary >= max_sal;
    
select * from sal_grade; 
select * from employee;


-- 9. 한국(KO)과 일본(JP)에 근무하는 직원들의 
-- 사원명, 부서명, 지역명, 국가명을 조회하시오.
select
    e.emp_name 사원명,
    d.dept_title 부서명,
    l.local_name 지역명,
    l.national_code 국가명
from
    employee e
    join department d on e.dept_code = d.dept_id
    join location l on d.location_id = l.local_code;
    
select * from location;

-- 10. 같은 부서에 근무하는 직원들의 사원명, 부서코드, 동료이름을 조회하시오.
-- self join 사용
select * from department;
select * from employee;
select
    e.emp_name 사원명,
    e.dept_code 부서코드,
    e2.emp_name
from
    employee e
    join employee e2 on e.dept_code = e2.dept_code;


-- 11. 보너스포인트가 없는 직원들 중에서 직급이 차장과 사원인 직원들의 사원명, 직급명, 급여를 조회하시오.
select
    emp_name,
    job_name,
    salary
from
    employee e
    JOIN job        j ON e.job_code = j.job_code
where
    e.job_code in ('J4','J7');
    

------------------------------------
-- NON-EQUI-JOIN
------------------------------------    
-- 조인 조건절에서 동등 비교 연산자가 아닌 다른 연산자를 사용해 연결하는 경우
-- !=, >, <, <=, >=, between and, in ...

select * from sal_grade;
select * from employee; 

SELECT
    *
FROM
    employee e
    JOIN sal_grade s ON e.salary BETWEEN s.min_sal AND s.max_sal;

--===================================================--
-- SET OPERATOR 집합연산자
--===================================================--
-- 컬럼 기준으로 테이블을 합쳐 가상테이블을 만드는 연산자
-- union(합집합) | union all(합집합) | intersect(교집합) | minus(차집합)

-- 집합 연산 룰
-- 1. 두 테이블의 컬럼 수가 같아야 함
-- 2. 상응하는 컬럼의 자료형이 상호호환 가능해야 함 (char, varchar2 호환 가능)
-- 3. order by절은 마지막에 한 번만 사용이 가능
-- 4. 컬럼명이 다르다면 첫번째 테이블 기준으로 합쳐짐

-- 테이블a : 부서코드가 D5인 사원 테이블
select
    emp_id, emp_name, dept_code, salary
from
    employee
where
    dept_code = 'D5'; -- 6행

-- 테이블b : 급여가 300만원 이상인 사원 테이블
select
    emp_id, emp_name, dept_code, salary
from
    employee
where
    salary >= 3000000; -- 9행

------------------------------------
-- UNION | UNION ALL
------------------------------------
-- 합집합 연산
-- union     : 두 테이블을 연결 후, 중복 제거, 첫번째 컬럼 기준 오름차순 정렬(기본값)
-- union all : 두 테이블을 그대로 연결
-- 정렬이 필요한 경우 order by를 꼭 사용할 것!

-- union all (중복제거되지 않음)
select
    emp_id, emp_name, dept_code, salary
from
    employee
where
    dept_code = 'D5'
union all
select
    emp_id, emp_name, dept_code, salary -- 컬럼 명이 똑같아야 함
                                        -- ORA-01790: 대응하는 식과 같은 데이터 유형이어야 합니다
from
    employee
where
    salary >= 3000000; 
    
    
-- union 
select
    emp_id, emp_name, dept_code, salary
from
    employee
where
    dept_code = 'D5'
union
select
    emp_id, emp_name, dept_code, salary -- 컬럼 명이 똑같아야 함                                    -- ORA-01790: 대응하는 식과 같은 데이터 유형이어야 합니다
from
    employee
where
    salary >= 3000000 
order by
    emp_id; -- order by는 마지막에 한 번만 쓸 수 있음
    
------------------------------------
-- INTERSECT
------------------------------------
-- 교집합
-- 위/아래 테이블의 중복된 행 반환

select
    emp_id, emp_name, dept_code, salary
from
    employee
where
    dept_code = 'D5'
intersect
select
    emp_id, emp_name, dept_code, salary -- 컬럼 명이 똑같아야 함                                    -- ORA-01790: 대응하는 식과 같은 데이터 유형이어야 합니다
from
    employee
where
    salary >= 3000000;
    
-- 차집합
-- 위 테이블에서 아래 테이블과 중복된 행 제거
select
    emp_id, emp_name, dept_code, salary
from
    employee
where
    dept_code = 'D5'
minus
select
    emp_id, emp_name, dept_code, salary -- 컬럼 명이 똑같아야 함                                    -- ORA-01790: 대응하는 식과 같은 데이터 유형이어야 합니다
from
    employee
where
    salary >= 3000000;


-- 판매데이터 관리하기
create table tbl_sales (
    p_name varchar2(50),
    p_count number,
    sale_date date
);

select * from tbl_sales;

-- 2달 전 데이터
insert into tbl_sales values('맛동산', 10, '230201');
insert into tbl_sales values('홈런볼', 15, '230203');
insert into tbl_sales values('새우깡', 23, '230210');
insert into tbl_sales values('홈런볼', 30, '230215');
insert into tbl_sales values('다이제', 7, '230222');
insert into tbl_sales values('맛동산', 9, '230225');

-- 지난 달 데이터
insert into tbl_sales values('맛동산', 10, '230301');
insert into tbl_sales values('홈런볼', 15, '230303');
insert into tbl_sales values('새우깡', 23, '230310');
insert into tbl_sales values('홈런볼', 30, '230315');
insert into tbl_sales values('다이제', 7, '230322');
insert into tbl_sales values('맛동산', 9, '230325');
insert into tbl_sales values('자가비', 17, '230325');

-- 현재 월 데이터
insert into tbl_sales values('맛동산', 10, '230401');
insert into tbl_sales values('홈런볼', 15, '230403');
insert into tbl_sales values('새우깡', 23, '230410');
insert into tbl_sales values('홈런볼', 30, '230415');
insert into tbl_sales values('다이제', 7, '230422');
insert into tbl_sales values('맛동산', 9, '230425');
insert into tbl_sales values('자가비', 17, '230425');

-- 두 달 전 판매데이터를 조회
-- 언제 조회를 해도 두 달 전 데이터가 잘 조회가 되어야 함
-- 현재가 1월일 때 지난해 11월 데이터가 잘 조회되는가?
-- 현재 날짜와 상관없이 두달 전 1일부터 말일까지 데이터가 잘 조회되는가?
-- 2023년 4월 조회 시 2023년 2월 데이터만 조회되는가? (2022년 2월 또는 2021년 2월 데이터는 포함되어서는 안됨)
-- 즉, 년월을 동시 조회해야 함

-- 2023년 2월 1일 ~ 2023년 2월 말일   
select
    *
from
    tbl_sales
where
    to_char(sale_date, 'yyyy-mm') = to_char(add_months(sysdate,-2), 'yyyy-mm');    
--    extract(month from sysdate)-2 = extract(month from sale_date);
-- add_months : 날짜의 일은 변경되지 않으며 연산값에 따라서 년도와 월만 변경이 된다.

-- 지난달 데이터
select
    *
from
    tbl_sales
where
    to_char(sale_date, 'yyyy-mm') = to_char(add_months(sysdate, -1), 'yyyy-mm');


-- 판매 데이터는 현재 월의 데이터만 관리하고, 달이 바뀌면 지난 달 판매 테이블을 생성해서 이전시킨다.
-- tbl_sales_2302 - 2월달 데이터만 관리
create table tbl_sales_2302 -- as 아래 작성된 테이블을 기준으로 새로운 테이블 생성함
as
select
    *
from
    tbl_sales
where
    to_char(sale_date, 'yyyy-mm') = to_char(add_months(sysdate,-2), 'yyyy-mm');
    
-- tbl_sales_2303 - 3월달 데이터만 관리
create table tbl_sales_2303
as
select
    *
from
    tbl_sales
where
    to_char(sale_date, 'yyyy-mm') = to_char(add_months(sysdate,-1), 'yyyy-mm'); 

select * from tbl_sales_2302;
select * from tbl_sales_2303;
select * from tbl_sales;

-- 지난 판매 데이터 삭제
delete from
    tbl_sales
where
    to_char(sale_date, 'yyyy-mm') = to_char(add_months(sysdate,-2), 'yyyy-mm');

-- 지난 3개월간 판매데이터를 조회
select * from tbl_sales_2302
union All
select * from tbl_sales_2303
union All
select * from tbl_sales
order by
    3;

select
    *
from (
    select * from tbl_sales_2302
    union All
    select * from tbl_sales_2303
    union All
    select * from tbl_sales
)
where
    p_name = '맛동산';
    
-- 1. 지난 3개월 제품별 총 판매량 조회
select 
    p_name,
    sum(p_count) 판매량
from (
    select * from tbl_sales_2302
    union All
    select * from tbl_sales_2303
    union All
    select * from tbl_sales
)
group by
    p_name
order by
    2 desc;

-- 2. 지난 3개월 월별 제품별 총 판매량 조회
select
    to_char(sale_date, 'yyyy-mm') "yyyy-mm",
    p_name,    
    sum(p_count) 판매량
from (
    select * from tbl_sales_2302
    union All
    select * from tbl_sales_2303
    union All
    select * from tbl_sales
)
group by
    p_name,
    to_char(sale_date, 'yyyy-mm');
--    extract(month from sale_date);


-- grouping sets
-- group by + union all
-- 각 컬럼명 group by 결과를 합친 것과 동일한 결과집합 반환

-- 부서별, 직급별 급여 평균
select
    dept_code, job_code, trunc(avg(salary))
from
    employee
group by
    grouping sets(dept_code, job_code);
    
------------------------ 아래 코드와 동일한 결과   
select
    null,
    job_code,
    trunc(avg(salary))
from
    employee
group by
    job_code; 
union all
select
    dept_code,
    null,    
    trunc(avg(salary))
from
    employee
group by
    dept_code;   
------------------------
-- 성별 직급별 부서별 인원수 조회
-- decode(grouping(col), 0, 실제데이터, 1, '-')
-- grouping : GROUPING 함수는 직접 그룹별 집계를 구하지는 않지만 GROUPING SETS를 지원하는 역할을 한다. 
-- 소계나 총합 등 집계된 데이터일 경우 1리턴, 집계된 데이트가 아니면 0을 리턴

select
    decode(grouping(decode(substr(emp_no, 8, 1), '1', '남', '3', '남', '여')), 0, decode(substr(emp_no, 8, 1), '1', '남', '3', '남', '여'), '-') gender,
    decode(grouping(job_code), 0, job_code, '-') job_code,
    decode(grouping(dept_code), 0, nvl(dept_code,'인턴'), '-') dept_code,
--    decode(substr(emp_no, 8, 1), '1', '남', '3', '남', '여') 성별,
    count(*) cnt
from
    employee
group by
    grouping sets(dept_code, job_code, decode(substr(emp_no, 8, 1), '1', '남', '3', '남', '여'));

select count(*) from employee group by ();


--===================================================--
-- SUB QUERY
--===================================================--
-- 하나의 SQL(main-query)에 포함된 또 다른 SQL(syb-query)
-- 존재하지 않는 값에 근거해 조회할 때 유용하다
-- 메인쿼리 실행 중 서브쿼리를 만나면, 서브쿼리 우선 실행 후 그 결과를 다시 메인쿼리에 전달하는 구조

-- 종류
-- 1. 일반 subquery : 메인 쿼리와 독립적으로 실행 가능한 서브쿼리
-- 2. 상관 subquery : 상호 연관 서브쿼리, 메인쿼리로 부터 값을 참조해서 실행 가능한 서브쿼리

-- 사용시 유의사항
-- 1. 서브쿼리는 반드시 () 소괄호를 묶어서 작성
-- 2. 서브쿼리 내에서는 order by 문법 적용되지 않음
-- 3. 되도록 연산자 우측에 작성

-- 노옹철 직원의 관리자 이름 조회
select * from employee;
select
    m.emp_name
from
    employee e
    left join employee m on m.emp_id = e.manager_id
where
    e.emp_name = '노옹철';

-- 노옹철 사원의 관리자 아이디 조회 -> 사원테이블에서 사번과 일치하는 행의 사원명을 조회
select manager_id from employee where emp_name = '노옹철';
select emp_name from employee where emp_id = '201'; -- 노옹철의 관리자 아이디

select
    emp_name
from
    employee
where
    emp_id = (select manager_id from employee where emp_name = '노옹철'); -- 서브쿼리

-- 전 직원의 평균 급여보다 많은 급여를 받는 사원 조회 (사원명, 급여)
select avg(salary) from employee; -- 평균 급여를 구함
select emp_name, salary from employee where salary > (평균급여);

select
    emp_name, salary
from
    employee
where
    salary > (select avg(salary) from employee);

------------------------------------
-- 1행 1열 서브쿼리 (단일행)
------------------------------------
-- 윤은해와 같은 급여를 받는 사원 조회(사번, 사원명, 급여)
select salary from employee where emp_name = '윤은해';

select 
    emp_id, emp_name, salary 
from 
    employee 
where 
    salary = (select salary from employee where emp_name = '윤은해')
    AND
    emp_id != 210;

-- 사원테이블에서 최대 급여를 받는 사원 조회
-- max(salarys)

select
    emp_id, emp_name, salary
from
    employee
where
    salary = (select max(salary) from employee)
    OR
    salary = (select min(salary) from employee);
-- salary in ((select max(salary) from employee), (select min(salary) from employee));     
-- 같은 결과!

-- D1, D2 부서원 중에서 D5 부서원의 평균 급여보다 많은 급여를 받는 사원 조회

select
    *
from
    employee
where
    salary > (select avg(salary) from employee where dept_code = 'D5')
    AND
    dept_code in ('D1', 'D2');

------------------------------------
-- n행 1열 서브쿼리
------------------------------------
-- 서브 쿼리의 조회 결과 여러행인 경우, 적절한 연산자(int, any(some), all, exists 등)와 사용해야 한다

-- 송중기, 하이유가 속한 부서원 조회 (D9, D5)
select dept_code from employee where emp_name in ('송종기', '하이유');


select
    *
from
    employee
where
    dept_code in (select dept_code from employee where emp_name in ('송종기', '하이유'));
--            = 연산자 사용 불가능 (ORA-01427: 단일 행 하위 질의에 2개 이상의 행이 리턴되었습니다.)

-- 차태연, 박나라, 이오리와 같은 직급의 사원
select
    *
from
    employee
where
    job_code in (select job_code from employee where emp_name in ('차태연','박나라','이오리'));


-- not in
-- 서브쿼리의 조회 결과와 하나라도 일치하지 않는 행을 조회
-- 송종기/박나라의 부서에 근무하지 않는 사원
select
    *
from
    employee
where
    dept_code not in (select dept_code from employee where emp_name in ('송종기', '박나라'));

-- 직급이 대표, 부사장이 아닌 사원의 사번, 사원명, 직급명 조회
select
    e.emp_id,
    e.emp_name,
    j.job_name
from
    employee e join job j
    using(job_code)
where
    job_name not in (select job_name from job where job_name in ('부사장', '대표'));

-- Asia1 지역에 근무하는 사원의 정보 출력, 부서코드, 사원명(메인쿼리 조인하지 않기)

select * from employee; -- dept_code
select * from location;    -- local_name, local_code
select * from department; -- location_id, dept_id

select
    dept_code,
    emp_name
from
    employee
where
    dept_code in (select dept_id from department where (location_id = (select local_code from location where local_name = 'ASIA1')));

-- any | some
-- any(n행 1열 서브쿼리) 여러값 중 하나와 비교
-- col > any(...) 여러 값의 최소 값보다 크면 참
-- col < any(...) 여러 값의 최대 값보다 작으면 참

-- J3 직급의 사원보다 적은 급여를 받는 사원 조회
select
    *
from
    employee
where
    salary < any(select salary from employee where job_code = 'J3'); -- 3400000, 3900000, 3500000

-- all
-- all(n행 1열의 서브쿼리) 포함된 모든 값 비교
-- col > all(...) 여러 값의 최대값보다 크면 참
-- col < all(...) 여러 값의 최소값보다 작으면 참
select
    *
from
    employee
where
    salary < all(select salary from employee where job_code = 'J3');  -- 3400000, 3900000, 3500000


------------------------------------
-- n열 서브쿼리
------------------------------------
-- 서브쿼리 조회 결과가 n열인 경우
-- 행의 수에 따라 사용해야 하는 연산자가 다르다
-- 1행인 경우 = , n행인 경우 in
-- in 연산자는 1행도 n행도 모두 처리할 수 있다.

-- 퇴사한 사원이 한 명 있다. 그 사원과 같은 부서, 같은 직급의 사원 조회(이름, 직급코드, 부서코드)
select dept_code, job_code from employee where quit_yn = 'Y';
select dept_code, job_code from employee where quit_date is not null;

select
    *
from
    employee
where
    (dept_code, job_code) in (select dept_code, job_code from employee where quit_yn = 'Y')
    and
    quit_yn = 'N';
    
-- 직급별 최소 급여를 받는 사원 조회
SELECT
    job_code,
    MIN(salary)
FROM
    employee
GROUP BY
    job_code;

-- n행 n열 서브쿼리 사용
SELECT
    *
FROM
    employee
WHERE
    (job_code, salary) IN (SELECT job_code,MIN(salary) FROM employee GROUP BY job_code);
    
    
-- 부서별 최대/최소 급여를 받는 사원의 부서코드, 사원명, 급여
select
    dept_code,
    emp_name,
    salary
from
    employee
where
    (nvl(dept_code, 'D0'), salary) in (select nvl(dept_code, 'D0'), MAX(salary) from employee group by dept_code)
    OR
    (nvl(dept_code, 'D0'), salary) in (select nvl(dept_code, 'D0'), MIN(salary) from employee group by dept_code)
order by
    1;
 
------------------------------------
-- 상관 SUBQUERY
------------------------------------  
-- 메인쿼리의 값을 받아 서브쿼리를 실행. 그 결과값을 다시 메인쿼리로 반환하는 서브쿼리
-- 단독으로 블럭잡아 실행할 수 없다.

-- 직급 별 평균 급여보다 많은 급여를 받는 사원 조회
select job_code, avg(salary) from employee GROUP BY job_code;

select
    job_code,
    emp_name,
    salary
from
    employee e
where
    salary > (select avg(salary) from employee where job_code = e.job_code)
order by
    1;

-- 부서 코드 별 평균 급여보다 적은 급여를 받는 사원 조회 (부서코드, 사원명, 급여)
-- 인턴도 결과 집합에 포함시킬 것
select
    nvl(dept_code,'인턴') 부서코드,
    emp_name,
    salary
from
    employee e
where
    --null이라면 false가 나오고 연산이 안됨
    salary < (select avg(salary) from employee where nvl(dept_code,'인턴') = nvl(e.dept_code,'인턴'))
order by
    1;
    
-- exists (상관 서브 쿼리)
-- where 절의 조건식 연산자, true/false 반환
-- 서브쿼리의 결과 집합이 1행 이상이면 (행이 존재하면) 참
-- 서브쿼리의 결과 집합이 0행이면 (행이 존쟇하지 않으면) 거짓
select
    *
from
    employee
where
--  1 = 1; -- 무조건 참
  1 = 0; -- 무조건 거짓

-- 부서원이 존재하는 부서 조회
select * from employee where dept_code = 'D3';

select
    *
from 
    department d
where
    exists(select * from employee where dept_code = d.dept_id);
    -- exists 결과가 0(false)이면 메인 쿼리의 결과 값에서 제외함

-- 관리자 사원만 조회
select * from employee;

select
    *
from 
    employee m
where
    exists(select 1 from employee where manager_id = m.emp_id); 
            -- select 1 : 행의 갯수만큼 1이 출력됨
            -- 논리식에 사용될 때는 실제 값이 아닌, 값의 존재 유무가 더 중요하기 때문에
            -- 보다 간단하게 사용하기 위해서 이러한 형태로 사용함
            
            
-- not exists 최대/최소값 조회
-- 최대 급여를 받는 사원 조회

select
    *
from
    employee e -- employee 행의 모든 급여를 조회해서 서브 쿼리에 전달
where
    not exists(select 1 from employee where salary > e.salary);
    -- salary에서 더 큰 급여를 받는 사람이 있나 검색해서, 그게 조회되지 않으면 == 즉 최대급여이면
    
select
    *
from
    employee e
where
    not exists (select 1 from employee where hire_date > e.hire_date);

    
select
    *
from
    employee
where
    hire_date = (select max(hire_date) from employee);
            
select * from employee;
------------------------------------
-- SCALA SUBQUERY
------------------------------------  
-- 단일값 scala
-- 결과값(1행 1열)이 하나인 select 절에 사용된 상관서브쿼리

-- 사원명, 부서명을 조회
select dept_title from department where dept_id = 'D1';

select
    emp_name,
    (select job_name from job where job_code = e.job_code) job_name,
    (select dept_title from department where dept_id = e.dept_code) dept_title
    ----------- scala 서브쿼리 -----------
from
    employee e;

-- 사원명, 직급코드, 부서코드, 직급 평균 급여, 부서 평균 급여 조회
-- scala subquery 사용(그룹바이 금지)
select
    emp_name,
    job_code 직급코드,
    nvl(dept_code,'인턴') 부서코드,
    salary,
    (select trunc(avg(salary)) from employee where job_code = e.job_code) "직급 평균 급여",
    (select trunc(avg(salary)) from employee where dept_code = e.dept_code) "부서평균급여"
from
    employee e;


--@실습문제 - 서브쿼리
--1. 주민번호가 1970년대 생이면서 성별이 여자이고, 성이 전씨인 직원들의 사원명, 주민번호, 부서명, 직급명을 조회하시오.
select
    emp_name 사원명,
    emp_no 주민번호,
    (select dept_title from department  where dept_id = e.dept_code) 부서명,
    (select job_name from job where job_code = e.job_code) 직급명
from 
    employee  e
where
    substr(emp_no,1,1) ='7' AND substr(emp_no,8,1) in ('2','4') AND emp_name like '전%';

select 
min(extract(year from sysdate) - (decode(substr(emp_no,8,1),'1',1900,'2',1900,2000)) - substr(emp_no,1,2) +1)
from employee;

--2. 가장 나이가 적은 직원의 사번, 사원명, 나이, 부서명, 직급명을 조회하시오
select
    emp_id 사번,
    emp_name 사원명,
    extract(year from sysdate) - (decode(substr(emp_no,8,1),'1',1900,'2',1900,2000)) - substr(emp_no,1,2) +1 as age,
    (select dept_title from department  where dept_id = e.dept_code) 부서명,
    (select job_name from job where job_code = e.job_code) 직급명
from
    employee e
where
    extract(year from sysdate) - (decode(substr(emp_no,8,1),'1',1900,'2',1900,2000)) - substr(emp_no,1,2) +1 = (select min(extract(year from sysdate) - (decode(substr(emp_no,8,1),'1',1900,'2',1900,2000)) - substr(emp_no,1,2) +1) from employee);
    -- select가 가장 마지막에 해석되기 때문에, 별칭을 where절에 쓸 수 없다.
    
--3. 이름에 '형'자가 들어가는 직원들의 사번, 사원명, 부서명을 조회하시오.
select
    emp_id 사번,
    emp_name 사원명,
    (select dept_title from department where dept_id = e.dept_code) 부서명
from
    employee e
where
    emp_name like '%형%';
    
--4. 해외영업부에 근무하는 사원명, 직급명, 부서코드, 부서명을 조회하시오.
select * from employee; -- dept_code
select * from department; -- dept_id, dept_title
select * from job; -- job_code, job_name

select
    emp_name 사원명,
    (select job_name from job where job_code = e.job_code) 직급명,
    dept_code 부서코드,
    (select dept_title from department where dept_id = e.dept_code) 부서명 
from
    employee e;

select * from location;
select * from employee; -- dept_code
select * from department; -- dept_id, dept_title
select * from job; -- job_code, job_name

--5. 보너스포인트를 받는 직원들의 사원명, 보너스포인트, 부서명, 근무지역명을 조회하시오.
select
    emp_name 사원명,
    bonus 보너스포인트,
    (select dept_title from department where dept_id = e.dept_code) 부서명,
    (select local_name from location where local_code = (select location_id from department where (dept_id = e.dept_code))) 근무지역명    
from
    employee e
where
    bonus is not null;
    
    
--6. 부서코드가 D2인 직원들의 사원명, 직급명, 부서명, 근무지역명을 조회하시오.
select
    emp_name 사원명,
    (select job_name from job where job_code = e.job_code) 직급명,
    (select dept_title from department where dept_id = e.dept_code) 부서명,
    (select local_name from location where local_code = (select location_id from department where (dept_id = e.dept_code))) 근무지역명    
from
    employee e
where
    dept_code = 'D2';
    

--7. 급여등급테이블의 등급별 최대급여(MAX_SAL)보다 많이 받는 직원들의 사원명, 직급명, 급여, 연봉을 조회하시오.
select
     emp_name 사원명,
    (select job_name from job where job_code = e.job_code) 직급명,
    salary 급여,
    (salary+( salary*nvl(bonus,0)))*12 연봉,
    (select max_sal from sal_grade where sal_level = e.sal_level) 최대급여
from
    employee e 
where
    salary > (select max_sal from sal_grade where sal_level = e.sal_level);
    
select * from sal_grade;

--8. 보너스포인트가 없는 직원들 중에서 직급이 차장과 사원인 직원들의 사원명, 직급명, 급여를 조회하시오.
select
    emp_name 사원명,
    (select job_name from job where job_code = e.job_code) 직급명,
    salary
from
    employee e
where
    bonus is null
    and
    e.job_code in (select job_code from job where job_name in ('차장','사원'));
    
    
------------------------------------
-- INLINE-VIEW
------------------------------------  
-- from절에 사용한 서브쿼리
-- view란? 실제 테이블에 근거한 논리적 가상테이블. 실제 테이블과 사용 방법은 동일
-- 복잡한 쿼리를 간결하게 사용하거나, 실제 테이블을 제한적으로 사용할 때 뷰를 사용하면 좋다
-- 1. inline-view 1회용
-- 2. stored-view db객체로 저장해서 재사용이 가능

-- 여자 사원만 조회 (사번, 이름, 성별)
select
    emp_id 사번,
    emp_name 이름,
    gender
    --inline-view에 포함된 컬럼만 사용 가능하다
from ( -- 가상의 테이블을 생성해서 거기서 조회하는 것이기 때문
    select
        e.*,
        decode(substr(emp_no,8,1), '1', '남', '3', '남', '여') gender
    from
        employee e
)    
where
    gender = '여';

-- 1990년대 입사자만 조회 (사번, 사원명, 입사년도)

select extract(year from hire_date) from employee where 2000 > extract(year from hire_date);

select
    emp_id 사번,
    emp_name 이름,
    hire_year
from (
    select 
        e.*,
        extract(year from hire_date) hire_year
    from 
        employee e 
)
where
    hire_year between 1990 and 1999;
    
-- 30,40대 남자 사원 조회

select
    emp_id 사번,
    emp_name 이름,
    gender,
    age,
    부서명
-- (select dept_title from department where dpet_id = e.dept_code) dept_title,
    --inline-view에 포함된 컬럼만 사용 가능하다
from ( -- 가상의 테이블을 생성해서 거기서 조회하는 것이기 때문
    select
        e.*,
        d.dept_title 부서명,
        decode(substr(emp_no,8,1), '1', '남', '3', '남', '여') gender,
        extract(year from sysdate) - (decode(substr(emp_no,8,1),'1',1900,'2',1900,2000)) - substr(emp_no,1,2) +1 as age
    from
        employee e
        join department d on e.dept_code = d.dept_id
)    
where
    gender = '남'
    and
    age between 30 and 49;
    
select * from department;
select * from employee;


--===================================================--
-- 고급 쿼리
--===================================================--

------------------------------------
-- TON-N 분석
------------------------------------
-- 특정 테이블에서 특정 컬럼 기준 오름차순/내림차순 정렬했을 때 상위 n개의 행을 조회
-- 이번 달 가장 많이 팔린 상품 10가지, 시험성적 1~10위 조회, 연봉 상위 Top-5

-- rowid : 테이블 특정 레코드에 접근하기 위한 논리적 주소값. 인덱스 객체에서 레코드 접근 시 사용.
-- rownum : 결과집합 생성 시 행단위로 부여되는 식별번호
--          where절을 사용하거나, inline-view 사용 시 새로 부여된다
--          from/where 절을 거쳐 생성되며, order by로는 rownum이 변경되지 않는다.

select
    rowid,
    rownum,
    e.*
from
    employee e
order by
    emp_name;
    
-- 급여 Top-5 조회
select
    rownum new,
    e.*
from (
    select
--        rownum old,
        emp_name,
        salary
    from
        employee -- << rownum 부여됨
    order by
        salary desc
) e
where
    rownum between 1 and 5;

-- 가장 최근 입사자 5명 조회 (사번, 사원명, 부서명, 입사일)
select
    rownum new,
    e.*
from (
    select
        emp_id,
        emp_name,
        (select dept_title from department where dept_id = e.dept_code) 부서명,
        hire_date
    from
        employee e
    order by
        hire_date desc -- << 정렬 필요
) e
where
    rownum between 1 and 5;

-- 직급이 대리인 사원 중에서 급여 top3 조회 (사번, 사원명, 직급명, 급여)
select
    *
from (
    select
        emp_id,
        emp_name,
        job_code,
        job_name,
        salary
    from
        employee e join job j
            using(job_code)
    where
        j.job_name = '대리'
    order by
        salary desc
)
where
    rownum <= 3;


-- 부서별 급여 평균 top3 조회 (부서명, 평균급여(버림))

select
    e.*
from (
    select
        dept_code,
        trunc(avg(salary)) avg_sal
    from
        employee
    group by
        dept_code
    order by
        2 desc
)e
where
    rownum <=3;
    
-- offset이 있는 Top-N 분석
-- rownum은 from...where절이 끝나야 부여가 완성된다
-- where절에서는 1부터 접근하는 경우는 rownum 사용 가능
-- offset이 발생하면 하나의 inline-view를 추가로 사용해야 함
select
    *
from (
    select
        rownum rnum,
        e.*
    from (
        select
            emp_name,
            salary
        from
            employee 
        order by
            salary desc
    ) e
)
where
    rnum between 6 and 10;


-- 부서별 급여 평균 4위~6위 조회

select
    *
from (
    select
        rownum rnum, -- 별칭을 붙여야 바깥에서 참조할 수 있음
        e.*
    from (
        select
            dept_code,
            trunc(avg(salary)) avg_sal
        from
            employee
        group by
            dept_code
        order by
            2 desc
    )e
)
where
    rnum between 4 and 6;

------------------------------------
-- 계층형 쿼리
------------------------------------
-- join은 특정 컬럼 기준으로 수평적으로 행을 연결하는데 반해, 계층형 쿼리는 특정 컬럼 기준으로 수직적으로 행을 연결한다
-- 부모행 - 자식행 연결
-- 조직도, 메뉴, 답변형 게시판 표현 시 사용

-- start with : 루트행을 지정
-- connect by : 부모-자식행을 연결할 조건절 작성 prior 키워드를 부모행 컬럼에 작성
-- level 의사(pesedo : 가상) 컬럼 : 계층레벨 표현

-- 1. 하향식
-- 사원테이블의 조직도
-- employee.emp_id(부모행) <--------- employee.manager_id(자식행)
select
    level,
    lpad(' ',(level-1)*5) || emp_name 조직도,
    e.*
from
    employee e -- 루트에서 부터 내려오면서 탐색할 때, 연결고리가 없는 애들은 조회에서 제외됨
start with
    emp_id = '200' -- 최상위 행 지정 (n개 지정 가능)
connect by
    prior emp_id = manager_id;


-- 2. 상향식
-- 결재선 작성에 유용

-- 윤은해 사원의 결재선 조회
select
    emp_id,
    emp_name,
    manager_id
from
    employee
start with
    emp_name = '윤은해'
connect by
    prior manager_id = emp_id; -- 사향식 탐색이니까 부모 자식 관계를 뒤집어서 지정함
    

create table tbl_menu(
    no number primary key, -- 고유식별컬럼
    menu_name varchar2(100),
    parent_no number references tbl_menu(no) -- 참조컬럼 parent_no -> no
);

insert into tbl_menu values(100, '주메뉴1', null);
insert into tbl_menu values(200, '주메뉴2', null);
insert into tbl_menu values(300, '주메뉴3', null);
insert into tbl_menu values(1000, '서브메뉴A', 100);
insert into tbl_menu values(2000, '서브메뉴B', 200);
insert into tbl_menu values(3000, '서브메뉴C', 300);
insert into tbl_menu values(1001, '상세메뉴A1', 1000);
insert into tbl_menu values(1002, '상세메뉴A2', 1000);
insert into tbl_menu values(1003, '상세메뉴A3', 1000);
insert into tbl_menu values(2001, '상세메뉴B1', 2000);
insert into tbl_menu values(3001, '상세메뉴C1', 3000);

select * from tbl_menu;

select
    lpad(' ',(level-1)*5) || menu_name || '(' || no || ')' menu
from
    tbl_menu
start with
    parent_no is null
connect by
    prior no = parent_no;

------------------------------------
-- WINDOW 함수
------------------------------------
-- 행과 행간의 관계를 쉽게 정의하기 위한 함수
-- Ansi 표준 함수
-- select 절에서만 사용 가능
-- 순위관련 | 집계관련 | 순서관련 | 비율관련 | 통계관련

-- window_function (arguments) over ([partition by절] [order by절] [windowing절])
-- arguments 윈도우 함수 인자 (0~n개)
-- partition by 절 : 윈도우 함수 내 group by
-- order by 절 : 윈도우 함수 내 order by
-- windowing 절 : 대상 행 지정


--+++++++++++++++++++++++++++++++++++
-- 순위관련
--+++++++++++++++++++++++++++++++++++
-- rank() over()
-- 순위부여, 중복된 값이 있다면 다음 순위는 중복된 개수만큼 건너뜀
select
    emp_name,
    salary,
    dept_code,
    rank() over(order by salary desc) rank, -- 전체 급여 내림차순
    rank() over(partition by dept_code order by salary desc) rank_2 -- 부서 내 급여 내림차순
    -- partition by : 내부적으로만 grouping했을 뿐, 출력했을 땐 모두가 출력됨
from
    employee
order by
    dept_code;

-- dense_rank() over()
-- 순위부여, 중복된 값이 있어도 다음 순위는 건너뛰지 
select
    emp_name,
    salary,
    dense_rank() over(order by salary desc) rank
from
    employee;

-- 입사일이 빠른 순서대로 순위 조회 (사번, 사원명, 입사일, 순위)
select
    emp_id,
    emp_name,
    hire_date,
    rank() over(order by hire_date asc) 순위
from
    employee;

-- 부서 내 입사일이 빠른 순서대로 순위 조회 (사번, 사원명, 입사일, 순위)
select
    emp_id,
    emp_name,
    dept_code,
    hire_date,
    rank() over(partition by dept_code order by hire_date asc ) rank
from
    employee
order by
    dept_code;

-- row_number() over()
-- 중복된 값이 있어도 순위는 중복되지 않는다 --> 페이징 처리 적합
select
    emp_name,
    salary,
    row_number() over (order by salary desc) rank
from
    employee;

-- 윈도우 함수로 top-n 분석을 처리하면 offset이 있어도 단 한번의 inline-view만 사용하면 된다
select
    *
from (
    select
        emp_name,
        salary,
        row_number() over (order by salary desc) rank
    from
        employee
)
where
    rank between 6 and 10;


-- 부서별 급여 평균 top3 조회 (부서명, 평균급여(버림))
select
    *
from (
    select
        dept_code,
        trunc(avg(salary)) avg_sal,
        rank() over(order by trunc(avg(salary)) desc) rank
    from
        employee
    group by
        dept_code
)
where
    rank between 1 and 3;
-- 부서별 급여 평균 4위~6위 조회
select
    *
from (
    select
        dept_code,
        trunc(avg(salary)) avg_sal,
        rank() over(order by trunc(avg(salary)) desc) rank
    from
        employee
    group by
        dept_code
)
where
    rank between 4 and 6;
    
--+++++++++++++++++++++++++++++++++++
-- 집계관련
--+++++++++++++++++++++++++++++++++++
-- 합계, 평균관련 윈도우 함수

-- sum() over()
-- order by와 함께 사용하면 누계를 확인 가능
select
    emp_name,
    salary,
    dept_code,
    sum(salary) over() 전체급여합,
    sum(salary) over(partition by dept_code) 부서별급여합,
    sum(salary) over(partition by dept_code order by salary) "부서별급여누계(급여오름차순)"
from
    employee;

-- 지난 3개월 판매 데이터에서 월별 총 판매량 조회
select
    to_char(sale_date, 'yyyy-mm')  판매월,
    p_name 제품명,
    P_count 판매수량,
    sum(p_count) over(partition by p_name order by sale_date) 제품별누계,
    sum(p_count) over(order by sale_date) 전체판매누계
from (
    select
        *
    from
        tbl_sales
    union all
    select
        *
    from
        tbl_sales_2302
    union all
    select
        *
    from
        tbl_sales_2303
)
order by
    sale_date;


-- avg() over()
select
    emp_name,
    salary,
    dept_code,
    trunc(avg(salary) over()) 전체사원급여평균,
    trunc(avg(salary) over(partition by dept_code)) 부서별급여평균
from    
    employee;

-- list_agg() within group()
-- 컬럼값을 합쳐서 하나의 결과물로 반환
select
    listagg(emp_name, ',') within group (order by emp_name) 전사원이름
from
    employee;

-- 부서별 사원명
select
    dept_code,
    listagg(emp_name, '|') within group (order by emp_name) 부서원
from  
    employee
group by
    dept_code;
    
-- 입사년도별 인원수 조회
/*
    1번 방법
    1990 1991 1992 ...
    ------------------
      2    3    0 ...

    2번 방법
    =============      
    1990 2
    1991 3
    1992 0
    ...
    =============
*/

-- 1번 방법
select
    sum(decode(extract(year from hire_date), 1998, 1,0)) "1998",
    sum(decode(extract(year from hire_date), 1999, 1,0)) "1999",
    sum(decode(extract(year from hire_date), 2000, 1,0)) "2000",
    sum(decode(extract(year from hire_date), 2001, 1,0)) "2001",
    sum(decode(extract(year from hire_date), 2002, 1,0)) "2002",
    sum(decode(extract(year from hire_date), 2003, 1,0)) "2003",
    sum(decode(extract(year from hire_date), 2004, 1,0)) "2004",
    sum(decode(extract(year from hire_date), 2005, 1,0)) "2005"
from
    employee;

-- 2번 방법    
select
    extract(year from hire_date) year,
    count(*)
from
    employee
group by
    extract(year from hire_date)
order by
    year;

-- 임의의 행 생성하기
select
    level + 1989 year
from
    dual
connect by
    level <= 30;


SELECT
    y.year,
    nvl(e.cnt, 0) cnt
FROM(
    SELECT
        EXTRACT(YEAR FROM hire_date) year,
        COUNT(*) cnt
    FROM
         employee
     GROUP BY
        EXTRACT(YEAR FROM hire_date)
    ) e
    RIGHT JOIN (
    SELECT
        level + 1989 year
    FROM
        dual
    CONNECT BY
        level <= 30
    ) y ON e.year = y.year
ORDER BY
    year;

--문제1. 기술지원부에 속한 사람들의 사람의 이름,부서코드,급여를 출력하시오
select
    emp_name,
    dept_code,
    salary
from
    employee
where
    dept_code = (select dept_code from department where dept_title = '기술지원부');

-- 문제2. 기술지원부에 속한 사람들 중 가장 연봉이 높은 사람의 이름,부서코드,급여를 출력하시오
select
    emp_name,
    dept_code,
    salary
from
    employee 
where
    salary = (select max(salary) from employee where dept_code = (select dept_id from department where dept_title ='기술지원부'));

--문제3. 매니저가 있는 사원중에 월급이 전체사원 평균보다 많은 사원의  사번,이름,매니저 이름, 월급을 구하시오. 
--    1. JOIN을 이용하시오
select
    e.emp_id,
    e.emp_name,
    m.emp_name 매니저이름,
    e.salary
from
    employee e join employee m on -- inner join하면 null값은 자동으로 제외되기 때문에 where절에 manager_id is not null과 같은 조건을 붙일 필요가 없음
    m.emp_id = e.manager_id
where
    e.salary > (select avg(salary) from employee);

--    2. JOIN하지 않고, 스칼라상관쿼리(SELECT)를 이용하기
select
    emp_id,
    emp_name,
    (select emp_name from employee where emp_id = m.manager_id) 매니저이름,
    salary
from
    employee m
where
    manager_id is not null
    and
     salary > (select avg(salary) from employee);

-- 문제4. 같은 직급의 평균급여보다 같거나 많은 급여를 받는 직원의 이름, 직급코드, 급여, 급여등급 조회
select
    emp_name,
    dept_code,
    salary,
    sal_level
from
    employee e
where
    salary >= (select avg(salary) from employee where job_code = e.job_code);
    
--문제5. 부서별 평균 급여가 3000000 이상인 부서명, 평균 급여 조회. 단, 평균 급여는 소수점 버림, 부서명이 없는 경우 '인턴'처리
select
    (select nvl(dept_title,'인턴') from department where dept_id = e.dept_code) 부서명,
    e.*
from (
    select
        dept_code,
        trunc(avg(salary)) avg_sal
    from
        employee
    group by
        dept_code
) e
where
    avg_sal >= 3000000;

-- 문제6. 직급의 연봉 평균보다 적게 받는 여자사원의 사원명,직급명,부서명,연봉을 이름 오름차순으로 조회하시오 연봉 계산 => (급여 + (급여*보너스))*12    
select
    e.*
from (
    select
        emp_name,
        emp_no,
        job_name,
        dept_title,
        (salary + (salary * nvl(bonus,0)))*12 연봉,
        (select trunc(avg((salary + (salary * nvl(bonus,0)))*12)) from employee where job_code = e.job_code) 직급평균연봉,
        decode(substr(emp_no,8,1), '2','여','4','여','남') gender
    from
        employee e 
        join job j on e.job_code = j.job_code
        join department d on e.dept_code = d.dept_id      
)e
where
    직급평균연봉 > e.연봉
    and
    gender = '여'
order by
    e.emp_name asc;

-- 두번째 풀이
-- with : inline view를 별칭으로 참조
with my_emp
as
(
    select 
        emp_name,
        nvl((select dept_title from department where dept_id = E.dept_code), '인턴') dept_title, 
        job_code,
        (select job_name from job where job_code = E.job_code) job_name,
        (salary + salary * nvl(bonus, 0)) * 12 annual_salary,
        decode(substr(emp_no,8,1), '2','여','4','여','남') gender
    from 
        employee E
)
select 
    *
from 
    my_emp E
where 
    gender = '여' 
    and 
    annual_salary < (
                    select avg((salary+salary*nvl(bonus,0))*12) 
                    from employee 
                    where job_code = E.job_code
                )
order by emp_name;
 

    
--문제7. 다음 도서목록테이블을 생성하고, 공저인 도서만 출력하세요. (공저 : 두명이상의 작가가 함께 쓴 도서)
create table tbl_books (
book_title  varchar2(50)
,author     varchar2(50)
,loyalty     number(5)
);

insert into tbl_books values ('반지나라 해리포터', '박나라', 200);
insert into tbl_books values ('대화가 필요해', '선동일', 500);
insert into tbl_books values ('나무', '임시환', 300);
insert into tbl_books values ('별의 하루', '송종기', 200);
insert into tbl_books values ('별의 하루', '윤은해', 400);
insert into tbl_books values ('개미', '장쯔위', 100);
insert into tbl_books values ('아지랑이 피우기', '이오리', 200);
insert into tbl_books values ('아지랑이 피우기', '전지연', 100);
insert into tbl_books values ('삼국지', '노옹철', 200);
insert into tbl_books values ('별의 하루', '대북혼', 300);

-- 풀이법1 (group by having 사용)
SELECT 
    book_title,
    count(book_title) 저자수
FROM 
    tbl_books
GROUP BY 
    book_title
HAVING
    count(book_title)>=2;

-- 풀이법2 (상관서브쿼리사용)
SELECT
    *
FROM
    tbl_books a
WHERE
    book_title IN (
        SELECT
            book_title
        FROM
            tbl_books b
        WHERE
            book_title = a.book_title
            AND author != a.author
    );

-- 풀이법3 (상관서브쿼리와 exists( ) 사용)
select 
    *
from 
    tbl_books A
where 
    exists (
        select 
            1 
        from 
            tbl_books B
        where 
            book_title = A.book_title 
            and 
            author != A.author);
            
-- 풀이법4 (조인 사용)
select distinct 
    a.book_title
from 
    tbl_books A join tbl_books B
        on A.book_title = B.book_title -- 제목은 같고 저자가 다르면 붙여라
            and A.author != B.author;

--===================================================--
-- DML
--===================================================--
-- Data Manipulation Language 데이터 조작어
-- 테이블의 행을 조작하는 sql문
-- 추가(insert) 수정(update) 삭제(delete) 조회(select)

------------------------------------
-- insert
------------------------------------
-- 테이블의 행을 추가하는 명령어
-- 실행 결과 테이블 행 수가 증가

-- 문법1 (컬럼 지정 없음)
-- 테이블의 컬럼 순서, 개수를 정확히 일치 시켜야 함
-- insert into 테이블 values (컬럼1 값, 컬럼2 값, ...)

-- 문법2 (컬럼 지정)
-- 지정한 컬럼에 대해서만 values 절에서 순서대로 값을 작성
-- 컬럼을 생략할 수 있음 (not null 컬럼 생략 불가)
-- insert into 테이블 (컬럼1, 컬럼2, ...) values (컬럼1 값, 컬럼2 값, ...)

CREATE TABLE sample (
    a NUMBER,
    b NUMBER DEFAULT 10,
    c VARCHAR2(20) NOT NULL,
    d DATE DEFAULT sysdate NOT NULL -- null을 추가할 수 없다
);

desc sample; -- 테이블 구조 확인

-- 문법1
-- 컬럼별 자료형이 일치해야 한다
-- 컬럼수가 정확해야 한다
-- not null 컬럼에는 null 대입 불가

INSERT INTO sample VALUES (
    10,
    20,
    '안녕',
    TO_DATE('1999-09-09', 'yyyy-mm-dd')
);

INSERT INTO sample VALUES (
    11, DEFAULT,
    '잘가', DEFAULT
);


-- 문법2
-- 생략된 컬럼은 null 또는 지정한 기본 값이 대입된다.
-- not null 컬럼은 반드시 값 지정해줘야 한다
-- not null 컬럼도 기본값을 지정했다면 생략할 수 있다
insert into
    sample(c,d)
values (
    '여보세요', default
);
insert into
    sample(b) -- ORA-01400: NULL을 ("SH"."SAMPLE"."C") 안에 삽입할 수 없습니다
values (
    200
);

select * from sample;
-- DML은 commit 명령을 통해 실제 db에 방영된다
-- rollback 명령은 작업내용을 취소하고 마지막 commit 시점으로 돌아간다. 단 undo가 아니다.

-- 연습용 employee_ex
create table employee_ex
as
select * from employee;
-- 서브쿼리를 이용한 테이블 생성시에는 not null 제약 조건을 제외한 
-- pk, fk, unique, check 제약조건과 지정된 기본값은 모두 제외된다
alter table
    employee_ex
add constraint pk_employee_ex primary key(emp_id) -- 기본키 : 고유식별컬럼
add constraint uq_employee_ex_emp_bo unique(emp_no) -- 중복허용X
add constraint fk_employee_ex_dept_code foreign key(dept_code) references department(dept_id) -- 외래키
add constraint fk_employee_ex_job_code foreign key(job_code) references job(job_code) -- 외래키
add constraint ck_employee_ex_quit_yn check (quit_yn in ('Y', 'N')) -- 체크 제약 도메인 지정
modify quit_yn default 'N'
modify hire_date default sysdate;

select * from employee_ex;
desc employee_ex;

commit;
-- 3명의 데이터 추가
-- 3번짼 not null 컬럼만 작성된 본인 데이터 추가
insert into
    employee_ex (EMP_ID,EMP_NAME,EMP_NO,JOB_CODE,SAL_LEVEL,MANAGER_ID) -- ORA-01400: NULL을 ("SH"."SAMPLE"."C") 안에 삽입할 수 없습니다
values (
    '224','김상훈','910101-1000000','J5','S5','225'
);

insert into
    employee_ex (EMP_ID,EMP_NAME,EMP_NO,JOB_CODE,SAL_LEVEL) -- ORA-01400: NULL을 ("SH"."SAMPLE"."C") 안에 삽입할 수 없습니다
values (
    '225','김담희','940908-2000000','J3','S3'
);

insert into
    employee_ex (EMP_ID,EMP_NAME,EMP_NO,JOB_CODE,SAL_LEVEL) -- ORA-01400: NULL을 ("SH"."SAMPLE"."C") 안에 삽입할 수 없습니다
values (
    '226','문소희','941008-2000000','J2','S2'
);

insert into
    employee_ex  -- ORA-01400: NULL을 ("SH"."SAMPLE"."C") 안에 삽입할 수 없습니다
values (
    '301','함지민','781020-2123453', 'hamham@kh.or.kr', '01012343334', 'D1', 'J4', 'S3', 4300000, 0.2, '200', default, null, default
);

insert into
    employee_ex  -- ORA-01400: NULL을 ("SH"."SAMPLE"."C") 안에 삽입할 수 없습니다
values (
    '302','장채현','901123-1080503', 'jang_ch@kh.or.kr', '01012343334', 'D2', 'J7', 'S3', 3500000, null, '201', default, null, default
);

select * from employee_ex;

-- 서브쿼리를 이용한 insert
-- 사번, 사원명, 부서명 정보만 따로 관리
create table employee_ex2 (
    emp_id varchar2(3),
    emp_name varchar2(50),
    dept_title varchar2(100)
);
insert into employee_ex2(emp_id, emp_name, dept_title) ( -- values를 적지 않는다
    select
        emp_id,
        emp_name,
        (select dept_title from department where dept_id = e.dept_code) dept_title
    from
        employee e
);

select * from employee_ex2;

-- insert all을 이용해서 여러테이블에 데이터 이전하기
-- employee_job, employee_manager
create table employee_job
as
select emp_id, emp_name, '직급명직급명' job_name from employee where 1 = 0;
-- where 1 = 0이 무조건 false이기 때문에, 값은 넘어가지 않고 테이블의 구조만 만들어진다
-- drop table employee_job;
select * from employee_job;
desc employee_job;

create table employee_manager
as
select emp_id, emp_name, manager_id, emp_name manager_name from employee where 1=0;

alter table employee_manager
modify manager_name null; -- not null 제약 조건 -> nullable로 변경

select * from employee_manager;
desc employee_manager;

insert all
into employee_job values(emp_id, emp_name, job_name)
into employee_manager values(emp_id, emp_name, manager_id, manager_name)
select
    emp_id,
    emp_name,
    manager_id,
    (select emp_name from employee where emp_id = e.manager_id) manager_name,
    (select job_name from job where job_code = e.job_code) job_name
from
    employee e;

-- insert all 이용해서 하나의 테이블에 여러 데이터 동시 insert
create table tbl_str (
    str varchar2(50)
);
insert all 
    into tbl_str values('hello')
    into tbl_str values('byebye')
    into tbl_str values('ㅌㅌㅌ')
select * from dual; -- insert all 자체가 하위에 서브쿼리가 있어야 하기 때문에, 임의의 테이블 사용

select * from tbl_str;


------------------------------------
-- UPDATE
------------------------------------
-- 테이블에 저장된 행의 특정 컬럼값을 수정하는 명령
-- 실행 전후로 행의 수는 변화가 없다
-- where절에 따라 0행 또는 여러행이 수정될 수 있다

select * from employee_ex;

update
    employee_ex
set
    emp_name = '송종식',
    dept_code = 'D8'
where
    emp_id = '201';

-- employee_ex 테이블에서 D5 부서원들의 급여를 모두 500000원 인상처리
update
    employee_ex
set
    salary = salary + 500000
where
    dept_code = 'D5';

-- 서브쿼리 이용한 update
-- 방명수 사원의 직급을 유재식과 동일하게 수정
select * from employee_ex where emp_name = '방명수';
update
    employee_ex
set
    job_code = (select job_code from employee_ex where emp_name = '유재식')
where
    emp_name = '방명수';
    
-- 임시환 사원의 직급을 과장, 부서를 해외영업 3부로 수정
select
    emp_name,
    (select job_name from job where job_code = e.job_code) job_name,
    (select dept_title from department where dept_id = e.dept_code) dept_title
from
    employee_ex e
where
    emp_name = '임시환';

select * from employee_ex;


update
    employee_ex
set
    job_code = (select job_code from job where job_name = '과장'),
    dept_code = (select dept_id from department where dept_title = '해외영업3부')
where
    emp_name = '임시환';


------------------------------------
-- DELETE
------------------------------------
-- 테이블에 저장된 행을 삭제하는 명령
-- 실행 후 행 수가 감소
-- where절에 따라 0행 또는 여러행이 삭제처리 될 수 있다
select * from employee_ex;

delete from
    employee_ex
where
    emp_id = '224';
    
------------------------------------
-- TRUNCATE
------------------------------------
-- 테이블 데이터를 잘라내는 DDL
-- 모든 DDL문(create, alter, drop)은 실행과 동시에 commit 된다
-- 테이블의 모든 데이터를 삭제 (복구안됨)
-- delete from과 비교해서 처리 속도가 빠름 (DML의 before image 작업이 없다)

truncate table employee_ex;

-- insert 서브쿼리 이용해서 employee 모든 데이터 추가하기
insert into
    employee_ex (select * from employee); -- values 쓰면 안됨
    
select * from employee_ex;


--===================================================--
-- DDL
--===================================================--
-- Data Definition Language 데이터 정의어
-- db 객체에 대해 생성 create 수정 alter 삭제 drop 하는 명령어
-- rename, truncate 등
-- 자동커밋되므로 유의해야 한다.

-- table, user, view, sequence, index, procedure, function, trigger, synonym ...

------------------------------------
-- CREATE
------------------------------------
-- 테이블/컬럼 주석
create table member (
    id varchar2(20),
    password varchar2(20),
    name varchar2(50)
);

-- 테이블 주석
comment on table member is '회원테이블'; -- 별도의 수정 메소드 없이, 덮어쓰기하면 됨
comment on table member is ''; -- 삭제 : null값('') 대입

select * from user_tab_comments where table_name = 'MEMBER'; -- comment 확인 방법
                                                             -- 테이블명은 대문자로 검색해야 함
-- 컬럼 주석
comment on column member.id is '회원아이디';
comment on column member.password is '비밀번호';
comment on column member.name is '회원명';

select * from user_col_comments where table_name = 'MEMBER';


--+++++++++++++++++++++++++++++++++++
-- 제약조건 constraints
--+++++++++++++++++++++++++++++++++++
-- 테이블 생성시 각 컬럼에 대해 제약조건을 설정할 수 있다
-- 데이터 무결성을 지키기 위해 제한하는 조건
-- 데이터 무결성이란? 데이터의 일관성과 정확성을 유지하는 것

-- 1. not null 데이터에 null 값을 허용하지 않음. 필수 입력 컬럼
-- 2. unique 데이터에 중복 값을 허용하지 않음. 이메일, 주민번호 등에 적용
-- 3. primary key 기본키(주키) 고유 식별 컬럼. 레코드를 구별할 수 있는 컬럼.
-- 4. foreign key 외래키. 다른 테이블의 컬럼 값을 참조하는 컬럼 (해당 컬럼에 존재하는 값만 사용 가능)
-- 5. check 도메인을 지정. (값 목록/값 범위) 퇴사여부(Y/N). 점수(0~100)

-- 컬럼 레벨 : 컬럼명 옆에 작성
-- 테이블 레벨 : 컬럼과 나란히 작성 -> 제약조건 이름과 함께 테이블 레벨에 작성하는 것이 가장 좋다

/*
    create table member (
        id varchar2(20) primary key, -- 컬럼레벨 << 비추! 구분하기 힘듦
        password varchar2(20),
        name varchar2(50),
        primary ket(id) -- 테이블레벨
    );
*/

--************************************
-- NOT NULL
--************************************
-- not null 제약조건은 유일하게 컬럼 레벨에만 작성이 가능하다
-- 제약 조건 이름도 작성할 필요 없음
-- drop table member;
create table member (
    id varchar2(20) not null,
    password varchar2(20) default '1234' not null, -- 기본값은 not null 앞에 작성
    name varchar2(50) not null
);

insert into member values('honggd', null, null); --ORA-01400: NULL을 ("SH"."MEMBER"."PASSWORD") 안에 삽입할 수 없습니다

--************************************
-- UNIQUE
--************************************
-- 중복값 허용하지 않음
-- 주민번호, 이메일, 닉네임 적용
-- 전화번호에 적용하지 말 것

-- drop table member;
create table member ( id VARCHAR2(20), password VARCHAR2(20) NOT NULL, -- 기본값은 not null 앞에 작성
 name VARCHAR2(50) NOT NULL,
--    email varchar2(100) unique, -- 컬럼레벨
 email VARCHAR2(100) NOT NULL, -- unique + not null
constraints uq_member_email UNIQUE(email) );
insert into member values('honggd', '1234', '홍길동', 'honggd@naver.com');
insert into member values('honggd', '1234', '홍기동', 'honggd@naver.com'); -- ORA-00001: 무결성 제약 조건(SH.UQ_MEMBER_EMAIL)에 위배됩니다
insert into member values('sinsa', '1234', '신사임당', null); -- unique 제약조건 걸려있어도 null값은 허용하기 때문에 따로 제약조건을 설정해줘야 함

-- 컬럼 여러개를 묶어서 복합 unique 제약조건을 걸 수도 있다
-- unique(컬럼1, 컬럼2, ...) 여러 컬럼 값을 합쳐서 유일해야 한다

-- 제약조건 조회
-- constraints_name, constraints_type(C(not null, check) | U | P | R), table_name, search_condition 등 확인 가능
select * from user_constraints where table_name = 'MEMBER';
select * from user_cons_columns where table_name = 'MEMBER'; -- column명 조회

-- 제약조건 조회 (컬럼명 포함)
select
    ucc.column_name,
    uc.*
from
    user_constraints uc join user_cons_columns ucc
    on uc.constraint_name = ucc.constraint_name
where
    uc.table_name = 'MEMBER';

--************************************
-- PRIMARY KEY
--************************************
-- 레코드를 구분하기 위한 고유식별 컬럼에 적용
-- not null과 unique 제약이 동시에 적용됨. 한 테이블 당 하나의 기본키만 적용이 가능하다.
-- 단일 기본키 또는 복합 기본키(컬럼 여러개) 가능

-- drop table member;
create table member (
    id varchar2(20),
    password varchar2(20) not null,
    name varchar2(50) not null,
    email varchar2(100) not null, -- unique + not null
    constraints uq_member_email unique(email),
    constraints pk_member_id primary key(id)
);

insert into member values('honggd', '1234', '홍길동', 'honggd@naver.com');
insert into member values('sinsa', '1234', '신사임당', 'sinsa@naver.com');
insert into member values('leess', '1234', '이순신', 'leess@naver.com');
insert into member values('gdgd', '1234', '홍길동', 'gdgd@naver.com');
insert into member values(null, '1234', '홍길동', 'gdgd@naver.com'); -- ORA-01400: NULL을 ("SH"."MEMBER"."ID") 안에 삽입할 수 없습니다

select * from member where id = 'leess'; -- 고유식별컬럼의 경우 조회 결과는 무조건 1행이여야 함 (식별컬럼O)
select * from member where name = '홍길동'; -- 조회 결과가 n행일 수 있다 (식별컬럼X)

-- 복합 PK
-- 여러 컬럼을 묶어서 고유식별 컬럼으로 사용
create table tb_order(
    product_no varchar2(100),
    user_id varchar2(100),
    order_date date,
    cnt number default 1,
    constraints pk_tb_order primary key(product_no, user_id, order_date) -- 테이블 레벨에만 작성 가능
);
insert into tb_order values('p1234', 'honggd', sysdate, default); -- p_no, u_id값이 같아도 sysdate 덕분에 고유하게 식별이 가능함
insert into tb_order values('p1234', null, sysdate, default); -- ORA-01400: NULL을 ("SH"."TB_ORDER"."USER_ID") 안에 삽입할 수 없습니다

select * from tb_order;

select
    product_no,
    user_id,
    to_char(order_date, 'yy/mm/dd hh24:mi:ss') order_date,
    cnt
from
    tb_order;
    
select
    ucc.column_name,
    uc.*
from
    user_constraints uc join user_cons_columns ucc
    on uc.constraint_name = ucc.constraint_name
where
    uc.table_name = 'TB_ORDER';   

--************************************
-- FOREIGN KEY
--************************************
-- 외래키
-- 참조 무결성을 유지하기 위한 제약조건
-- 참조하는 테이블(부모)에서 제공하는 값만 사용하도록 제한을 거는 것
-- null값 허용
-- 참조하는 테이블의 해당 컬럼은 pk 또는 uq 제약조건이 걸려있어야 함
-- drop table shop_member;
-- drop table shop_purchase;
-- drop table shop_nickname;

-- shop_member
create table shop_member(
    id varchar2(20),
    name varchar2(100) not null,
    constraints pk_shop_member_id primary key(id)
);

insert into shop_member values('honggd', '홍길동');
insert into shop_member values('sinsa', '신사임당');
insert into shop_member values('yoogs', '유관순');

select * from shop_member;

-- shop_purchase
-- fk 삭제 옵션 : 부모레코드 삭제 시 자식레코드 처리 여부
-- 1. on delete restricted - 자식 레코드 존재하는 경우 부모 레코드 삭제 불가
-- 2. on delete set null - 부모 레코드 삭제 시 자식 레코드의 fk 컬럼을 null로 수정
-- 3. on delete cascade - 부모 레코드 삭제 시 자식 레코드도 따로서 삭제

create table shop_purchase (
    no number, -- 구매번호(pk)
    member_id varchar2(20),
    product_id varchar2(50),
    cnt number default 1,
    reg_date date default sysdate,
    constraints pk_shop_purchase_no primary key(no),
    constraints fk_shop_purchase_member_id foreign key(member_id) references shop_member(id) on delete cascade
    -- on delete set null
        
);
insert into shop_purchase values(1, 'honggd', 'p1234', 5, sysdate);
insert into shop_purchase values(2, 'sinsa', 'p555', 10, sysdate);
insert into shop_purchase values(3, 'honggd', 'p9999', 2, sysdate);
insert into shop_purchase values(4, 'sejong', 'p888', 2, sysdate); --ORA-02291: 무결성 제약조건(SH.FK_SHOP_PURCHASE_MEMBER_ID)이 위배되었습니다- 부모 키가 없습니다


select * from shop_purchase;

-- 부모레코드 삭제
delete from shop_member where id = 'honggd'; -- ORA-02292: 무결성 제약조건(SH.FK_SHOP_PURCHASE_MEMBER_ID)이 위배되었습니다- 자식 레코드가 발견되었습니다
-- 자식레코드 먼저 삭제 후, 부모 레코드 삭제하면 됨
delete from shop_purchase where member_id = 'honggd';
--
-- 식별관계 : 부모키를 다시 기본키로 사용하는 경우. fk컬럼이 동시에 pk컬럼으로 사용, 딱 한번만 참조 가능
-- 비식별관계 : 부모키를 다시 기본키로 사용하지 않는 경우. fk컬럼 중복 사용 가능, 여러번 참조 가능
-- shop_member (부모) --- shop_nickname (자식)
-- 1 : 1 관계
create table shop_nickname (
    member_id varchar2(20),
    nickname varchar2(100),
    constraints fk_shop_nickname_member_id foreign key(member_id) references shop_member(id),
    constraints pk_shop_nickname_member_id primary key(member_id)
);

insert into shop_nickname values('sinsa', '신어머니');
insert into shop_nickname values('honggd', '홍아들');
insert into shop_nickname values('yoogs', '유관수니');

select * from shop_nickname;

-- 조인
-- 아이디 이름 별명 구매물품 수량 구매일자
SELECT
    *
FROM
    shop_member s
    LEFT JOIN shop_purchase p ON s.id = p.member_id
    LEFT JOIN shop_nickname n ON p.member_id = n.member_id;
    
--************************************
-- CHECK
--************************************
-- 도메인을 지정. 값 목록/값 범위 지정

-- drop table member;
create table member (
    id varchar2(20),
    password varchar2(20) not null,
    name varchar2(50) not null,
    email varchar2(100) not null, -- unique + not null
    gender char(1),
    point number default 1000,
    constraints uq_member_email unique(email),
    constraints pk_member_id primary key(id),
    constraints ck_member_gender check(gender in('M','F')),
    constraints ck_member_point check(point >= 0)
);
insert into member values('honggd', '1234','홍길동','honggd@naver.com','M',default);
insert into member values('sinsa', '1234','신사임당','sinsa@naver.com','f',default); --ORA-02290: 체크 제약조건(SH.CK_MEMBER_GENDER)이 위배되었습니다
insert into member values('sinsa', '1234','신사임당','sinsa@naver.com','F',-1000); -- ORA-02290: 체크 제약조건(SH.CK_MEMBER_POINT)이 위배되었습니다
select * from member;

update
    member
set
--    gender = 'm' -- ORA-02290: 체크 제약조건(SH.CK_MEMBER_GENDER)이 위배되었습니다
    point = point - 2000
where
    id = 'honggd';

------------------------------------
-- ALTER
------------------------------------
-- db 객체에 대한 수정

-- table 수정하기 (컬럼/제약조건에 대한 수정)
-- add 컬럼/제약조건 추가
-- modify 컬럼(자료형, default값, not null 등)
-- rename 컬럼/제약조건
-- drop 컬럼/제약조건

-- 제약조건은 이름변경 외에는 수정할 수 없다 (삭제 후 재생성)
-- not null 제약조건은 add가 아니라 modify 명령어로 처리해야 한다

create table foo (
    no number,
    username varchar2(20),
    password varchar2(20)
);

-- add 컬럼 (자료형/기본값/not null 지정가능)
-- 컬럼은 맨 마지막에만 추가 가능하다
alter table 
    foo
add
    name varchar2(50) default '김민호' not null;

-- add 제약조건
-- pk, uq, fk, ck만 가능 (not null은 modify로 수정한다)
alter table
    foo
add
    constraints pk_foo_no primary key(no);

-- modify 컬럼
-- 자료형 변경, 기본값 변경, not null 추가 또는 제거

ALTER TABLE foo MODIFY
    password VARCHAR2(300) DEFAULT '1234' NOT NULL;


ALTER TABLE foo MODIFY
    password DEFAULT '' NULL; -- 기본값 제거, not null -> null로 수정    

-- rename 컬럼
ALTER TABLE foo RENAME COLUMN password TO pwd;

-- rename 제약조건
ALTER TABLE foo ADD email VARCHAR2(100) UNIQUE; -- 이름을 지정해주지 않으면  SYS_C008498 와 같은 자동 이름 생성됨

ALTER TABLE foo RENAME CONSTRAINTS SYS_C008498 TO UQ_FOO_EMAIL ;

select
    ucc.column_name,
    uc.*
from
    user_constraints uc join user_cons_columns ucc
    on uc.constraint_name = ucc.constraint_name
where
    uc.table_name = 'FOO'; 

-- drop 컬럼
alter table
    foo
drop -- 여기서의 drop은 alter의 서브 명령어로 drop table과는 무관함
    column email;
    
-- drop 제약조건
-- pk, ck, not null, uq, sk 다 제거 가능함
alter table
    foo
drop
    constraints pk_foo_no;

alter table
    foo
drop
    constraints SYS_C008495; -- not null 제약조건

------------------------------------
-- RENAME
------------------------------------
-- 테이블명 변경가능
rename foo to bar; 

------------------------------------
-- DROP
------------------------------------
-- 객체 삭제
drop table bar;


--===================================================--
-- DCL
--===================================================--
-- Data Control Language 데이터 제어어
-- DB 객체에 대한 권한 부여 / 회수, 트랜잭션 처리 등을 담당하는 명령어
-- TCL이 포함하는 개념

-- qwerty 계정 생성 @system
-- qwerty / qwerty
-- drop user qwerty;
alter session set "_oracle_script" = true;
create user qwerty 
identified by qwerty
default tablespace users;

-- alter user qwerty identified by 수정할 비밀번호

-- 접속권한, 객체 생성 권한 부여
-- create session, create table ...
-- 권한 직접 부여
grant create session, create table to qwerty;
-- 롤(권한 묶음) 부여
grant connect, resource to qwerty;
-- tablespace에 대한 용량부여
alter user qwerty quota unlimited on users;

-- @system qwerty에게 부여된 권한/롤 조회
-- privilege 권한 = privs
select * from dba_sys_privs where grantee = 'QWERTY';
select * from dba_role_privs where grantee = 'QWERTY';


-- @qwerty 계정으로 확인 - 사용자에게 부여된 권한/롤 확인
select * from role_sys_privs; -- 부여받은 롤에 포함된 권한 정보
select * from user_sys_privs;
select * from user_role_privs;

-- @sh 테이블 권한 @qwerty에게 부여
-- select 권한, insert 권한, update 권한, delete 권한을 각각 관리할 수 있다.
create table coffee(
    name varchar2(50),
    price number not null,
    company varchar2(50),
    constraints pk_coffee primary key(company, name)
);
insert into coffee values('맥심', 3000, '동서식품');
insert into coffee values('카누', 5000, '동서식품');
insert into coffee values('네스카페', 4000, '네슬레');

-- 조회권한 부여
-- 테이블 소유주 또는 관리자가 부여할 수 있다
grant select on coffee to qwerty;
grant insert on coffee to qwerty;
grant select, insert, update, delete on coffee to qwerty;
grant all on coffee to qwerty; -- 테이블 권한 모두 부여

-- 권한 회수
revoke insert, update, delete on coffee from qwerty;

-- employee 테이블에 대해 조회권한만 qwerty에게 부여
grant select on employee to qwerty;

------------------------------------
-- TCL
------------------------------------
-- Transaction Control Language 트랜잭션 제어어
-- commit, rollback, savepoint

-- 트랜잭션이란
-- 한 번에 수행되어야 할 최소한의 작업 단위
-- 여러 DML을 묶어서 한 번에 commit 혹은 rollback 처리해야 할 작업량
-- ALL or NONE 

-- 계좌이체
-- A가 1000000원을 B에게 송금하는 경우
-- update 계좌 set 잔액 = 잔액 - 100000 where id = 'A';
-- update 계좌 set 잔액 = 잔액 + 100000 where id = 'B';
-- 모든 DML이 성공했을 경우 commit, 하나라도 실패했을 경우 rollback

--===================================================--
-- DATABASE OBJECT 1
--===================================================--
-- 데이터를 효율적으로 관리하기 위해 준비된 오라클 내의 객체
-- table, user, sequence, procedure, function, view, index, package ...

-- procedure, trigger, function 등은 pl/sql 문법 사용하는 객체 --> DB OBJECT2

------------------------------------
-- DATA DICTIONARY
------------------------------------
-- 자원을 효율적으로 관리하기 위한 객체별 메타 정보를 관리하는 관리자의 뷰(가상테이블)
-- 일반 사용자는 권한을 부여받아 이를 사용하는 것
-- 사용자는 DD의 내용을 열람만 할 뿐, 수정할 필요는 없다
-- 사용자 DDL을 통해 객체의 정보를 변경할 때에도, DD는 자동으로 반영된다.

-- 종류
-- 1. user_xxx 사용자가 소유한 객체에 대한 정보
-- 2. all_xxx  사용자가 소유한 객체와 권한을 부여받은 객체의 정보
-- 3. dba_xxx  관리자의 DD. 모든 객체에 대한 정보를 확인 가능

-- 모든 dd 조회
select * from dict;

-- user_tables
-- 객체는 반드시 복수형의 이름을 가진다
-- 사용자가 소유한 테이블 정보
select * from user_tables;

-- 제약 조건 user_constraints
select * from user_constraints where table_name = 'EMEPLOYEE';

-- 권한 user_sys_privs
-- 롤 user_role_privs
select * from user_sys_privs;
select * from user_role_privs;
select * from role_sys_privs; -- 부여받은 롤이 가지고 있는 권한
-- 시퀀스 user_sequences;
select * from user_sequences;
-- 인덱스 user_idnexes
select * from user_indexes;
-- 뷰 user_views
select * from user_views;

-- all_tables
-- 사용자 소유테이블 포함, 권한을 부여받은 테이블 조회
select * from all_tables;

select * from all_views;

-- dba_xxx
-- @system 또는 @sys로 접속해서 사용이 가능
select * from dba_users;
select * from dba_tables;

select * from dba_tables where owner = 'SH'; -- 테이블에 대한 메타정보
select * from sh.employee; -- 테이블에 대한 정보

select * from dba_constraints where owner = 'SH';

-- 사용자 권한 관리
select * from dba_sys_privs where grantee = 'SH';
select * from dba_role_privs where grantee = 'SH';
select * from dba_tab_privs where owner = 'SH';

grant select on sh.coffee to qwerty;


------------------------------------
-- STORED VIEW
------------------------------------
-- 하나 이상의 실제 테이블에서 원하는 정보만 추려낸 가상테이블
-- inline-view와는 달리 객체로 저장해 테이블처럼 사용할 수 있다.
-- 실제 데이터를 가지고 있지 않고, 실제 테이블에 대한 링크개념
-- create view 권한 필요(resource롤에 포함되지 않은 권한이므로 별도 부여 필요함)

-- @system에서 권한 부여
grant create view to sh;

-- or replace : 없으면 새로 만들고, 존재하면 대체해라
create or replace view view_emp
as
select 
    e.*,
    decode(substr(emp_no, 8, 1), '1', '남', '3', '남', '여') gender
from
    employee e;

select * from view_emp where gender = '남';    

-- dd에서 조회
select * from user_views;

-- 1. 복잡한 데이터를 쉽게 열람 가능
-- 사번, 사원명, 부서명, 직급명, 성별, 나이를 view_emp_detail 뷰로 생성해서 조회
create or replace view view_emp_detail
as
select
    e.emp_id,
    e.emp_name,
    d.dept_title,
    j.job_name,
    decode(substr(emp_no, 8, 1), '1', '남', '3', '남', '여') gender,
    extract(year from sysdate)-(decode(substr(emp_no,8,1),'1',1900,'2',1900,2000))-substr(emp_no,1,2)+1 age
/* 나이를 구하는 두번째 방법
   trunc(months_between(
                sysdate, 
                to_date(decode(substr(emp_no, 8, 1), '1', 1900, '2', 1900, 2000) + substr(emp_no, 1, 2) || substr(emp_no, 3, 4))
        ) / 12
    ) age
*/
from
    employee e
    join department d on e.dept_code = d.dept_id
    join job j on e.job_code = j.job_code;

select * from view_emp_detail;

-- 2. 제한적인 데이터를 다른 사용자에게 제공
grant select on sh.view_emp_detail to qwerty;
revoke select on sh.employee from qwerty;

------------------------------------
-- SEQUENCE
------------------------------------
-- 정수 값을 순차적으로 제공하는 채번 객체
-- pk 컬럼의 **중복없는** 고유값을 사용

-- create sequnece 시퀀스명
-- [start with 시작값] 기본값1
-- [increment by 증감값] 기본값1
-- [maxvalue 최소값 | nomaxvalue] 기본값 nomaxvalue
-- [minvalue 최소값 | nominvalue] 기본값 nominvalue
-- [cycle | nocycle] 기본값 nocycle 최대/최소값 도달 시 순환여부
-- [cache 캐싱개수 | nocache] 기본값 20 - 시퀀스 객체 접근을 최소화하는 옵션 (메모리에서 관리)

create table tb_items (
    no number,
    name varchar2(100) not null,
    constraints pk_tb_items_no primary key(no)
);

create sequence seq_tb_items_no
start with 1 -- 기본값들은 생략이 가능함
increment by 1
nomaxvalue
nominvalue
nocycle
cache 20;

insert into tb_items values (seq_tb_items_no.nextval, '축구화');
insert into tb_items values (seq_tb_items_no.nextval, '농구화');
insert into tb_items values (seq_tb_items_no.nextval, '배구화');
insert into tb_items values (seq_tb_items_no.nextval, '족구화');

select * from tb_items;

-- 최근 발급된 번호
select seq_tb_items_no.currval from dual;

-- DD에서 조회
select * from user_sequences;

-- cache 사용 시 유의사항
-- 번호를 메모리에서 관리하기 때문에 휘발될 수 있다. 채번된 번호가 건너뛸 수 있다.
-- 1 2 3 4 .. 21 22 23 ...
-- 채번의 가장 중요한 점은 중복없는 수를 발급하는 것 이므로, 건너뛰는게 큰 문제가 되지는 않음
-- 번호 공백 없는 데이터를 관리하는 경우  nocache 옵션을 사용해야 한다
-- (nocache도 롤백하면 번호를 건너뛸 수 있기 때문에, 마지막 번호를 조회해서 +1 처리한 번호를 사용하는 것이 제일 좋다)
insert into tb_items values ((select max(no)+1 from tb_items), '반팔티');


-- 주문
create table tbl_order (
    no varchar2(50),
    product varchar2(100),
    reg_date date default sysdate,
    constraints pk_tbl_order_no primary key(no)
    
);
create sequence seq_tbl_order_no;

-- kh-20230503-01234 주문번호
insert into
    tbl_order
values (
    'kh-' || to_char(sysdate, 'yyyymmdd') || '-' || to_char(seq_tbl_order_no.nextval, 'fm00000'), 
    '농구공',
    default
);
select * from tbl_order;

------------------------------------
-- INDEX
------------------------------------
-- 색인, sql문 처리속도 향상을 위해 컬럼에 대해 생성하는 객체
-- key-value 형식을 작성, key는 컬럼값, value는 레코드에 대한 주소값(rowid)
-- 별도의 저장공간이 필요한 객체. 테이블 DML 작업시 인덱스에 대한 작업 역시 발생한다.

-- 장점
-- 해당 테이블에 대한 검색 속도, 조인 처리 등이 몹시 빨라진다.
-- 단점
-- DML 작업에 따른 후처리때문에 오히려 성능저하가 일어날 수도 있다.

-- 어떤 컬럼에 인덱스를 적용해야 하는가?
-- 1. 선택도(유일한 값 빈도)가 좋은 컬럼 -- 회원아이디(선택도가 좋음), 이름(선택도 좋은 편) | 성별(선택도 나쁨)
-- 2. where절에 주요 사용되는 컬럼
-- 3. 조인 시 기준컬럼

-- 인덱스 적용을 피해야 하는 컬럼
-- 1. DML 작업이 많은 컬럼
-- 2. null데이터가 많은 컬럼

-- 인덱스 조회
-- pk, uq 제약조건이 걸린 컬럼은 자동으로 인덱스 생성 (직접 관리 할 필요 없음)
select * from user_indexes where table_name = 'EMPLOYEE';

-- 실행 계획을 통한 비용 확인 (F10)
select * from employee where emp_name = '송종기'; -- emp_name 인덱스 없음
select * from employee where emp_id = '201'; -- index 존재함

-- emp_name 컬럼에 대한 인덱스 생성
create index idx_employee_emp_name on employee(emp_name);

select * from employee e left join department d on e.dept_code = d.dept_id;

create index idx_employee_dept_code on employee(dept_code);

-- 인덱스 사용시 주의할 점
-- 인덱스 활용 여부 optimizer에 의해서 결정이 되지만, 다음 경우 인덱스를 사용하지 않는다
-- 1. 인덱스컬럼에 변행이 가해지는 경우
select * from employee where emp_no = '861015-1356452'; -- 인덱스 사용해서 조회
select * from employee where substr(emp_no, 8, 1) in ('1','3'); -- 인덱스 사용하지 않음
-- 2. null 비교하는 경우
select * from employee where emp_name is not null;
-- 3. not 비교하는 경우
select * from employee where emp_name != '송종기';
-- 4. 컬럼타입과 비교하는 값의 타입이 다른 경우
select * from employee where emp_id = '201';

--===================================================--
-- PL/SQL
--===================================================--
-- Procedural Language Extension to SQL
-- SQL 절차적 언어 확장, 프로그래밍적 요소가 추가된 SQL문

-- 유형
-- 1. 익명블럭
-- 2. db객체 - 프로시져, 함수, 트리거, 스케쥴러 등

-- 익명블럭 구조
/*
declare
    변수 선언 구문
begin (필수)
    실행구문 
exeception
    예외처리구문
end; (필수)
/ 익명블럭의 종료표시 << 꼭 넣어주기
*/

-- 출력 설정 (세션마다)
set serveroutput on;
declare
    name varchar2(100) := '홍길동';
begin
    dbms_output.put_line(name);
end;
/
declare
    v_emp_id char(3) := '&사번';
    v_emp_name varchar2(50);
begin
    select
        emp_name
    into
        v_emp_name -- 조회된 결과를 담을 변수
    from
        employee
    where
        emp_id = v_emp_id;
    dbms_output.put_line('결과:' || v_emp_name);
exception
    when no_data_found then dbms_output.put_line('조회된 사원이 없습니다.');
    when others then dbms_output.put_line('알 수 없는 오류가 발생했습니다.');
end;
/

------------------------------------
-- 변수
------------------------------------
-- 자료형 - 기본자료형 | 복합자료형
-- 변수종류 - 스칼라변수 | 참조변수

-- 기본 자료형-- 문자형 char varchar2 clob
-- 숫자형 number, binary_integer, pls_integer
-- 날짜형 date, timestamp
-- 논리형 boolean (true, false, null)

-- 복합자료형
-- 레코드
-- 커서
-- 컬렉션

-- 스칼라 변수
-- 값 하나를 담는 변수, SQL 자료형 그대로 사용

-- 참조변수
-- 기존 선언된 자료형을 참조
-- %type, %rowtype, record, collection

-- %type
-- 기존 테이블의 컬럼의 자료형을 그대로 사용

declare
    v_emp_id employee.emp_id%type;
    v_emp_name employee.emp_name%type;
    v_phone employee.phone%type;
begin
    v_emp_id := '&사번';
    select
        emp_name, phone
    into
        v_emp_name, v_phone
    from
        employee
    where
        emp_id = v_emp_id;
    dbms_output.put_line('이름 : ' || v_emp_name);
    dbms_output.put_line('전화번호 : ' || v_phone);
end;
/

-- %rowtype
-- 테이블의 구조. 전체 컬럼을 가져와 참조.

declare
    v_emp_id employee.emp_id%type;
    emp_row employee%rowtype;
begin
    v_emp_id := '&사번';
    
    select
        *
    into
        emp_row
    from
        employee
    where
        emp_id = v_emp_id;
    
    dbms_output.put_line('이름 : ' || emp_row.emp_name);
    dbms_output.put_line('전화번호 : ' || emp_row.phone);
    
end;
/

-- @실습문제 : 사번을 입력받고, 사원명, 주민번호, 부서명을 출력하는 익명블럭 작성

declare
    v_emp_id employee.emp_id%type := '&사번';
    v_emp_name employee.emp_name%type;
    v_emp_no employee.emp_no%type;
    v_dept_title department.dept_title%type;
begin
    select
        e.emp_name, e.emp_no, d.dept_title
    into
        v_emp_name, v_emp_no, v_dept_title
    from
        employee e left join department d
            on e.dept_code = d.dept_id
    where
        e.emp_id = v_emp_id;
    
    dbms_output.put_line('이름 : ' || v_emp_name);
    dbms_output.put_line('주민번호 : ' || v_emp_no);
    dbms_output.put_line('부서명 : ' || v_dept_title);
end;
/

-- recode 사용하기
-- 복합컬럼
declare
    v_emp_id employee.emp_id%type := '&사번';
    
    type my_rec is record(
        v_emp_name employee.emp_name%type,
        v_emp_no employee.emp_no%type,
        v_dept_title department.dept_title%type
    );
    
    my_row my_rec; -- 변수명 자료형
begin
    select
        e.emp_name, e.emp_no, d.dept_title
    into
        my_row
    from
        employee e left join department d
            on e.dept_code = d.dept_id
    where
        e.emp_id = v_emp_id;
    
    dbms_output.put_line('이름 : ' || my_row.v_emp_name);
    dbms_output.put_line('주민번호 : ' || my_row.v_emp_no);
    dbms_output.put_line('부서명 : ' || my_row.v_dept_title);
end;
/

-- pl/sql문에서 DML
-- commit도 함께 작성할 것

select * from member;
desc member;

begin
    insert into
        member
    values(
        'abcde', '1234', '장발장', 'abcde@naver.com','M', default
    );
    commit;
end;
/

-- pl/sql 조건문
-- 1. if
-- 2. case문

-- if 조건식 then 실행문 end if;
-- if 조건식 then 실행문 else 기본실행문 end if;
-- if 조건식 then 실행문1 elsif 조건식2 then 실행문2 ... else 기본실행문 end if;

begin
    if '&이름' = '홍길동' then
    dbms_output.put_line('홍길동님 반갑습니다.');
    else
        dbms_output.put_line('누구세여?');
    end if;
    
    dbms_output.put_line('~~끝~~');
end;
/
declare
    num number := &숫자;
begin
    if mod(num,3)=0 then
        dbms_output.put_line('3의 배수를 입력했습니다.');
    elsif mod(num,3)=1 then
        dbms_output.put_line('3으로 나눈 나머지가 1입니다.');
    else
        dbms_output.put_line('3으로 나눈 나머지가 2입니다.');
    end if;
end;
/

-- 사용자가 입력한 정수가 양수인지 음수인지 0인지 출력하는 익명블럭을 작성
declare
    num number := &숫자;
begin
    if num=0 then
        dbms_output.put_line('0을 입력하셨습니다.');
    elsif num>0 then
        dbms_output.put_line('양수를 입력하셨습니다.');
    else
        dbms_output.put_line('음수를 입력하셨습니다.');
    end if;
end;
/

-- @실습문제 : 사번을 입력받고, 해당사원의 급여가 평균 급여와 비교결과를 출력하세요
/*
declare
    v_emp_id employee.emp_id%type := '&사번';
    v_emp_name employee.emp_name%type;
    v_salary employee.salary%type;
    v_avg_sal employee.salary%type;
begin
    select
        e.emp_id,
        e.emp_name,
        e.salary,
        (select avg(salary) from employee) avg_sal
    into
        v_emp_id, v_emp_name, v_salary, v_avg_sal
    from
        employee e
    where
        emp_id = v_emp_id;
    
    if v_salary > v_avg_sal then
        dbms_output.put_line('평균 급여보다 많은 급여를 받고 있습니다.');
    elsif v_salary < v_avg_sal then
        dbms_output.put_line('평균 급여보다 적은 급여를 받고 있습니다.');
    else
        dbms_output.put_line('평균 급여를 받고 있습니다.');
    end if;
end;
/
*/
declare
    v_emp_id employee.emp_id%type := '&사번';
    v_emp_name employee.emp_name%type;
    v_salary employee.salary%type;
    v_avg_sal employee.salary%type;
begin
    -- 평균급여
    select
        avg(salary)
    into
        v_avg_sal
    from
        employee;
    dbms_output.put_line(v_avg_sal);
    
    -- 사원별 이름/급여 조회
    select
        emp_name, salary
    into
        v_emp_name, v_salary
    from
        employee
    where
        emp_id = v_emp_id;
        
    dbms_output.put_line(v_emp_name || ' ' || v_salary);
    
    -- 분기처리
    if v_salary > v_avg_sal then
        dbms_output.put_line(v_emp_id || '번 ' || v_emp_name || ' 사원은 평균보다 많은 급여를 받고 있습니다.');
    elsif v_salary < v_avg_sal then
        dbms_output.put_line(v_emp_id || '번 ' || v_emp_name || ' 사원은 평균보다 적은 급여를 받고 있습니다.');
    else
        dbms_output.put_line(v_emp_id || '번 ' || v_emp_name || ' 사원은 평균 급여를 받고 있습니다.');
    end if;
    

end;
/

-- case 문
/*
문법1
case 표현식
    when 값1 then 실행문1;
    when 값2 then 실행문2;
    ...
    [else 기본 실행문]
end case;

문법2 조건
case
    when 조건식1 then 실행문1;
    when 조건식2 then 실행문2;
    ...
    [else 기본 실행문]
end case;
*/

-- 문법1
accept rps prompt '가위1 바위2 보3 중에 입력하세요.'
declare
    num number := &rps;
begin
    dbms_output.put_line(num);
    case num
        when 1 then dbms_output.put_line('가위를 내셨습니다.');
        when 2 then dbms_output.put_line('바위를 내셨습니다.');
        when 3 then dbms_output.put_line('보를 내셨습니다.');
        else dbms_output.put_line('잘못 입력하셨습니다.'); return; -- 조기리턴
    end case;
end;
/
-- 문법2
accept rps prompt '가위1 바위2 보3 중에 입력하세요.'
declare
    num number := &rps;
    com number := trunc(dbms_random.value(1, 4)); -- 1.0이상 4.0미만의 난수 실수 반환
begin
    dbms_output.put_line(num);
    dbms_output.put_line(com);
    case
        when num=1 then dbms_output.put_line('가위를 내셨습니다.');
        when num=2 then dbms_output.put_line('바위를 내셨습니다.');
        when num=3 then dbms_output.put_line('보를 내셨습니다.');
        else dbms_output.put_line('잘못 입력하셨습니다.'); return; -- 조기리턴
    end case;
    
    case com
        when 1 then dbms_output.put_line('컴퓨터는 가위를 냈습니다.');
        when 2 then dbms_output.put_line('컴퓨터는 바위를 냈습니다.');
        when 3 then dbms_output.put_line('컴퓨터는 보를 냈습니다.');
    end case;
    
    -- 가위바위보 결과 출력
    case
        when (num=1 and com=2) or (num=2 and com=3) or (num=3 and com=1) then dbms_output.put_line('<<졌습니다.>>');
        when (num=1 and com=3) or (num=2 and com=1) or (num=3 and com=2) then dbms_output.put_line('<<이겼습니다.>>');
        else dbms_output.put_line('<<비겼습니다.>>'); return;
     end case;
end;
/

-- 반복문
-- 1. 기본반복문 loop
-- 2. while loop
-- 3. for..in loop

-- 기본 반복문(무한반복)

declare
    n number := 1;
begin
    loop
        dbms_output.put_line(n);
        n := n+1; -- 증감처리
        -- exit 처리
        exit when n=6;
    end loop;
end;
/

-- 1~10 사이의 난수 10개 출력
declare
    n number := 1;
begin
    loop
        dbms_output.put_line(trunc(dbms_random.value(1, 11)));
        n := n+1;
        exit when n>10;
    end loop;
end;
/

-- while loop
-- 조건식일때 참일때 while 반복 실행

declare
    n number := 1;
begin
    while n <=5 loop
    dbms_output.put_line(n);
    n := n + 1;
    end loop;
end;
/

-- 구구단 7단을 출력 (while loop)

declare
    n number := 7;
    i number := 1;
begin
    while i <=9 loop
    dbms_output.put_line(n || 'X' || i || '=' || n*i);
    i := i + 1;
    end loop;
end;
/

declare
    n number := 2;
    i number := 1;
begin
    while n <= 9 loop 
        i := 1;
        while i <=9 loop
            dbms_output.put_line(n || 'X' || i || '=' || n*i);
            i := i + 1;
        end loop; 
        n := n + 1;
    end loop;
end;
/

-- for..in loop
-- 증감변수 선언할 필요 없다
-- 증감처리는 무조건 +1
-- reverse -1 처리 가능

begin
    for n in 1..10 loop
        dbms_output.put_line(n);
    end loop;
    
    dbms_output.new_line;
    
    for n in reverse 1..10 loop
        dbms_output.put_line(n);
    end loop;
end;
/

-- 구구단
begin
    for n in 2..9 loop
        for i in 1..9 loop
            dbms_output.put_line(n || 'X' || i || '=' || n*i);
        end loop;
        dbms_output.new_line;
    end loop;    
end;
/

-- 200~223번 사원 조회
declare
    emprow employee%rowtype;
begin
    for n in 200..223 loop
        select
            *
        into
            emprow
        from
            employee
        where
            emp_id = n;
            
        dbms_output.put_line(emprow.emp_id || ':' || emprow.emp_name);
    end loop;
end;
/

--===================================================--
-- DATABASE OBJECT 2
--===================================================--
-- pl/sql 문법을 사용하는 db객체

------------------------------------
-- STORED FUNCTION
------------------------------------
-- 저장 사용자함수
-- 미리 컴파일되어 즉시 실행가능한 상태로 보관중이므로, 직접 쿼리를 전송하는 것 대비 효율이 좋다.
-- 무조건 리턴값이 있어야 한다
-- 함수 선언부(변수의 자료형에 크기를 지정하지 않는다), 함수 실행부로 구분한다

create or replace function fn_headphone (p_str varchar2)
return varchar2
is
    -- 지역변수 선언부
    result varchar2(32767);
begin
    -- 실행부
    result := 'd' || p_str || 'b';
    return result;
end;
/

-- 일반 sql에서 함수를 사용할 수 있다
select
    fn_headphone(emp_name)
from
    employee;

-- 타 pl/sql문에서 호출
declare
    name varchar2(100) := '&이름';
begin
    dbms_output.put_line('헤드폰을 드려요~' || fn_headphone(name));
end;
/

-- 성별을 구하는 함수 fn_gender
create or replace function fn_gender(p_emp_no employee.emp_no%type)
return char
is
begin
    return case substr(p_emp_no, 8, 1)
                    when '1' then '남'
                    when '3' then '남'
                    else '여'
        end;
end;
/

select
    emp_name, fn_gender(emp_no)
from
    employee;

-- 나이를 반환하는 fn_age 함수를 작성하고 사원명, 나이를 조회(일반 sql문)
create or replace function fn_age(p_emp_no employee.emp_no%type)
return number
is
    birthday date;
begin
    if substr(p_emp_no, 8, 1) in ('1', '2') then
        birthday := to_date(1900 + substr(p_emp_no,1,2) || substr(p_emp_no, 3, 4), 'yyyymmdd');
    else
        birthday := to_date(2000 + substr(p_emp_no,1,2) || substr(p_emp_no, 3, 4), 'yyyymmdd');
    end if;
    return trunc(months_between(sysdate, birthday)/12);
end;
/

select
    emp_name, fn_age(emp_no)
from   
    employee;
    
    
--===================================================--
-- STORED PROCEDURE
--===================================================--
-- 일련의 작업절차를 그룹핑한 객체로 호출해서 사용. (일반 sql문에서는 호출 불가)
-- 함수와 달리 리턴값이 없다. 대신 out모드 매개변수를 이용해 client에 값 전달 가능
-- 미리 컴파일 된 형태로 저장되므로, 일반 sql대비 처리효율이 좋다.
-- pl/sql문 또는 exec 명령으로 호출/실행할 수 있다.

-- 매개변수에는 in/out모드가 있다.
-- in 프로시져에 값 전달용
-- out 클라이언트에 값 전달용

select * from employee_ex;

-- 부서원 모두 삭제 프로시져
create or replace procedure proc_del_emp_by_dept(
    p_dept_code in employee.dept_code%type -- 자료형 크기는 작성하지 않는다
)
is
begin
    dbms_output.put_line(p_dept_code || '부서원을 삭제합니다.');
    delete from
        employee_ex
    where
        dept_code = p_dept_code;
    commit;
end;
/

begin
    proc_del_emp_by_dept('D5');
end;
/

-- 부서원 조회 프로시져
-- 사번을 받아서 사원명, 부서명, 직급명 조회
-- 조회한 값을 out모드 매개변수에 대입하면 클라이언트에서 확인 가능하다
create or replace procedure proc_emp(
    p_emp_id in employee.emp_id%type,
    p_emp_name out employee.emp_name%type,
    p_dept_title out department.dept_title%type,
    p_job_name out job.job_name%type
)
is
begin
    select
        emp_name,
        (select dept_title from department where dept_id = e.dept_code) dept_title,
        (select job_name from job where job_code = e.job_code) job_name
    into
        p_emp_name, p_dept_title, p_job_name
    from
        employee e
    where
        emp_id = p_emp_id;
end;
/
-- 익명블럭 안에서 proc 호출
declare
    v_emp_id employee.emp_id%type := '&사번';
    v_emp_name employee.emp_name%type;
    v_dept_title department.dept_title%type;
    v_job_name job.job_name%type;
begin
    -- 사번(in), 사원명(out), 부서명(out), 직급명(out)
    proc_emp(v_emp_id, v_emp_name, v_dept_title, v_job_name);
    
    dbms_output.put_line('사원명 : ' || v_emp_name);
    dbms_output.put_line('부서명 : ' || v_dept_title);
    dbms_output.put_line('직급명 : ' || v_job_name);
end;
/

-- 직급명 upsert예제
-- insert 해당 데이터가 없으면 삽입
-- update 해당 데이터가 있으면 수정
create table job_ex
as
select * from job;

select * from job_ex;

alter table job_ex
add constraints pk_job_ex_job_code primary key(job_code)
modify job_code varchar2(10)
modify job_name not null;

-- J8 알바 -> insert
-- J7 주임 -> update

create or replace procedure proc_job_ex (
    p_job_code job_ex.job_code%type,
    p_job_name job_ex.job_name%type
)
is
    cnt number;
begin
    -- 행 존재 여부
    select
        count(*)
    into
        cnt
    from
        job_ex
    where
        job_code = p_job_code;

     dbms_output.put_line(cnt);
     
    if cnt = 0 then
        -- insert
        insert into job_ex values (p_job_code, p_job_name);
    else
        -- update
        update job_ex set job_name = p_job_name where job_code = p_job_code;
    end if;
    commit;
end;
/

begin
--    proc_job_ex('J8','알바');
    proc_job_ex('J1','보스');
end;
/

select * from job_ex;

------------------------------------
-- CURSOR
------------------------------------
-- 쿼리를 실행 결과에 접근할 수 있는 포인터
-- 커서란 특정 SQL 문장을 처리한 결과를 담고 있는 영역을 가리키는 일종의 포인터
-- 커서를 사용하면 처리된 SQL 문장의 결과 집합에 접근할 수 있음
-- DQL, DML 모두 cursor를 통해 실행결과 확인 가능

-- 종류
-- 1. 암시적 커서(자동)
-- 2. 명시적 커서(직접 선언)

-- 속성
-- %rowcount 처리된 행 수(DML), fetch된 행 수(DQL)
-- %found opne이후 fetch된 행이 존재하면 true, 존재하지 않으면 false
-- %notfound open이후 fetch된 행이 존재하면 false, 존재하지 않으면 true
-- %isopen

-- DQL
declare
    emprow employee%rowtype;
begin
    select
        *
    into 
        emprow
    from
        employee
    where
        emp_id = '&사번';  
    -- 암시적커서 sql
    if sql%found then
        dbms_output.put_line(sql%rowcount || '행이 조회됨');
        dbms_output.put_line(emprow.emp_name);
    end if;   
end;
/

-- DML

begin
    update
        employee_ex
    set
        salary = salary + 500000   
    where
        dept_code = 'D1';
    dbms_output.put_line(sql%rowcount || '행이 수정되었음');
    commit;
end;
/

select * from employee_ex;

insert into job_ex values('J9','견습생');

-- 명시적 커서
-- 사용자가 쿼리 결과에 직접 접근해 처리할 수 있게 선언한 객체
-- cursor 선언 - open - fetch(한 행씩) - close
-- 여러 행을 처리하려면 커서를 반드시 써야함
declare
    -- 선언
    cursor cs_emp
    is
    select * from employee;
    
    emprow employee%rowtype;
begin
    -- open
    open cs_emp;
    loop
    -- fetch (한 행씩 읽어들이는 것)
        fetch cs_emp into emprow;
        exit when cs_emp%notfound; -- 모든 행을 가져온 경우
        dbms_output.put_line(emprow.emp_id || ' : ' || emprow.emp_name);
    end loop;
    -- close
    close cs_emp;
end;
/

-- for..in문을 통한 커서제어
-- 선언 - open - fetch - close 중에 open - fetch - close 과정을 for..in문이 자동으로 처리
-- fetch 결과를 담을 변수도 별도의 선언 없이 for..in 문 안에서 처리
declare
    cursor cs_emp
    is
    select * from employee e left join department d on e.dept_code = d.dept_id;
begin
    -- row 타입 변수는 테이블 컬럼을 상관치 않는다. (여러테이블 조인해도 ok)
    for erow in cs_emp loop
    dbms_output.put_line(erow.emp_id || ':' || erow.emp_name);
    end loop;
end;
/


-- 파라미터를 받는 커서
declare
    cursor cs_emp (p_dept_title department.dept_title%type)
    is
    select * from employee e left join department d on e.dept_code = d.dept_id where d.dept_title = p_dept_title;
begin
    -- row 타입 변수는 테이블 컬럼을 상관치 않는다. (여러테이블 조인해도 ok)
    for erow in cs_emp('&부서명') loop
    dbms_output.put_line(erow.emp_id || ':' || erow.emp_name || ' ' || erow.dept_title);
    end loop;
end;
/

------------------------------------
--  TRIGGER
------------------------------------
-- 방아쇠. 연쇄반응의 시작점
-- 특정이벤트(DML)가 실행되었을 때, 연쇄적으로 실행할 코드를 가진 객체

-- 회원 탈퇴(DML)시 탈퇴한 회원정보를 탈퇴 회원 테이블에 insert 처리
-- 회원 정보 수정 시, 수정 로그를 로그 테이블에 insert 처리
-- 트리거 안에서는 TCL 처리를 하지 않는다. 선 DML의 트랜잭션에 포함되어 처리됨.

/*
create [or replace] trigger 트리거명
    before | after -- 선 DML 이전 혹은 이후 실행 여부
    insert | update | delete on 테이블명 -- 선 DML 대상테이블
    [for each row] -- 행 단위 트리거 설정 (선 DML이 처리행 별로 트리거 실행), 생략하면 문장레벨 트리거 (선 DML당 1번 실행)
[declare]
    -- 변수 선언
begin
    -- 실행
end;
/
*/

create table tbl_user (
    id varchar2(20),
    name varchar2(50),
    constraints pk_tbl_user_id primary key(id)
);

create table tbl_user_log(
    no number,
    user_id varchar2(20),
    log varchar2(4000),
    log_date date default sysdate,
    constraints pk_tbl_user_log_no primary key(no)
);

create sequence seq_tbl_user_log_no;

create or replace trigger trig_user_log 
    before
    insert or update or delete on tbl_user
    for each row
begin
    if inserting then 
        -- 사용자 등록시
        insert into
            tbl_user_log(no, user_id, log)
        values(
            seq_tbl_user_log_no.nextval,
            :new.id,
            :new.name || '(' || :new.id || ') 사용자 등록'
        );
    elsif updating then
        -- 사용자 정보 수정시
        insert into
            tbl_user_log(no, user_id, log)
        values(
            seq_tbl_user_log_no.nextval,
            :new.id,
            :old.name || '---->' || :new.name || ' 이름변경'
        );
    else
        -- 사용자 탈퇴시
        insert into
            tbl_user_log(no, user_id, log)
        values(
            seq_tbl_user_log_no.nextval,
            :old.id,
            :old.name || '(' || :old.id || ') 사용자 등록'
        );
    end if;
end;
/

-- 의사 레코드 :old :new
-- Pseudo Record (가상 레코드) : 트리거에서 사용하는 가상 행. 선 DML의 값에 접근 가능
-- 행 레벨 트리거(for each row)일때 사용 가능
/*
            :old          :new
insert      null        추가된 행
update    변경 전 행    변경 후 행
delete    삭제 전 행       null
*/


insert into tbl_user values('hon3221ggd', '홍길동');
update tbl_user set name = '홍길동길동' where id = 'honggd';
delete from tbl_user where id = 'honggd';
select * from tbl_user;
select * from tbl_user_log;

-- 상품 재고 관리
-- 상품 테이블(재고수량)과 입출고테이블(입고수량 | 출고수량)

create table tb_product(
    pcode varchar2(20),
    pname varchar2(50),
    stock number default 0, -- 재고
    constraints pk_tb_product_pcode primary key(pcode),
    constraints ck_tb_product_stock check (stock >= 0)
);

create table tb_product_io (
    no number,
    pcode varchar2(20),
    status char(1), -- I O
    amount number,
    io_date date default sysdate,
    constraints pk_tb_product_io_no primary key(no),
    constraints fk_tb_prodcut_io_pcode foreign key(pcode) references tb_product(pcode)
);

create sequence seq_tb_product_io_no;

insert into
    tb_product
values (
    'apple_iphone_15', '아이폰15', default
);

insert into
    tb_product
values (
    'samsung_galaxy_23', '갤럭시23', default
);

select * from tb_product;

insert into
    tb_product_io
values(
    seq_tb_product_io_no.nextval,
    'samsung_galaxy_23',
    'I',
    30,
    default
);
insert into
    tb_product_io
values(
    seq_tb_product_io_no.nextval,
    'samsung_galaxy_23',
    'O',
    15,
    default
);

select * from tb_product_io;
select * from tb_product;

-- 입출고 내역에 따라 자동으로 재고를 관리하는 트리거
create or replace trigger trig_product_stock
    before
    insert on tb_product_io
    for each row -- 의사레코드 사용 가능
begin
    if :new.status = 'I' then
    -- 입고 (수량만큼 재고에 더하기)
        update
            tb_product
        set
            stock = stock + :new.amount
        where
            pcode = :new.pcode;
    else
    -- 출고 (수량만큼 재고에 빼기)
        update
            tb_product
        set
            stock = stock - :new.amount
        where
            pcode = :new.pcode;
    end if;
end;
/

-- before / after 옵션
-- 선DML과 후DML(트리거 작업) 사이에 fk 제약조건이 걸린 경우 이 옵션을 적절히 활용해야 한다.
-- 후DML의 테이블이 fk 제약조건이 걸린 자식 테이블이라면 insert(후DML)는 after로 작동해야 한다.
-- 후DML의 테이블이 fk 제약조건이 걸린 자식 테이블이라면 delete(후DML)는 before로 작동해야 한다.
create table tb_parent(id varchar2(20) primary key);
create table tb_child(parent_id  varchar2(20) references tb_parent(id));

insert into tb_parent values('abcde'); -- 선DML (먼저 처리해야되는 DML)
insert into tb_child values('abcde'); -- 부모 테이블 id를 참조하기 때문에 부모 키에 있는 값만 insert할 수 있음
                                      -- 후DML(트리거) --> after 옵션으로 작성
create or replace trigger trig_parent_child
    after
    insert on tb_parent
    for each row
begin
    insert into tb_child values(:new.id);
end;
/

insert into tb_parent values('honggd2');

select * from tb_parent;
select * from tb_child;


create or replace trigger trig_parent_child_del
   before
   delete on tb_parent
   for each row
begin
    delete from tb_child where parent_id = :old.id; -- 후DML
end;
/

delete from tb_parent where id = 'abcde'; -- 선DML


--@ 실습문제
-- EMP 테이블에서 사원 이름, 입사일, 근무개월수 조회하는 SELECT 작성
SELECT
    emp_name as 이름,
    floor(months_between(sysdate,hire_date)) as 근무개월수
from
    EMP;

select
    emp_name as 이름,
    salary 월급,
from
    emp
where
    (sysdate-hire_date)/365 >= 20; --근속 20년 이상 조건
    
-- _앞이 3글자인 사원
select
    emp_name
from
    employee
where
    email like '___#_%' escape'#';

