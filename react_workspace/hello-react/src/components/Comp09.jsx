import React from 'react'
import { Link, Route, Routes } from 'react-router-dom'
import A from './A'
import B from './B'

const Comp09 = () => {
  return (
    <div>
      <h2>BrowserRouter</h2>
      {/* Link 시각화될 링크 */}
      <nav>
        <Link to='/a'>a</Link>
        {' '}
        <Link to='/b'>b</Link>
      </nav>
      {/* Route 리액트에 저장할 경로/컴포넌트 매핑 */}
      <Routes>
        <Route path='/a' element={<A />}/>
        <Route path='/b' element={<B />}/>
      </Routes>
    </div>
  )
}

export default Comp09