import React from 'react'
import { Container, Nav, Navbar } from 'react-bootstrap'
import { Outlet, useNavigate } from 'react-router-dom'

const Layout = () => {
  const navigate = useNavigate();
  const onLinkClick = (e) => {
    e.preventDefault(); // a태그 페이지이동 방지
  
    const path = e.target.dataset.to;
    console.log(path);
    navigate(path);
  };
  
  return (
    <div>
      <Navbar bg="primary" data-bs-theme="dark">
        <Container>
          <Navbar.Brand href="/" data-to="/" onClick={onLinkClick}>React</Navbar.Brand>
          <Nav className="me-auto">
            <Nav.Link href="/" data-to="/" onClick={onLinkClick}>Home</Nav.Link>
            <Nav.Link href="/posts" data-to="/posts" onClick={onLinkClick}>Posts</Nav.Link>
          </Nav>
        </Container>
      </Navbar>
      <div className='p-3'>
        <Outlet/>
      </div>
    </div>
  )
}

export default Layout