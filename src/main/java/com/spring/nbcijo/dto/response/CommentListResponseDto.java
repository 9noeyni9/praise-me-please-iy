package com.spring.nbcijo.dto.response;

import com.spring.nbcijo.global.dto.response.ListResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CommentListResponseDto extends ListResponseDto {

    private List<CommentResponseDto> commentList;
}
