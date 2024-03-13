package com.spring.nbcijo.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.spring.nbcijo.common.UserFixture;
import com.spring.nbcijo.jwt.JwtUtil;
import com.spring.nbcijo.repository.PasswordHistoryRepository;
import com.spring.nbcijo.repository.RefreshTokenBlacklistRepository;
import com.spring.nbcijo.repository.UserRepository;
import com.spring.nbcijo.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceIntegrationTest implements UserFixture {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RefreshTokenBlacklistRepository refreshTokenBlacklistRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PasswordHistoryRepository passwordHistoryRepository;

    @DisplayName("회원가입 성공")
    @Test
    void singUp() {
        //given
        var requestDto = TEST_ANOTHER_SIGN_UP_REQUEST_DTO;

        //when
        userService.signup(requestDto);

        //then
        var found = userRepository.findByUsername(requestDto.getUsername());
        var foundId = found.get().getId();
        var passwordHistory = passwordHistoryRepository.findTop3ByUserIdOrderByCreatedAtDesc(
            foundId);

        assertThat(found).isNotEmpty();
        assertThat(found.get().getUsername()).isEqualTo(requestDto.getUsername());
        assertThat(
            passwordEncoder.matches(requestDto.getPassword(), passwordHistory.get(0).getPassword()
            )).isTrue();
    }

    @DisplayName("로그아웃")
    @Test
    void logout() {
        //given
        String token = createRefreshToken();

        //when
        userService.logout(token);

        //then
        boolean isExist = refreshTokenBlacklistRepository.existsByRefreshToken(token);
        assertThat(isExist).isTrue();
    }

    private String createRefreshToken() {
        return jwtUtil.createRefreshToken(TEST_USER_NAME, TEST_USER_ROLE);
    }
}
