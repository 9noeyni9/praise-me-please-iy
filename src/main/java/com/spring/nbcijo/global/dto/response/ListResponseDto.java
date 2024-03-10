package com.spring.nbcijo.global.dto.response;

import com.spring.nbcijo.global.util.PagingUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ListResponseDto {
    private PagingUtil pagingUtil;
}
