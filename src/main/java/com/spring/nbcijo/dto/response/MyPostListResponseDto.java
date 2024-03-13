package com.spring.nbcijo.dto.response;

import com.spring.nbcijo.global.dto.response.ListResponseDto;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyPostListResponseDto extends ListResponseDto {

    private List<MyPostResponseDto> myPostList;
}
