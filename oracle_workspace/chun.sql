--=====================================
-- 춘대학 학사시스템 @chun
--=====================================
SELECT * FROM tb_department; -- 학과
SELECT * FROM tb_student;
SELECT * FROM tb_class;
SELECT * FROM tb_professor;
SELECT * FROM tb_class_professor;
SELECT * FROM tb_grade;

-- 1. 춘 기술대학교의 학과 이름과 계열을 표시하시오. 단, 출력 헤더는 "학과 명", "계열"으로 표시되도록 한다.
SELECT
    department_name "학과 이름",
    category        "계열"
FROM
    tb_department;
    
-- 2. 학과의 학과 정원을 다음과 같은 형태로 화면에 출력한다.
SELECT
    department_name "학과 이름",
    capacity        "정원",
    department_name
    || '의 정원은'
    || capacity
    || '명 입니다.'
FROM
    tb_department;

-- 3. "국어국문학과" 에 다니는 여학생 중 현재 휴학중인 여학생을 찾아달라는 요청이
-- 들어왔다. 누구인가? (국문학과의 '학과코드'는 학과 테이블(TB_DEPARTMENT)을 조회해서
-- 찾아 내도록 하자)
SELECT
    student_name
FROM
    tb_student
WHERE
    ( department_no = '001' )
    AND ( absence_yn = 'Y' )
    AND ( substr(
        student_ssn, 8, 1
    ) IN ( '2', '4' ) );
    
-- 4. 도서관에서 대출 도서 장기 연체자 들을 찾아 이름을 게시하고자 한다. 그 대상자들의
-- 학번이 다음과 같을 때 대상자들을 찾는 적젃핚 SQL 구문을 작성하시오    
SELECT
    student_name
FROM
    tb_student
WHERE
    student_no IN ( 'A513079', 'A513090', 'A513091', 'A513110', 'A513119' );


-- 5. 입학정원이 20 명 이상 30 명 이하인 학과들의 학과 이름과 계열을 출력하시오.
SELECT
    department_name "학과 명",
    category        "계열"
FROM
    tb_department
WHERE
    capacity >= 20
    AND capacity <= 30;


-- 6. 6. 춘 기술대학교는 총장을 제외하고 모든 교수들이 소속 학과를 가지고 있다. 그럼 춘
-- 기술대학교 총장의 이름을 알아낼 수 있는 SQL 문장을 작성하시오.
SELECT
    professor_name
FROM
    tb_professor
WHERE
    department_no IS NULL;
    

-- 7. 혹시 전산상의 착오로 학과가 지정되어 있지 않은 학생이 있는지 확인하고자 한다. 
-- 어떠한 SQL 문장을 사용하면 될 것인지 작성하시오
SELECT
    department_name
FROM
    tb_department
WHERE
    department_name IS NULL;


-- 8. 수강신청을 하려고 한다. 선수과목 여부를 확인해야 하는데, 선수과목이 존재하는
-- 과목들은 어떤 과목인지 과목번호를 조회해보시오.
SELECT
    class_no
FROM
    tb_class
WHERE
    preattending_class_no IS NOT NULL;

-- 9. 춘 대학에는 어떤 계열(CATEGORY)들이 있는지 조회해보시오.
SELECT DISTINCT
    category
FROM
    tb_department;
    
-- 10. 02 학번 전주 거주자들의 모임을 만들려고 한다. 휴학한 사람들은 제외한 재학중인
-- 학생들의 학번, 이름, 주민번호를 출력하는 구문을 작성하시오.
SELECT
    student_no   학번,
    student_name 이름,
    student_ssn  주민번호
FROM
    tb_student
WHERE
    absence_yn = 'N'
    AND student_address LIKE '%전주%'
    AND student_no LIKE 'A2%';
    
-- @ 실습문제 : group by | having
-- 1. 학과 테이블에서 계열별 정원 평균 조회 (내림차순 정렬)
select
    category,
    trunc(avg(capacity)) "평균"
from
    tb_department
group by
    category
order by
    2 desc;
    
    
-- 2. 학생 테이블에서 휴학생을 제외하고 학과별 학생 수 조회(인원수 내림차순)
select
    department_no "학과 번호",
    count(*)
from
    tb_student
where
    absence_yn = 'N' -- 전체조건
group by
    DEPARTMENT_NO, absence_yn
order by
    2 desc;
-- where : 전체 조건 
-- having : 그룹에 대한 조건

-- 3. 교수 과목 테이블에서 과목 별 지정교수가 2명 이상인 과목 번호/ 교수 인원수 조회
select
    class_no,
    count(*)
from
    tb_class_professor
group by
    class_no
having
    count(class_no) >= 2;

-- 4.학과별로 과목을 구분했을 때, 과목 구분이 '전공선택'에 한하여 
-- 과목 수가 10개 이상인 데이터의 학과 번호/과목구분/과목수 조회
select
    department_no "학과번호",
    class_type "과목구분",
    count(*)
from
    tb_class
group by
    department_no, class_type
having
    class_type = '전공선택'
    and
    count(*) >= 10;
    
    
SELECT * FROM tb_department; -- 학과
SELECT * FROM tb_student;
SELECT * FROM tb_class;
SELECT * FROM tb_professor;
SELECT * FROM tb_class_professor;
SELECT * FROM tb_grade;


--===============================================
-- @조인실습문제
-- 1. 필요한 테이블 조회
-- 2. 연결 컬럼 찾기
-- 3. 내부/외부 조인 파악 - 누락된 행 여부 조사
--===============================================

--1. 의학계열 학과학생들의 학번, 학생명, 학과명 조회
select
    s.student_no 학번,
    s.student_name 학생명,
    d.department_name 학과명
from
    tb_student s join tb_department d on s.department_no = d.department_no
where
    category = '의학';


-- 2.  2005학번 학생명, 담당교수명 조회(담당교수 없는 학생도 조회)
select
    s.student_no 학번,
    s.student_name 학생명,
    p.professor_name 담당교수명
from
    tb_student s left join tb_professor p on s.department_no = p.department_no
where
    extract(year from ENTRANCE_DATe) = 2005; -- 날짜는 문자열 함수보다 extract 하는게 더 정확


-- 3. 자연과학 계열의 수업명, 학과명 조회
select
    c.class_name 수업명,
    d.department_name 학과명
from
    tb_class c join tb_department d on c.department_no = d.department_no
where
    d.category = '자연과학';


-- 4. 담당학생이 한명도 없는 교수정보 조회
-- 내부조인 579 (담당교수가 지정된 학생)
-- 좌측외부조인 588 : 내부조인 + 9 (담당교수 없는 학생)
-- 우측외부조인 580 : 내부조인 + 1 (담당학생이 없는 교수)
SELECT
    p.professor_no,
    p.professor_name
FROM
    tb_student   s
    RIGHT JOIN tb_professor p ON s.coach_professor_no = p.professor_no
WHERE
    s.coach_professor_no IS NULL;
    
-- 수업번호 | 수업명 | 선수수업번호 | 선수수업명
-- 선수수업이 있는 경우만 조회
SELECT
    c.class_no              수업번호,
    c.class_name            수업명,
    c.preattending_class_no 선수수업번호, -- p.class_no
    p.class_name            선수수업명
FROM
    tb_class c
    JOIN tb_class p ON c.preattending_class_no = p.class_no;
    -- 그냥 inner join하니까 null인 경우는 자동 제외
    
    
    
    
--@실습문제 조인1 : INNER JOIN & OUTER JOIN
--1. 학번, 학생명, 담당교수명을 출력하세요.
--담당교수가 없는 학생은 '없음'으로 표시
select
    student_no 학번,
    student_name 학생명,
    nvl(professor_name,'없음') 담당교수
    
from
    tb_student s left join tb_professor p 
    on s.department_no = p.department_no;

--2. 학과별 교수명과 인원수를 모두 표시하세요.
-- 학과 지정을 받지 못한 교수 여부 조사 -> 1명 있음

select * from tb_department; -- 63개
select * from tb_professor;
select distinct(department_no) from tb_professor; -- 52 (51 + 1)
-- inner join :  113행
--   tb_professor 학과 지정 안된 교수 1명이 제외
--   tb_department 교수가 존재하지 않는 학과 12개 제외

-- outer join
--    tb_department 기준 외부조인 125개
--    tb_professor 기준으로 외부조인 114개

--    professor_name 교수명 -- (rollup으로 인하여, 소계 부분에 null이 출력됨)
--    grouping(col) : 실제 데이터 컬럼은 0을 반환, rollup등에 의해 만들어진 컬럼인 경우 1 반환

select
    d.department_name,
    decode(grouping(p.professor_name), 0, p.professor_name, 1, count(*)) "professor_name"
from
    tb_department d right join tb_professor p 
-- inner join하면 학과지정 안된 교수 제외, right joint하면 학과지정 안된(학과가 null인) 교수도 소계함
        on d.department_no = p.department_no
group by
    rollup(d.department_name,p.professor_name)
order by
    1;

-- 3. 이름이 [~람]인 학생의 평균학점을 구해서 학생명과 평균학점(반올림해서 소수점둘째자리까지)과 같이 출력.
-- (동명이인일 경우에 대비해서 student_name만으로 group by 할 수 없다.)
select
    s.student_name 학생명,
    round(avg(point),2) 학점
from
    tb_student s join tb_grade g 
    on s.student_no = g.student_no
group by
    s.student_name,
    g.student_no
having
    student_name like '%람';

--4. 학생별 다음 과목별 수업 정보를 구하라.
-- (group by 없는 단순 조회)
/*
--------------------------------------------
학생명  학기     과목명    학점
-------------------------------------------
감현제	200702	치과분자약리학	4.5
감현제	200701	구강회복학	4
            .
            .
--------------------------------------------
*/
SELECT
    student_name 학생명,
    term_no      학기,
    class_name   과목명,
    point        학점
FROM
    tb_student s
    JOIN tb_class c ON s.department_no = c.department_no
    JOIN tb_grade g ON s.student_no = g.student_no;



-- @실습문제 - chun
-- 1. 재학중인 학번, 학생명, 학과명 조회
SELECT
    student_no      학번,
    student_name    학생명,
    department_name 학과명
FROM
    tb_student s
    JOIN tb_department d ON s.department_no = d.department_no
WHERE
    s.absence_yn = 'N';

-- 2. 의학계열 학과학생들의 학번, 학생명, 학과명 조회
SELECT
    student_no      학번,
    student_name    학생명,
    department_name 학과명
FROM
    tb_student s
    JOIN tb_department d ON s.department_no = d.department_no
WHERE
    category = '의학';
    

-- 3. 수업번호, 수업명, 교수번호, 교수명 조회
SELECT
    class_no       수업번호,
    class_name     수업명,
    professor_no   교수번호,
    professor_name 교수명
FROM
    tb_professor p
    JOIN tb_class c ON p.department_no = c.department_no;

-- 4.  2005학번 학생명, 담당교수명 조회(담당교수 없는 학생도 조회)
SELECT
    student_no     학번,
    student_name   학생명,
    professor_name 교수명
FROM
    tb_student   s
    LEFT JOIN tb_professor p ON s.department_no = p.department_no
WHERE
    EXTRACT(YEAR FROM entrance_date) = 2005;

-- 5. 자연과학 계열의 수업명, 학과명 조회
-- 모든 수업은 학과가 지정되어 있다.
SELECT
    class_name      수업명,
    department_name 학과명
FROM
    tb_class c
    JOIN tb_department d ON c.department_no = d.department_no
WHERE
    d.category = '자연과학';
    

-- 6. 교수번호, 교수명, 담당학생명수 조회
-- 단, 5명 이상의 학생이 담당하는 교수만 출력
SELECT
    *
FROM
    tb_professor; -- 114행

SELECT -- 101행
    professor_no,
    professor_name,
    COUNT(s.coach_professor_no)
FROM
    tb_professor p
    JOIN tb_student s ON p.department_no = s.department_no
GROUP BY
    professor_no,
    professor_name
HAVING
    COUNT(s.coach_professor_no) >= 5;
    
-- 7. 담당학생이 한명도 없는 교수정보 조회
-- 담당교수(coach_professor_no is null)가 없는 학생, 담당학생이 배정되지 교수(professor_no) 제외 inner 579
-- 담당교수가 배정되지 않은 학생 포함 left 588 = 579 + 9
-- 담당학생이 없는 교수 포함 right 580 = 579 + 1
SELECT
    professor_no,
    professor_name
FROM
    tb_professor p
    LEFT JOIN tb_student   s ON p.professor_no = s.coach_professor_no
WHERE
    s.coach_professor_no IS NULL;    

-- 8. 송박선 학생의 모든 학기 과목별 점수를 조회(학기, 학번, 학생명, 수업명, 점수)
SELECT
    g.term_no      학기,
    s.student_no   학번,
    s.student_name 학생명,
    c.class_name   수업명,
    g.point        점수
FROM
    tb_student s
    JOIN tb_class c ON s.department_no = c.department_no
    JOIN tb_grade g ON c.class_no = g.class_no
WHERE
    s.student_name = '송박선';
    
-- 9. 학생별 전체 평점(소수점이하 첫째자리 버림) 조회 (학번, 학생명, 평점)
SELECT
    s.student_no   학번,
    s.student_name 학생명,
    trunc(
        AVG(point), 1
    )              점수
FROM
    tb_student s
    JOIN tb_class c ON s.department_no = c.department_no
    JOIN tb_grade g ON c.class_no = g.class_no
GROUP BY
    s.student_no,
    s.student_name;


--1. 학번, 학생명, 담당교수명을 출력하세요.
--담당교수가 없는 학생은 '없음'으로 표시
select
    student_no,
    student_name,
    -- select절에 쓸 수 있는 sacla 서브쿼리는 무조건 1행 1열이여야 함 (다수의 결과가 조회되면 안됨)
    nvl((select professor_name from tb_professor where professor_no = s.COACH_PROFESSOR_NO),'없음') 담당교수명
from
    tb_student s;
    
-- 2. 이름이 [~람]인 학생의 평균학점을 구해서 학생명과 평균학점(반올림해서 소수점둘째자리까지)과 같이 출력.
select
    student_name,
    (select round(avg(point),2) from tb_grade where student_no = s.student_no)
from
    tb_student s
where
    student_name like '%람';
    
--3. 학생별 다음 과목별 수업정보를 구하라.
/*
--------------------------------------------
학생명     학기     과목명            학점
--------------------------------------------
감현제    200702    치과분자약리학    4.5
감현제    200701    구강회복학        4
            .
            .
--------------------------------------------
*/
select 
    (select student_name from tb_student where student_no = g.student_no) student_name, 
    term_no, 
    (select class_name from tb_class where class_no = g.class_no) class_name, 
    point
from 
    tb_grade g
    -- student 기준으로 조회하면 select에서 scala 서브쿼리를 사용할 수 없음 (여러 결과가 조회되기 때문에)
    -- 단일 행 하위 질의에 2개 이상의 행이 리턴되었습니다 오류가 뜸
order by 1;







--1. 영어영문학과(학과코드 002) 학생들의 학번과 이름, 입학 년도를 입학 년도가 빠른
-- 순으로 표시하는 SQL 문장을 작성하시오.( 단, 헤더는 "학번", "이름", "입학년도" 가
-- 표시되도록 핚다.)
select
    student_no 학번,
    student_name 이름,
    to_char(entrance_date, 'rrrr-mm-dd') 입학년도
from
    tb_student
where
    DEPARTMENT_NO = '002'
order by
    3;

-- 2. 춘 기술대학교의 교수 중 이름이 세 글자가 아닌 교수가 핚 명 있다고 핚다. 그 교수의
-- 이름과 주민번호를 화면에 출력하는 SQL 문장을 작성해 보자. (* 이때 올바르게 작성핚 SQL
-- 문장의 결과 값이 예상과 다르게 나올 수 있다. 원인이 무엇일지 생각해볼 것)
select
    professor_name 교수이름,
    professor_ssn 주민번호
from   
    tb_professor
where
    length(professor_name) != 3;
    
-- 3. 춘 기술대학교의 남자 교수들의 이름과 나이를 출력하는 SQL 문장을 작성하시오. 단
-- 이때 나이가 적은 사람에서 맋은 사람 순서로 화면에 출력되도록 맊드시오. (단, 교수 중
-- 2000 년 이후 출생자는 없으며 출력 헤더는 "교수이름", "나이"로 핚다. 나이는 ‘맊’으로
-- 계산핚다.)   
select
    professor_name 교수이름,
    TO_NUMBER(TO_CHAR(SYSDATE, 'YYYY')) - TO_NUMBER('19' || SUBSTR(PROFESSOR_SSN, 1, 2)) AS 나이,
    TRUNC(
        MONTHS_BETWEEN(
            SYSDATE,  
            TO_DATE('19' ||SUBSTR(PROFESSOR_SSN, 1, 6),'RRRRMMDD'))/12
    ) 만나이
from
    tb_professor
where
    substr(professor_ssn,8,1) = 1
order by
    2, 1;

--4. 교수들의 이름 중 성을 제외핚 이름맊 출력하는 SQL 문장을 작성하시오. 출력 헤더는
-- ‚이름‛ 이 찍히도록 핚다. (성이 2 자인 경우는 교수는 없다고 가정하시오)
select
    substr(professor_name,2) 이름
from   
    tb_professor;
    
-- 5. 춘 기술대학교의 재수생 입학자를 구하려고 핚다. 어떻게 찾아낼 것인가? 이때,
-- 19 살에 입학하면 재수를 하지 않은 것으로 갂주핚다.
select
    student_no,
    student_name
from
    tb_student
where
      extract(year from entrance_date) - (DECODE(SUBSTR(STUDENT_SSN, 8, 1), '1', 1900, '2', 1900, 2000) + SUBSTR(STUDENT_SSN, 1, 2)) > 19
order by
    1;
    
-- 6. 2020 년 크리스마스는 무슨 요일인가?
select
    to_char(to_date('2020-12-25'), 'day') 
from 
    dual;

-- 7. TO_DATE('99/10/11','YY/MM/DD'), TO_DATE('49/10/11','YY/MM/DD') 은 각각 몇 년 몇
-- 월 몇 일을 의미핛까? 또 TO_DATE('99/10/11','RR/MM/DD'),
-- TO_DATE('49/10/11','RR/MM/DD') 은 각각 몇 년 몇 월 몇 일을 의미핛까?

--RRRR YYYY
--RR YY
--YY : 현재년도기준으로 한세기(00~99)에서 판단. 2020 -> 2000 ~ 2099
--RR : 현재년도기준으로 한세기(50~49)에서 판단. 2020 -> 1950 ~ 2049,  2060 -> 2050 ~ 2149
select
    to_char(to_date('99/10/11','YY/MM/DD'), 'yyyy"년" mm"월" dd"일"') "1",
    to_char(to_date('49/10/11','YY/MM/DD'), 'yyyy"년" mm"월" dd"일"') "2",
    to_char(to_date('99/10/11','RR/MM/DD'), 'rrrr"년" mm"월" dd"일"') "3",
    to_char(to_date('49/10/11','RR/MM/DD'), 'rrrr"년" mm"월" dd"일"') "4"
from
    dual;

-- 8. 춘 기술대학교의 2000 년도 이후 입학자들은 학번이 A 로 시작하게 되어있다. 2000 년도
-- 이젂 학번을 받은 학생들의 학번과 이름을 보여주는 SQL 문장을 작성하시오.
select
    student_no,
    student_name
from
    tb_student
where
    student_no not like 'A%'
order by
    1;

-- 9. 학번이 A517178 인 핚아름 학생의 학점 총 평점을 구하는 SQL 문을 작성하시오. 단,
-- 이때 출력 화면의 헤더는 "평점" 이라고 찍히게 하고, 점수는 반올림하여 소수점 이하 핚
-- 자리까지맊 표시핚다.
select
    round(avg(point),1) 평점
from
    tb_grade
where
    student_no = 'A517178';

-- 10. 학과별 학생수를 구하여 "학과번호", "학생수(명)" 의 형태로 헤더를 맊들어 결과값이
-- 출력되도록 하시오.
select
    department_no "학과번호",
    count(*) "학생수(명)"
from
    tb_student
group by
    department_no
order by
    1;
--학생이 없는 학과도 조회
SELECT 
    D.DEPARTMENT_NO AS 학과번호,
    COUNT(S.STUDENT_NO) AS "학생수(명)"
FROM   
    TB_STUDENT S RIGHT JOIN TB_DEPARTMENT D
        ON S.DEPARTMENT_NO = D.DEPARTMENT_NO
GROUP BY 
    D.DEPARTMENT_NO
ORDER BY 
    1;
    
-- 11. 지도 교수를 배정받지 못핚 학생의 수는 몇 명 정도 되는 알아내는 SQL 문을 작성하시오.
select
    count(*)
from
    tb_student
group by
    coach_professor_no
having
    coach_professor_no is null;

-- 12. 학번이 A112113 인 김고운 학생의 년도 별 평점을 구하는 SQL 문을 작성하시오. 단,
-- 이때 출력 화면의 헤더는 "년도", "년도 별 평점" 이라고 찍히게 하고, 점수는 반올림하여
-- 소수점 이하 핚 자리까지맊 표시핚다.
select
    substr(term_no,1,4) "년도",
    round(avg(point),1) "년도 별 평점"
from
    tb_grade
where
    student_no = 'A112113'
group by
    substr(term_no,1,4);
    
    
    
-- 13. 학과 별 휴학생 수를 파악하고자 핚다. 학과 번호와 휴학생 수를 표시하는 SQL 문장을
-- 작성하시오. 
-- count(*)시에 휴학생이 0명인 학과 표시가 안되는데 출력 방법을 잘 모르겠습니다.
select
    department_no, 
    count(*)
from
    tb_student
where
    absence_yn = 'Y'
group by
    department_no
order by
    1;

--13.
SELECT 
    DEPARTMENT_NO AS 학과코드명,
    SUM(CASE  WHEN ABSENCE_YN = 'Y' THEN 1  ELSE 0 END) AS "휴학생 수"
FROM 
    TB_STUDENT
GROUP BY 
    DEPARTMENT_NO
ORDER BY
    1;

-- 14. 춘 대학교에 다니는 동명이인(同名異人) 학생들의 이름을 찾고자 핚다. 어떤 SQL
-- 문장을 사용하면 가능하겠는가?
select
    student_name,
    count(*)
from
    tb_student
group by
    student_name
having
    count(student_name) > 1
order by
    1;
    
-- 15. 학번이 A112113 인 김고운 학생의 년도, 학기 별 평점과 년도 별 누적 평점 , 총
-- 평점을 구하는 SQL 문을 작성하시오. (단, 평점은 소수점 1 자리까지맊 반올림하여
-- 표시핚다.)
select
    nvl(substr(term_no,1,4), '__') "년도", -- '   '일시 order by가 제대로 작동하지 않음
    nvl(substr(term_no,5,6), '__') "학기",   
    round(avg(point),1) "평점"
from
    tb_grade
where
    student_no = 'A112113'
group by
    rollup(substr(term_no,1,4), substr(term_no,5,6))
order by
    1,2;
    
-- 15.
--grouping : 실제 데이터 0, 집계데이터 1
SELECT * FROM TB_GRADE WHERE  STUDENT_NO = 'A112113';
-- grouping(col) : 실제 데이터 컬럼은 0을 반환, rollup등에 의해 만들어진 컬럼인 경우 1 반환
SELECT 
    DECODE(GROUPING(SUBSTR(TERM_NO, 1, 4)), 0, SUBSTR(TERM_NO, 1, 4), '___') 년도,
    DECODE(GROUPING(SUBSTR(TERM_NO, 5, 2)), 0, SUBSTR(TERM_NO, 5, 2), '___') 학기,
    ROUND(AVG(POINT), 1) 평점
FROM 
    TB_GRADE
WHERE  
    STUDENT_NO = 'A112113'
GROUP BY 
    ROLLUP(
        SUBSTR(TERM_NO, 1, 4), 
        SUBSTR(TERM_NO, 5, 2)
    )    
ORDER BY
    1, 2;

--DECODE이용
SELECT 
    DECODE(GROUPING(SUBSTR(TERM_NO, 1, 4)),0,SUBSTR(TERM_NO, 1, 4),1,'총평점') AS 년도,
    DECODE(GROUPING(SUBSTR(TERM_NO, 5, 2)),0,SUBSTR(TERM_NO, 5, 2),1,'연별누적평점') AS 학기,
    ROUND(AVG(POINT), 1) AS 평점
FROM
    TB_GRADE
WHERE
    STUDENT_NO = 'A112113'
GROUP BY 
    ROLLUP(SUBSTR(TERM_NO, 1, 4),SUBSTR(TERM_NO, 5, 2));

--CASE이용
SELECT 
    DECODE(GROUPING(SUBSTR(TERM_NO, 1, 4)),0,SUBSTR(TERM_NO, 1, 4),1,'총평점') AS 년도,
    CASE 
        WHEN GROUPING(SUBSTR(TERM_NO, 1, 4)) = 1 AND GROUPING(SUBSTR(TERM_NO, 5, 2))=1 THEN ' '
        WHEN GROUPING(SUBSTR(TERM_NO, 5, 2)) = 1 THEN '연별누적평점'
        ELSE SUBSTR(TERM_NO, 5, 2) 
    END AS 구분,
    ROUND(AVG(POINT), 1) AS 평점
FROM   
    TB_GRADE
WHERE  
    STUDENT_NO = 'A112113'
GROUP BY 
    ROLLUP(SUBSTR(TERM_NO, 1, 4),SUBSTR(TERM_NO, 5, 2));