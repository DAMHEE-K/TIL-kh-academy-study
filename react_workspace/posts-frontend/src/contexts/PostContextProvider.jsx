import React, { createContext, useState } from 'react'
import axios from 'axios';

export const PostContext = createContext();

const PostContextProvider = (props) => {
  const [posts, setPosts] = useState([]);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);

  const getPosts = () => {
    axios(`/posts?page=${page}`)
      .then((response) => {
        console.log(response);
        const {content, number : page, totalElements, totalPages} = response.data;
        setPosts(content);
        // setPage(page); // 이전/다음 연속 클릭시 버그방지
        setTotalPages(totalPages);
      });
  };
  const getPost = async (id) => {
    return await axios(`/posts/${id}`).then((response) => response.data);
  };
  const createPost = async (post) => {
    const response = await axios.post(`/posts`, post);
    console.log(response); // 201 Created - header.location
  };
  const updatePost = async (post) => {
    return await axios.patch(`/posts/${post.id}`, post);
  };
  const deletePost = async (id) => {
    return await axios.delete(`/posts/${id}`);
  };

  // 실제 context에 의해 관리될 속성들 (상태값, 함수)
  const value = {
    states : {
      posts, 
      page,
      totalPages
    },
    actions: {
      getPosts,
      getPost,
      createPost,
      updatePost,
      deletePost,
      setPage
    }
  };
  return (
    <PostContext.Provider value={value}>
      {props.children}
    </PostContext.Provider>
  )
}

export default PostContextProvider