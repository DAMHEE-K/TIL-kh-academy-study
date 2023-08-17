import React from 'react'

const Product = ({product}) => {
  const {code, name, price} = product;
  return (
    <tr>
      <td>{code}</td>
      <td>{name}</td>
      <td>￦{price.toLocaleString()}</td>
    </tr>
  )
}

export default Product