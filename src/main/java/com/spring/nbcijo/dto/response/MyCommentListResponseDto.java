package com.spring.nbcijo.dto.response;

import com.spring.nbcijo.global.dto.response.ListResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyCommentListResponseDto extends ListResponseDto {

    private List<MyCommentResponseDto> myCommentList;
}
