package com.sh.app.posts.repository;

import com.sh.app.posts.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Post, Long> {

    /**
     * jpql
     * @return
     */
    @Query("select count(*) from Post")
    int getTotalCount();


    /**
     * custom 쿼리메소드 작성시 find이후 By를 꼭 작성한다.
     * @param pageable
     * @return
     */
    Page<Post> findAllByOrderByIdDesc(Pageable pageable);

    List<Post> findByContentLike(String content);
    List<Post> findByContentLikeAndWriterLike(String content, String writer);
}
