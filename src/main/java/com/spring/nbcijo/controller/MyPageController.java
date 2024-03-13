package com.spring.nbcijo.controller;

import com.spring.nbcijo.dto.request.UpdateDescriptionRequestDto;
import com.spring.nbcijo.dto.request.UpdatePasswordRequestDto;
import com.spring.nbcijo.dto.response.CommentResponseDto;
import com.spring.nbcijo.dto.response.MyInformResponseDto;
import com.spring.nbcijo.dto.response.MyPostListResponseDto;
import com.spring.nbcijo.dto.response.PostResponseDto;
import com.spring.nbcijo.dto.response.ResponseDto;
import com.spring.nbcijo.global.dto.request.ListRequestDto;
import com.spring.nbcijo.security.UserDetailsImpl;
import com.spring.nbcijo.service.MyPageService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/my")
public class MyPageController {

    private final MyPageService myPageService;

    @GetMapping
    public ResponseEntity<ResponseDto<MyInformResponseDto>> getMyInform(@AuthenticationPrincipal
    UserDetailsImpl userDetails) {
        MyInformResponseDto myInformResponseDto = myPageService.getMyInform(userDetails.getUser());

        return ResponseEntity.status(HttpStatus.OK)
            .body(ResponseDto.<MyInformResponseDto>builder()
                .statusCode(HttpStatus.OK.value())
                .message("내 정보 조회가 완료되었습니다.")
                .data(myInformResponseDto).build());
    }

    @PutMapping
    public ResponseEntity<ResponseDto<Void>> updateMyDescription(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @Valid @RequestBody UpdateDescriptionRequestDto updateDescriptionRequestDto) {
        myPageService.updateMyDescription(userDetails.getUser(), updateDescriptionRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
            .body(ResponseDto.<Void>builder()
                .statusCode(HttpStatus.OK.value())
                .message("한 줄 소개 수정 완료")
                .build());
    }

    @PutMapping("/password")
    public ResponseEntity<ResponseDto<Void>> updateMyPassword(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @RequestBody UpdatePasswordRequestDto updatePasswordRequestDto) {
        myPageService.updateMyPassword(userDetails.getUser(), updatePasswordRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
            .body(ResponseDto.<Void>builder()
                .statusCode(HttpStatus.OK.value())
                .message("비밀 번호 변경 완료")
                .build());
    }

    @GetMapping("/posts")
    public ResponseEntity<ResponseDto<MyPostListResponseDto>> getMyPosts(
        @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam ListRequestDto listRequestDto) {
        MyPostListResponseDto myPostResponseDtos = myPageService.getMyPosts(userDetails.getUser(), listRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
            .body(ResponseDto.<MyPostListResponseDto>builder()
                .statusCode(HttpStatus.OK.value())
                .message("내 정보 조회가 완료되었습니다.")
                .data(myPostResponseDtos).build());
    }

    @GetMapping("/comments")
    public ResponseEntity<ResponseDto<List<CommentResponseDto>>> getMyComments(
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<CommentResponseDto> myCommentsResponseDtos = myPageService.getMyComments(
            userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK.value())
            .body(ResponseDto.<List<CommentResponseDto>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("내 정보 조회가 완료되었습니다.")
                .data(myCommentsResponseDtos).build());
    }
}
