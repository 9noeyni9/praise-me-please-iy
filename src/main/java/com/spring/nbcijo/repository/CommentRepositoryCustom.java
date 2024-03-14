package com.spring.nbcijo.repository;

import com.spring.nbcijo.dto.request.CommentListRequestDto;
import com.spring.nbcijo.dto.response.CommentResponseDto;
import com.spring.nbcijo.dto.response.MyCommentResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentRepositoryCustom {

    Page<CommentResponseDto> findAllComment(Long postId, CommentListRequestDto commentListRequestDto, Pageable pageable);

    Page<MyCommentResponseDto> findAllMyComment(Long userId, CommentListRequestDto commentListRequestDto, Pageable pageable);
}
