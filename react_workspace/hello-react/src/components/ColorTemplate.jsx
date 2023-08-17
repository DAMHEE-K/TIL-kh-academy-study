import React from 'react'
import ColorButton from './ColorButton'
import ColorHeader from './ColorHeader'

const ColorTemplate = () => {
  return (
    <div>
      <ColorHeader text="안녕하세요"/>
      <ColorButton text='빨강' _color='red'/>
      {' '}
      <ColorButton text='초록' _color='green'/>
      {' '}
      <ColorButton text='파랑' _color='blue'/>
    </div>
  )
}

export default ColorTemplate