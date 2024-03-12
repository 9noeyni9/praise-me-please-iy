package com.spring.nbcijo.repository;

import com.spring.nbcijo.dto.response.MyPostResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {

    Page<MyPostResponseDto> findAllMyPost(Long userId, Pageable pageable);
}
