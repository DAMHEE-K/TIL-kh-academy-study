import React, { createContext, useState } from 'react'
import GrandParent from './GrandParent'
import ColorTemplate from './ColorTemplate';
import ColorContextProvider from '../contexts/ColorContextProvider';

/**
 * Context API
 * - 컴포넌트가 데이터전달을 용이하게 하는 react 표준모듈. 
 * - 부모-자식간에만 props전달이 가능한 불편함을 개선. 
 * - 외부라이브러리 redux, zustand 사용도 추천.
 * @returns 
*/
export const FamilyContext = createContext(); 

const Comp07 = () => {
  const [data, setData] = useState('가보');
  
  return (
    <div>
      <h2>Context API</h2>
      <FamilyContext.Provider value={{data, setData}}>
        <GrandParent />
      </FamilyContext.Provider>

      <ColorContextProvider>
        <ColorTemplate />
      </ColorContextProvider>
      <br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
    </div>
  )
}

export default Comp07