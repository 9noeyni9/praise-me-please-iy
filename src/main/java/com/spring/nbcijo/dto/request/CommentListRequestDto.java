package com.spring.nbcijo.dto.request;

import com.spring.nbcijo.global.dto.request.ListRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class CommentListRequestDto extends ListRequestDto {

    private String content;

    @Builder
    public CommentListRequestDto(String content){
        this.content = content;
    }
}
