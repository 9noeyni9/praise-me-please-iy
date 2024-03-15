package com.spring.nbcijo.mypage;

import static org.hamcrest.Matchers.hasItems;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.spring.nbcijo.common.CommentFixture;
import com.spring.nbcijo.common.ControllerTest;
import com.spring.nbcijo.common.PostFixture;
import com.spring.nbcijo.common.UserFixture;
import com.spring.nbcijo.controller.MyPageController;
import com.spring.nbcijo.service.MyPageService;
import java.security.Principal;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

@WebMvcTest(MyPageController.class)
class MyPageControllerTest extends ControllerTest implements UserFixture, PostFixture,
    CommentFixture {

    @MockBean
    MyPageService myPageService;
    private Principal mockPrincipal;

    @Test
    @DisplayName("내 정보 조회 성공")
    void getMyInform_success() throws Exception {
        // given
        given(myPageService.getMyInform(eq(TEST_USER))).willReturn(TEST_USER_RESPONSE);

        // when
        var action = mockMvc.perform(get("/my")
            .accept(MediaType.APPLICATION_JSON));

        // then
        action.andExpect(status().isOk())
            .andExpect(jsonPath("$.data.username").value(TEST_USER_NAME))
            .andExpect(jsonPath("$.data.description").value(TEST_USER_DESCRIPTION))
            .andDo(print());
    }

    @Test
    @DisplayName("한 줄 소개 수정 성공")
    void updateMyDescription_success() throws Exception {

        // when
        var action = mockMvc.perform(put("/my")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(TEST_DESCRIPTION_UPDATE_REQUEST)));

        // then
        action.andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @DisplayName("비밀번호 수정 성공")
    void updateMyPassword_success() throws Exception {

        // when
        var action = mockMvc.perform(put("/my/password")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(TEST_PASSWORD_UPDATE_REQUEST)));

        // then
        action.andExpect(status().isOk())
            .andDo(print());
    }

//    @Test
//    @DisplayName("내가 쓴 글 목록 조회 성공")
//    void getMyPosts_success() throws Exception {
//        //given
//        given(myPageService.getMyPosts(eq(TEST_USER))).willReturn(
//            List.of(postResponseDto1, postResponseDto2));
//        // when
//        var action = mockMvc.perform(get("/my/posts")
//            .accept(MediaType.APPLICATION_JSON));
//
//        // then
//        action.andExpect(status().isOk())
//            .andExpect(
//                jsonPath("$.data[*].content",hasItems(TEST_POST_CONTENT)))
//            .andDo(print());
//    }

//    @Test
//    @DisplayName("내가 쓴 댓글 목록 조회 성공")
//    void getMyPosts_fail() throws Exception {
//        //given
//        given(myPageService.getMyComments(eq(TEST_USER))).willReturn(
//            List.of(commentResponseDto1, commentResponseDto2));
//        // when
//        var action = mockMvc.perform(get("/my/comments")
//            .accept(MediaType.APPLICATION_JSON));
//
//        // then
//        action.andExpect(status().isOk())
//            .andExpect(
//                jsonPath("$.data[*].content",
//                    hasItems(TEST_COMMENT_CONTENT, TEST_ANOTHER_COMMENT.getContent())))
//            .andDo(print());
//    }

}
