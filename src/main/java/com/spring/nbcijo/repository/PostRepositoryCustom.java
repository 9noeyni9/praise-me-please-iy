package com.spring.nbcijo.repository;

import com.spring.nbcijo.dto.request.PostListRequestDto;
import com.spring.nbcijo.dto.response.MyPostResponseDto;
import com.spring.nbcijo.dto.response.PostResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {

    Page<PostResponseDto> findAllPost(PostListRequestDto postListRequestDto, Pageable pageable);

    Page<MyPostResponseDto> findAllMyPost(Long userId, PostListRequestDto postListRequestDto,
        Pageable pageable);
}
