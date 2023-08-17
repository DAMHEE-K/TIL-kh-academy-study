import React from 'react'
import Student from './Student'

/**
 * Props 
 * - 부모컴포넌트에서 자식컴포넌트로 전달되는 속성값
 * - inline속성 또는 객체로 묶어 전달.
 */
const Comp02 = () => {
  const no = 123;
  const name = '홍길동';
  const dept = '컴퓨터 공학과';
  
  const student = {
    no, name, dept
  }
  return (
    <>
      <h2>Props</h2>
      <div className='wrapper'>
        {/* <Student no={no} name={name} dept={dept} /> */}
        <Student student={student} />
      </div>

    </>
  )
}

export default Comp02