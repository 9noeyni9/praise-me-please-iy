package com.spring.nbcijo.service;

import com.spring.nbcijo.dto.request.PostListRequestDto;
import com.spring.nbcijo.dto.request.PostRequestDto;
import com.spring.nbcijo.dto.response.PostListResponseDto;
import com.spring.nbcijo.dto.response.PostResponseDto;
import com.spring.nbcijo.entity.User;

public interface PostService {
    PostResponseDto createPost(PostRequestDto requestDto, User user);

    PostResponseDto getPost(Long postId);

    PostListResponseDto getPostList(PostListRequestDto postListRequestDto);

    void updatePost(Long postId, PostRequestDto requestDto, User user);

    void deletePost(Long postId, User user);
}
