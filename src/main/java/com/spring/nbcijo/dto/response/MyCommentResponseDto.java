package com.spring.nbcijo.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Builder
public class MyCommentResponseDto {

    private String username;
    private Long postId;
    private String content;
    private LocalDateTime createdAt;

    public MyCommentResponseDto(String username, Long postId, String content,
                                LocalDateTime createdAt) {
        this.username = username;
        this.postId = postId;
        this.content = content;
        this.createdAt = createdAt;
    }
}
