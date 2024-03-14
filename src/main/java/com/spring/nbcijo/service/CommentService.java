package com.spring.nbcijo.service;

import com.spring.nbcijo.dto.request.CommentRequestDto;
import com.spring.nbcijo.dto.response.CommentResponseDto;
import com.spring.nbcijo.entity.User;

import java.util.List;

public interface CommentService {

    public void createComment(User user, Long postId, CommentRequestDto requestDto);

    public List<CommentResponseDto> getComments(Long postId);

    public void updateComment(User user, Long postId, Long commentId,
                              CommentRequestDto requestDto);

    public void deleteComment(User user, Long postId, Long commentId);
}
