import React, { useContext, useEffect, useState } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom';
import { PostContext } from '../contexts/PostContextProvider';
import { Badge, Button, Card } from 'react-bootstrap';

const PostDetail = () => {
  const navigate = useNavigate();
  // 경로변수 
  const params = useParams();
  console.log(params);
  const {id} = params;

  const {actions : {getPost, deletePost}} = useContext(PostContext);

  const [post, setPost] = useState({
    id : '',
    title : '',
    writer : '',
    content : '',
    createdAt : ''
  });

  useEffect(() => {
    getPost(id)
      .then((post) => {
        console.log(post);
        setPost(post);
      });
  }, [])

  const onDelBtnClick = () => {
    if(window.confirm('정말 삭제하시겠습니까?')) {
      deletePost(id)
        .then((response) => {
          console.log(response);
          navigate("/posts");
        });
    }
  };

  const {title, writer, content, createdAt} = post;

  return (
    <div>
      <h1>게시글 상세보기</h1>
      <div className='my-2'>
        <Link to={`/posts/update/${id}`}>
          <Button variant='outline-info'>수정</Button>
        </Link>
        {' '}
        <Button variant='outline-danger' onClick={onDelBtnClick}>삭제</Button>
      </div>
      <Card className="text-center">
        <Card.Header>
          <Badge bg='primary'>{id}</Badge>
        </Card.Header>
        <Card.Body>
          <Card.Title>{title}</Card.Title>
          <Card.Text>
            {content}
          </Card.Text>
        </Card.Body>
        <Card.Footer className="text-muted">
          Created on {createdAt} by {writer}
        </Card.Footer>
      </Card>

    </div>
  )
}

export default PostDetail