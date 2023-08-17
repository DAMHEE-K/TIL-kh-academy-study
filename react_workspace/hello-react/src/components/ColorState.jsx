import React, { useState } from 'react'

const ColorState = () => {
  const [color, setColor] = useState('red');
  return (
    <div>
      <h3 style={{color : color}}>안녕하세요</h3>
      <button onClick={() => setColor('red')}>빨강</button>
      {' '}
      <button onClick={() => setColor('blue')}>파랑</button>
      {' '}
      <button onClick={() => setColor('hotpink')}>핑크</button>
    </div>
  )
}

export default ColorState