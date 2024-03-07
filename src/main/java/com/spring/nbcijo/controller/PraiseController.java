package com.spring.nbcijo.controller;

import com.spring.nbcijo.dto.response.ResponseDto;
import com.spring.nbcijo.entity.Post;
import com.spring.nbcijo.security.UserDetailsImpl;
import com.spring.nbcijo.service.PraiseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/praise")
public class PraiseController {

    private final PraiseService praiseService;

    @PostMapping("/posts/{postId}")
    public ResponseEntity<ResponseDto<Void>> praisePost(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        Post post, @PathVariable Long postId) {
        praiseService.praisePost(userDetails.getUser(), post);
        return ResponseEntity.status(HttpStatus.OK)
            .body(ResponseDto.<Void>builder()
                .statusCode(HttpStatus.OK.value())
                .message("칭찬합니다 추가 성공")
                .build());
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ResponseDto<Void>> cancelToPraise(
        @AuthenticationPrincipal UserDetailsImpl userDetails, Post post,
        @PathVariable Long postId) {
        praiseService.cancelToPraise(userDetails.getUser(), post);
        return ResponseEntity.status(HttpStatus.OK)
            .body(ResponseDto.<Void>builder()
                .statusCode(HttpStatus.OK.value())
                .message("칭찬합니다 취소 성공")
                .build());
    }
}
