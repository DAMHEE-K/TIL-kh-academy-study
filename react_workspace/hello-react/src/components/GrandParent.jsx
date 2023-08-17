import React, { useContext, useState } from 'react'
import Parent from './Parent'
import { FamilyContext } from './Comp07';

const GrandParent = () => {
  const familyContext = useContext(FamilyContext);
  console.log(familyContext); // {data: '가보', setData: ƒ}
  const {data} = familyContext;
  return (
    <div className='family grand-parent'>
      <h3>GrandParent {data}</h3>
      <Parent /> 
    </div>
  )
}

export default GrandParent