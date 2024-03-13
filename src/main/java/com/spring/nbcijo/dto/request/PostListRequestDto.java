package com.spring.nbcijo.dto.request;

import com.spring.nbcijo.global.dto.request.ListRequestDto;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PostListRequestDto extends ListRequestDto {

    private String title;
    private String content;
    private LocalDateTime createdAt;
}
