package com.spring.nbcijo.post;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.times;
import static org.mockito.BDDMockito.verify;

import com.spring.nbcijo.common.PostFixture;
import com.spring.nbcijo.dto.request.PostRequestDto;
import com.spring.nbcijo.dto.response.PostResponseDto;
import com.spring.nbcijo.entity.Post;
import com.spring.nbcijo.entity.User;
import com.spring.nbcijo.repository.PostRepository;
import com.spring.nbcijo.repository.UserRepository;
import com.spring.nbcijo.service.PostService;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class PostServiceTest implements PostFixture {

    @InjectMocks
    PostService postService;

    @Mock
    PostRepository postRepository;

    @Mock
    UserRepository userRepository;

    @DisplayName("게시글 생성")
    @Test
    void createPost() {
        // given
        given(userRepository.findById(any())).willReturn(Optional.of(TEST_USER));
        given(postRepository.save(any(Post.class))).willReturn(TEST_POST);

        // when & then
        assertDoesNotThrow(() -> postService
            .createPost(TEST_POST_REQUEST_DTO, TEST_USER));
        verify(postRepository, times(1)).save(any(Post.class));
    }

    @DisplayName("게시글 조회")
    @Test
    void getPost() {
        // given
        var testPost = PostTestUtils.get(TEST_POST, TEST_USER);
        given(postRepository.findById(TEST_POST_ID)).willReturn(Optional.of(testPost));

        // when
        var result = postService.getPost(TEST_POST_ID);

        // then
        assertThat(result).isEqualTo(new PostResponseDto(testPost));
    }

//    @DisplayName("게시글 전체 조회")
//    @Test
//    void getPostList() {
//        // given
//        var testPost1 = PostTestUtils.get(TEST_POST, 1L,
//            LocalDateTime.now(), TEST_USER);
//        var testPost2 = PostTestUtils.get(TEST_POST, 2L,
//            LocalDateTime.now().minusMinutes(1), TEST_USER);
//        given(postRepository.findAll(Sort.by(Direction.DESC, "createdAt")))
//            .willReturn(List.of(testPost1, testPost2));
//
//        // when
//        var result = postService.getPostList();
//
//        // then
//        assertThat(result).hasSize(2);
//        assertThat(result.get(0)).isEqualTo(new PostResponseDto(testPost1));
//        assertThat(result.get(1)).isEqualTo(new PostResponseDto(testPost2));
//    }

    @DisplayName("게시글 수정")
    @Test
    void updatePost() {
        // given
        ReflectionTestUtils.setField(TEST_USER, User.class, "id", TEST_USER_ID, Long.class);
        var testPost = PostTestUtils.get(TEST_POST, TEST_USER);
        given(postRepository.findById(eq(TEST_POST_ID))).willReturn(Optional.of(testPost));

        // when
        var request = new PostRequestDto("updateTitle", "updateContent");

        // then
        assertDoesNotThrow(() ->
            postService.updatePost(TEST_POST_ID, request, TEST_USER));
        assertThat(testPost.getTitle()).isEqualTo("updateTitle");
        assertThat(testPost.getContent()).isEqualTo("updateContent");
    }

    @DisplayName("게시글 삭제")
    @Test
    void deletePost() {
        // given
        ReflectionTestUtils.setField(TEST_USER, User.class, "id", TEST_USER_ID, Long.class);
        var testPost = TEST_POST;
        given(postRepository.findById(TEST_POST_ID)).willReturn(Optional.of(testPost));

        // when & given
        assertDoesNotThrow(() ->
            postService.deletePost(TEST_POST_ID, TEST_USER));
    }
}
