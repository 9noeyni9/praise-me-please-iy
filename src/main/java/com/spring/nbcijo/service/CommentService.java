package com.spring.nbcijo.service;

import com.spring.nbcijo.dto.request.CommentListRequestDto;
import com.spring.nbcijo.dto.request.CommentRequestDto;
import com.spring.nbcijo.dto.response.CommentListResponseDto;
import com.spring.nbcijo.dto.response.CommentResponseDto;
import com.spring.nbcijo.entity.User;

import java.util.List;

public interface CommentService {

    void createComment(User user, Long postId, CommentRequestDto requestDto);

    CommentListResponseDto getComments(Long postId, CommentListRequestDto commentListRequestDto);

    void updateComment(User user, Long postId, Long commentId,
                              CommentRequestDto requestDto);

    void deleteComment(User user, Long postId, Long commentId);
}
