import React from 'react'
import { Link } from 'react-router-dom';

const Post = ({post}) => {
  const {id, title, writer, content, createdAt} = post;
  return (
    <tr>
      <td>{id}</td>
      <td>
        <Link to={`/posts/detail/${id}`}>
          {title}
        </Link>
      </td>
      <td>{writer}</td>
      <td>{createdAt}</td>
    </tr>
  )
}

export default Post