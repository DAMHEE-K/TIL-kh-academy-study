// rafce

import React from 'react'

/**
 * JSX
 * - xml
 * - 최상위태그는 하나여야 한다. 
 *    - <></> 사용가능
 * - 모든 태그는 종료되어야 한다. <div></div> <App />
 * - jsx 내부에서 {}는 js공간이다. 
 * - js문법요소와 겹치는 html키워드를 사용할 수 없다. class -> className, for -> htmlFor
 * - 이벤트속성은 카멜케이싱적용필수! onclick -> onClick 
 * - jsx내에서는 조건문/반복문 사용불가. 
 *    - 조건문 삼항연산자/짧은 조건문(&&, ||)
 *    - 반복문 배열메소드사용 Array#map
 * 
 */
const Comp01 = () => {
  const title = "Comp01";
  const age = 17;
  const gender = 'M';
  const married = false;
  const names = ['홍길동', '신사임당', '세종대왕'];
  const style = {
    color : "red",
    fontSize : "32px"
  };
  return (
    <>
      {/* jsx 주석 */}
      <div style={style}>{title}</div>
      {age < 20 && <p>미성년자입니다.</p>}
      {age >= 20 && <p>성인입니다.</p>}
      <mark>{gender === 'M' ? '남' : '여'}</mark>
      <div>
        <input type="checkbox" checked={married}/>
      </div>
      <ul style={{color : 'navy', 'listStyle' : 'none'}}>
        {names.map((name, index) => <li key={index}>{name}</li>)}
      </ul>
      <p className="sample">Helloworld</p>

    </>
  )
}

export default Comp01