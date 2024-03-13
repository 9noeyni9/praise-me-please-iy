package com.spring.nbcijo.common;

import com.spring.nbcijo.dto.request.PostRequestDto;
import com.spring.nbcijo.dto.response.PostResponseDto;
import com.spring.nbcijo.entity.Post;

public interface PostFixture extends UserFixture {

    Long TEST_POST_ID = 1L;
    String TEST_POST_TITLE = "title";
    String TEST_POST_CONTENT = "content";

    Post TEST_POST = Post.builder()
        .title(TEST_POST_TITLE)
        .content(TEST_POST_CONTENT)
        .user(TEST_USER)
        .build();

    Post TEST_POST2 = Post.builder()
        .title(TEST_POST_TITLE)
        .content(TEST_POST_CONTENT)
        .user(TEST_USER)
        .build();

    Post TEST_ANOTHER_POST = Post.builder()
        .title(ANOTHER_PREFIX + TEST_POST_TITLE)
        .content(ANOTHER_PREFIX + TEST_POST_CONTENT)
        .user(TEST_ANOTHER_USER)
        .build();


    PostResponseDto postResponseDto1 = new PostResponseDto(TEST_POST);
    PostResponseDto postResponseDto2 = new PostResponseDto(TEST_POST2);

    PostRequestDto TEST_POST_REQUEST_DTO = PostRequestDto.builder()
        .title(TEST_POST_TITLE)
        .content(TEST_POST_CONTENT)
        .build();

    PostResponseDto TEST_POST_RESPONSE_DTO = PostResponseDto.builder()
        .title(TEST_POST_TITLE)
        .content(TEST_POST_CONTENT)
        .build();
}
