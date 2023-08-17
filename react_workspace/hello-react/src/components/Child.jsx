import React, { useContext } from 'react'
import { FamilyContext } from './Comp07'

const Child = () => {
  const {data, setData} = useContext(FamilyContext);
  return (
    <div className='family child'>
      <h5>Child {data}</h5> 
      {/* 변경버튼 클릭시 '가보'가 '땅 만평'으로 변경 */}
      <button onClick={() => setData('땅 만평')}>변경</button>
    </div>
  )
}

export default Child