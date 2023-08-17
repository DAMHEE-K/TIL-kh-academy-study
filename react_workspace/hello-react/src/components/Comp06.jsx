import React, { useState } from 'react'
import Product from './Product';

const Comp06 = () => {
  const [products, setProducts] = useState([
    {code : 1, name : '세탁기', price : 3_000_000},
    {code : 2, name : '냉장고', price : 4_000_000},
    {code : 3, name : 'TV', price : 1_500_000},
  ]);

  const [frm, setFrm] = useState({
    code : 4,
    name : '공기청정기',
    price : 500_000
  });

  const onFrmChange = (e) => {
    // state를 변경할때는 매번 새객체를 전달해야 한다. 
    // (리액트는 기존객체를 변경할때 변경을 감지하지 못한다.)
    console.log(e.target.name, e.target.value);

    // 속성명의 []를 통해 동적데이터를 속성명(식별자)로 등록가능
    setFrm({
      ...frm,
      [e.target.name] : e.target.value
    });
  };

  const onSubmit = () => {
    // 새 배열에 담아 setter 호출
    // setProducts([...products, frm]);
    products.push(frm);
    setProducts([...products]);

    setFrm({
      code : code + 1,
      name : '',
      price : 0
    });
  }

  const {code, name, price} = frm;

  return (
    <div>
      <h3>사용자입력 form</h3>
      <fieldset>
        <div>
          <input type="text" name='code' placeholder='상품코드' value={code} onChange={onFrmChange}/>
        </div>
        <div>
          <input type="text" name='name' placeholder='상품명' value={name} onChange={onFrmChange}/>
        </div>
        <div>
          <input type="text" name='price' placeholder='가격' value={price} onChange={onFrmChange}/>
        </div>
        <div>
          <button onClick={onSubmit}>제출</button>
          {' '}
          <button>초기화</button>
        </div>
      </fieldset>
      <hr />
      <table>
        <thead>
          <tr>
            <th width={120}>상품<br/>코드</th>
            <th width={300}>상품명</th>
            <th width={150}>가격</th>
          </tr>
        </thead>
        <tbody>
          {products.map((product) => <Product key={product.code} product={product}/>)}
        </tbody>
      </table>
    </div>
  )
}

export default Comp06