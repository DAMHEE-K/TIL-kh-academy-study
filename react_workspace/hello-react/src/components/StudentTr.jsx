import React from 'react'

const StudentTr = ({student}) => {
  const {no, name, dept} = student;
  return (
    <tr>
      <td>{no}</td>
      <td>{name}</td>
      <td>{dept}</td>
    </tr>
  )
}

export default StudentTr