package com.sh.app.posts.controller;

import com.sh.app.posts.dto.PostCreateDto;
import com.sh.app.posts.dto.PostSearchDto;
import com.sh.app.posts.dto.PostUpdateDto;
import com.sh.app.posts.entity.Post;
import com.sh.app.posts.service.PostsService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
@Slf4j
public class PostsController {
    private final PostsService postsService;

//    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(postsService.findAll());
    }

    @GetMapping
    public ResponseEntity<?> findAll(@PageableDefault(value = 5, page = 0) Pageable pageable){
        // di받지 않는다면... Pageable객체 직접 생성
//        Pageable pageable = PageRequest.of(0, 5);

        Page<Post> result = postsService.findAll(pageable);
        return ResponseEntity.ok(result);
    }

    /**
     * jpql 사용버젼
     * @return
     */
    @GetMapping("/totalCount")
    public ResponseEntity<?> getTotalCount() {
        return ResponseEntity.ok(postsService.getTotalCount());
    }

    @GetMapping("/search")
    public ResponseEntity<?> findByContentLike(@RequestParam String content) {
        return ResponseEntity.ok(postsService.findByContentLike(content));
    }
    @GetMapping("/search2")
    public ResponseEntity<?> findByContentLikeAndWriterLike(@RequestParam String content, @RequestParam String writer) {
        return ResponseEntity.ok(postsService.findByContentLikeAndWriterLike(content, writer));
    }
    @GetMapping("/search3")
    public ResponseEntity<?> findByExample(PostSearchDto postSearchDto) {
        log.debug("postSearchDto = {}", postSearchDto);
        return ResponseEntity.ok(postsService.findByExample(postSearchDto.toPost()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Post post = postsService.findById(id);
        if(post == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(post);
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody @Valid PostCreateDto postCreateDto) {
        Post post = postsService.save(postCreateDto.toPost());
        return ResponseEntity.created(URI.create("/posts/" + post.getId())).build();
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody @Valid PostUpdateDto postUpdateDto){
        Post post = postsService.findById(id);
        if(post == null)
            return ResponseEntity.notFound().build();
        post = postUpdateDto.toPost(post);
        postsService.save(post);
        return ResponseEntity.ok(post);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id){
        Post post = postsService.findById(id);
        if(post == null)
            return ResponseEntity.notFound().build();
        postsService.delete(post);
        return ResponseEntity.noContent().build();
    }
}
