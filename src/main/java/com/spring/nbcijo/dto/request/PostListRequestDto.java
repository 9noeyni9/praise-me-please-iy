package com.spring.nbcijo.dto.request;

import com.spring.nbcijo.dto.response.PostListResponseDto;
import com.spring.nbcijo.global.dto.request.ListRequestDto;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostListRequestDto extends ListRequestDto {

    private String title;
    private String content;
    private LocalDateTime createdAt;

    @Builder
    public PostListRequestDto(String title, String content, LocalDateTime createdAt){
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }
}
