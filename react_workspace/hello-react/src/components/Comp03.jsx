import React from 'react'
import Student from './Student';
import StudentTr from './StudentTr';

const Comp03 = () => {
  const students = [
    {no : 1, name : '홍길동', dept : "컴퓨터공학과"},
    {no : 2, name : '신사임당', dept : "무역학과"},
    {no : 3, name : '세종대왕', dept : "국어국문학과"},
  ];
  return (
    <div>
      <h2>Props 배열</h2>
      <div className="wrapper">
        {students.map((student) => <Student key={student.no} student={student}/>)}
      </div>
      <div>
        <table>
          <thead>
            <tr>
              <th>학번</th>
              <th>이름</th>
              <th>학과</th>
            </tr>
          </thead>
          <tbody>
            {students.map((student) => <StudentTr key={student.no} student={student}/>)}
          </tbody>
        </table>
      </div>
    </div>
  )
}

export default Comp03