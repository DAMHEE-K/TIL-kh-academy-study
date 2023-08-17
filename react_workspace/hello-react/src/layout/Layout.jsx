import React from 'react'
import { Link, Outlet } from 'react-router-dom'

const Layout = () => {
  return (
    <div>
      <header>헤더</header>
      <aside>
        <nav>
          <Link to='/a'>a</Link>
          {' '}
          <Link to='/b'>b</Link>
        </nav>
      </aside>
      <main>
        {/* 경로에 맞는 컴포넌트가 실제 렌더됨 */}
        <Outlet/>
      </main>
      <footer>푸터</footer>
    </div>
  )
}

export default Layout