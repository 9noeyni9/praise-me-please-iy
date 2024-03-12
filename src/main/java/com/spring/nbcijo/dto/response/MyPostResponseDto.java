package com.spring.nbcijo.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyPostResponseDto {

    private String username;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    @Builder
    public MyPostResponseDto(String username, String title, String content,
        LocalDateTime createdAt) {
        this.username = username;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }
}
