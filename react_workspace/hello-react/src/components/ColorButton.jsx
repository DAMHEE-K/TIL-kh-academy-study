import React, { useContext } from 'react'
import { ColorContext } from '../contexts/ColorContextProvider';

const ColorButton = ({text, _color}) => {
  const {setColor} = useContext(ColorContext);
  return (
    <button onClick={() => setColor(_color)}>{text}</button>
  )
}

export default ColorButton