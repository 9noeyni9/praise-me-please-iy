package com.spring.nbcijo.filter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.nbcijo.common.UserFixture;
import com.spring.nbcijo.jwt.JwtUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class JwtAuthenticationFilterTest implements UserFixture {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    @DisplayName("로그인 테스트")
    void testLogin() throws Exception {
        //given
        signUp();

        //when
        var actions = mockMvc.perform(post("/user/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(TEST_LOGIN_REQUEST_DTO))
            .accept(MediaType.APPLICATION_JSON));

        actions.andExpect(header().exists(HttpHeaders.AUTHORIZATION));
    }

    private void signUp() throws Exception {
        mockMvc.perform(post("/user/signup")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(TEST_SIGN_UP_REQUEST_DTO))
            .accept(MediaType.APPLICATION_JSON));
    }
}
