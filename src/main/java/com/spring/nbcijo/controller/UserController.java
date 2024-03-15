package com.spring.nbcijo.controller;

import com.spring.nbcijo.dto.request.SignupRequestDto;
import com.spring.nbcijo.global.dto.response.ResponseDto;
import com.spring.nbcijo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/login-page")
    public String loginPage() {
        return "index";
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto<String>> signup(
        @RequestBody @Valid SignupRequestDto requestDto) {

        // 비밀번호 일치 확인
        if (!requestDto.getPassword().equals(requestDto.getPasswordConfirm())) {
            throw new IllegalArgumentException("회원가입 실패 : 비밀번호가 일치하지 않습니다.");
        }
        // 회원가입
        userService.signup(requestDto);

        ResponseDto<String> responseDto = ResponseDto.<String>builder()
            .statusCode(HttpStatus.OK.value())
            .message("회원가입이 완료되었습니다.")
            .build();

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<ResponseDto<String>> logout(
        @CookieValue(value = "refreshToken", defaultValue = "") String refreshToken) {
        if (refreshToken.isEmpty()) {
            throw new IllegalArgumentException("로그아웃 실패 : Refresh Token을 찾을 수 없습니다.");
        }

        userService.logout(refreshToken);

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.<String>builder()
            .statusCode(HttpStatus.OK.value())
            .message("로그아웃이 완료되었습니다.")
            .build());
    }
}
