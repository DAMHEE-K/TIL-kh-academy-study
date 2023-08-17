import React, { useRef, useState } from 'react';

const Comp05 = () => {
  const [name, setName] = useState('홍길동');
  const [addr, setAddr] = useState('서울시 강남구 역삼동');
  const nameRef = useRef();

  const onButtonClick = () => {
    alert(`
  이름 : ${name}
  주소 : ${addr}
  위 내용이 맞습니까?    
`);
    // 초기화
    setName('');
    setAddr('');
    // name태그 포커싱
    console.log(nameRef);
    nameRef.current.focus();
  };
  return (
    <div>
      <h2>사용자입력</h2>
      <fieldset>
        <div>
          <input ref={nameRef} type="text" placeholder='이름' value={name} onChange={(e) => setName(e.target.value)}/>
        </div>
        <div>
          <input type="text" placeholder='주소' value={addr} onChange={(e) => setAddr(e.target.value)}/>
        </div>
        <div>
          <button onClick={onButtonClick}>확인</button>
        </div>
      </fieldset>
      <p>{name}</p>
      <p>{addr}</p>
    </div>
  )
}

export default Comp05