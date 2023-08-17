import React, { useEffect, useState } from 'react'
import axios from 'axios'

/**
 * 리액트에서 서버와의 통신 항상 비동기로 처리한다. SPA
 * - js fetch
 * - axios (라이브러리 설치 필수)
 * @returns 
 */
const Comp08 = () => {
  const [posts, setPosts] = useState([]);
  const [loaded, setLoaded] = useState(false);

  const requestApi = () => {
    // fetch('https://jsonplaceholder.typicode.com/posts')
    //   .then((response) => response.json())
    //   .then((data) => console.log(data));

    axios('https://jsonplaceholder.typicode.com/posts')
      .then((response) => {
        console.log(response.data);
        setPosts(response.data);
        setLoaded(true);
      });
  };
  // component로드시 1회 작동 : 비어있는 의존배열을 작성
  useEffect(() => {
    requestApi();
  }, []);

  return (
    <div>
      <h2>비동기통신</h2>
      <div style={{textAlign: "left"}}>
        {
          loaded ||
            <p>로딩중...</p>
        }
        { 
          loaded && 
            posts.map((post) => <p key={post.id}>{post.id} {post.title}</p>)
        }
      </div>

    </div>
  )
}

export default Comp08