import React, { useContext, useState } from 'react';
import { Button, Col, Form, FormControl, Row } from 'react-bootstrap';
import { PostContext } from '../contexts/PostContextProvider';
import { Link, useNavigate } from 'react-router-dom';

const PostCreate = () => {
  const navigate = useNavigate();
  const {actions : {createPost}} = useContext(PostContext);
  const [frm, setFrm] = useState({
    title : 'a',
    writer : 'b',
    content : 'c'
  });
  const onFrmChange = (e) => {
    setFrm({
      ...frm,
      [e.target.name] : e.target.value
    });
  };
  const onFrmSubmit = (e) => {
    e.preventDefault();
    // 유효성검사
    // ...

    createPost(frm)
      .then(() => navigate('/posts'));

  };
  const {title, writer, content} = frm;
  return (
    <div>
      <Row>
        <Col>    
          <h1>게시글 등록</h1>
          <Form onSubmit={onFrmSubmit}>
            <FormControl name="title" className='my-3' placeholder='제목' value={title} onChange={onFrmChange}></FormControl>
            <FormControl name="writer" className='my-3' placeholder='작성자' value={writer} onChange={onFrmChange}></FormControl>
            <FormControl name="content" className='my-3' as='textarea' rows='5' placeholder='내용' value={content} onChange={onFrmChange}></FormControl>
            <div className='text-center'>
              <Button type="submit" className='me-3'>등록</Button>
              <Button variant='secondary'>취소</Button>
            </div>
          </Form>
        </Col>
      </Row>
    </div>
  )
}

export default PostCreate