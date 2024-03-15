package com.spring.nbcijo.controller;

import com.spring.nbcijo.dto.request.CommentListRequestDto;
import com.spring.nbcijo.dto.request.CommentRequestDto;
import com.spring.nbcijo.dto.response.CommentListResponseDto;
import com.spring.nbcijo.global.dto.response.ResponseDto;
import com.spring.nbcijo.security.UserDetailsImpl;
import com.spring.nbcijo.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<ResponseDto<Void>> createComment(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long postId,
            @RequestBody @Valid CommentRequestDto requestDto) {
        commentService.createComment(userDetails.getUser(), postId, requestDto);
        return ResponseEntity.status(HttpStatus.OK.value())
                .body(ResponseDto.<Void>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("댓글 작성 성공")
                        .build());
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<ResponseDto<CommentListResponseDto>> getComments(
            @PathVariable Long postId, @ModelAttribute CommentListRequestDto commentListRequestDto) {
        CommentListResponseDto commentListResponseDto = commentService.getComments(postId, commentListRequestDto);
        return ResponseEntity.status(HttpStatus.OK.value())
                .body(ResponseDto.<CommentListResponseDto>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("댓글 전체 조회 성공")
                        .data(commentListResponseDto)
                        .build());
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<ResponseDto<Void>> updateComment(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestBody CommentRequestDto requestDto) {
        commentService.updateComment(userDetails.getUser(), postId, commentId, requestDto);
        return ResponseEntity.status(HttpStatus.OK.value())
                .body(ResponseDto.<Void>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("댓글 수정 성공")
                        .build());
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<ResponseDto<Void>> deleteComment(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long postId,
            @PathVariable Long commentId) {
        commentService.deleteComment(userDetails.getUser(), postId, commentId);
        return ResponseEntity.status(HttpStatus.OK.value())
                .body(ResponseDto.<Void>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("댓글 삭제 성공")
                        .build());
    }
}
