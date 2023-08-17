import React, { useState } from 'react'

const Counter = () => {
  // useState반환값 [변수, setter]
  const [cnt, setCnt] = useState(0);
  return (
    <div>
      <h3>{cnt}</h3>
      <button onClick={() => setCnt(cnt + 1)}>+</button>
      <button onClick={() => setCnt(cnt - 1)}>-</button>
    </div>
  )
}

export default Counter