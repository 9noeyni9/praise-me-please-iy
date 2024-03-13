package com.spring.nbcijo.dto.response;

import com.spring.nbcijo.global.dto.response.ListResponseDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PostListResponseDto extends ListResponseDto {

    private List<PostResponseDto> postList;
}
