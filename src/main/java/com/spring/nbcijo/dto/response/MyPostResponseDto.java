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
    private String username;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public MyPostResponseDto(Long postId, String username, String title, String content,
        LocalDateTime createdAt) {
        this.postId = postId;
        this.username = username;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }
}
