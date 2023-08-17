import React from 'react'

const Student = ({student}) => {
  console.log(student);
  const {no, name, dept} = student;
  return (
    <div className='item'>
      <p>학번 : {no}</p>
      <p>이름 : {name}</p>
      <p>학과 : {dept}</p>
    </div>
  )
}

export default Student