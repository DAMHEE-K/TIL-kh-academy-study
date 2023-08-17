import React, { useContext, useEffect } from 'react'
import { Button, Col, Row, Table } from 'react-bootstrap'
import { Link } from 'react-router-dom'
import { PostContext } from '../contexts/PostContextProvider';
import Post from '../components/Post';
import PagingButtons from '../components/PagingButtons';

const PostList = () => {
  const postContext = useContext(PostContext);
  console.log(postContext);
  const {states : {posts, page}, actions : {getPosts}} = postContext;

  useEffect(() => {
    getPosts(page); 
  }, [page]); // page값이 변경될때마다 useEffect 콜백 호출

  return (
    <div>
      <Row>
        <Col>
          <h1>게시글 목록</h1>
          <div className="text-end mb-3">
            <Link to='/posts/create'>
              <Button>글쓰기</Button>
            </Link>
          </div>
          <Table striped bordered hover>
            <thead>
              <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일시</th>
              </tr>
            </thead>
            <tbody>
              {posts.map((post) => <Post key={post.id} post={post} />)}
            </tbody>
          </Table>
          <PagingButtons/>
        </Col>        
      </Row>
    </div>
  )
}

export default PostList