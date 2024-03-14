package com.spring.nbcijo.service;

import com.spring.nbcijo.dto.request.PostListRequestDto;
import com.spring.nbcijo.dto.request.PostRequestDto;
import com.spring.nbcijo.dto.response.PostListResponseDto;
import com.spring.nbcijo.dto.response.PostResponseDto;
import com.spring.nbcijo.entity.User;

public interface PostService {

    public PostResponseDto createPost(PostRequestDto requestDto, User user);

    public PostResponseDto getPost(Long postId);

    public PostListResponseDto getPostList(PostListRequestDto postListRequestDto);

    public void updatePost(Long postId, PostRequestDto requestDto, User user);

    public void deletePost(Long postId, User user);
}
