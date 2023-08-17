import React, { useState } from 'react'
import Counter from './Counter'
import ColorState from './ColorState'

/**
 * State
 * - 컴포넌트 내부에 변경가능한 값을 관리.
 * - 상태관리 - 화면렌더를 묶어서 리액트가 처리. 
 * - useState hooks로 사용
 * 
 */
const Comp04 = () => {
  
  return (
    <div>
      <h2>State</h2>
      <Counter/>
      <Counter/>
      <ColorState/>
    </div>
  )
}

export default Comp04