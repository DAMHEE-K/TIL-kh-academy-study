import React, { useContext } from 'react'
import { ColorContext } from '../contexts/ColorContextProvider';


const ColorHeader = ({text}) => {
  const {color} = useContext(ColorContext);
  return (
    <div>
      <h3 style={{color}}>{text}</h3>
    </div>
  )
}

export default ColorHeader