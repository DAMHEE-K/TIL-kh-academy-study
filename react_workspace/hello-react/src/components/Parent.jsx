import React, { useContext } from 'react'
import Child from './Child'
import { FamilyContext } from './Comp07'

const Parent = () => {
  const {data} = useContext(FamilyContext);
  return (
    <div className='family parent'>
      <h4>Parent {data}</h4>
      <Child />
    </div>
  )
}

export default Parent