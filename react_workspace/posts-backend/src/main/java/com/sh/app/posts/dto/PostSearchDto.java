package com.sh.app.posts.dto;

import org.springframework.lang.Nullable;

import com.sh.app.posts.entity.Post;

import lombok.Data;

@Data
public class PostSearchDto {
    private String title;
    private String writer;
    private String content;

    public void setTitle(String title) {
        this.title = trimValueOrNull(title);
    }
    public void setWriter(String writer) {
        this.writer = trimValueOrNull(writer);
    }
    public void setContent(String content) {
        this.content = trimValueOrNull(content);
    }
    public String trimValueOrNull(@Nullable String v) {
        if(v == null)
            return null;
        v = v.trim();
        return !"".equals(v) ? v : null;
    }

    public Post toPost() {
        return Post.builder()
                .title(title)
                .writer(writer)
                .content(content)
                .build();
    }
}
