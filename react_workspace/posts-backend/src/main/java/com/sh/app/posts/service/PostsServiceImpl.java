package com.sh.app.posts.service;

import com.sh.app.posts.entity.Post;
import com.sh.app.posts.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostsServiceImpl implements PostsService{
    private final PostsRepository postsRepository;

    @Override
    public List<Post> findAll() {
        return postsRepository.findAll();
    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return postsRepository.findAllByOrderByIdDesc(pageable);
    }

    @Override
    public Post findById(Long id) {
        return postsRepository.findById(id).orElse(null);
    }

    @Override
    public Post save(Post post) {
        return postsRepository.save(post);
    }

    @Override
    public void delete(Post post) {
        postsRepository.delete(post);
    }

    @Override
    public List<Post> findByExample(Post post) {
        // 대소문자 구분없이 title/writer/content 각 컬럼에서 검색어를 모두 포함하는 ExampleMatcher
        ExampleMatcher customExampleMatcher = ExampleMatcher.matchingAll()
            .withMatcher("title", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("writer", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("content", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        // post 각 컬럼별 검색어가 담겨있는 Post 객체
        Example<Post> example = Example.of(post, customExampleMatcher);
        return postsRepository.findAll(example);
    }

    @Override
    public List<Post> findByContentLike(String content) {
        return postsRepository.findByContentLike("%" + content + "%");
    }

    @Override
    public List<Post> findByContentLikeAndWriterLike(String content, String writer) {
        return postsRepository.findByContentLikeAndWriterLike("%" + content + "%", "%" + writer + "%");
    }

    @Override
    public int getTotalCount() {
        return postsRepository.getTotalCount();
    }
}
