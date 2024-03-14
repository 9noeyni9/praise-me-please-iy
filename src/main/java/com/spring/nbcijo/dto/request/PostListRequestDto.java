package com.spring.nbcijo.dto.request;

import com.spring.nbcijo.global.dto.request.ListRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class PostListRequestDto extends ListRequestDto {

    private String title;
    private String content;

    @Builder
    public PostListRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
