import React, { useContext } from 'react'
import { Button } from 'react-bootstrap'
import { PostContext } from '../contexts/PostContextProvider'

const PagingButtons = () => {
  const {states : {page, totalPages}, actions : {setPage}} = useContext(PostContext);
  return (
    <div>
      <Button disabled={page === 0} onClick={() => setPage(page - 1)}>이전</Button>
      <span className='mx-3'>{page + 1} / {totalPages + 1}</span>
      <Button disabled={page === totalPages} onClick={() => setPage(page + 1)}>다음</Button>
    </div>
  )
}

export default PagingButtons