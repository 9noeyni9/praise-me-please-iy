package com.spring.nbcijo.dto.response;

import com.spring.nbcijo.entity.Post;
import java.util.List;
import java.util.stream.Collectors;

public class PostResponseDtoConverter {

    public static List<PostResponseDto> convertPostToPostResponseDtoList(List<Post> posts) {
        return posts.stream().map(PostResponseDto::new).collect(Collectors.toList());
    }
}
