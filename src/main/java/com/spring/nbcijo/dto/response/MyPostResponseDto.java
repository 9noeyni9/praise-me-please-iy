package com.spring.nbcijo.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
public class MyPostResponseDto {

    private Long postId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private String username;

    public MyPostResponseDto(Long postId, String title, String content,
        LocalDateTime createdAt, String username) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.username = username;
    }
}
