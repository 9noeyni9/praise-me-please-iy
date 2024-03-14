package com.spring.nbcijo.service;

import com.spring.nbcijo.dto.request.PostListRequestDto;
import com.spring.nbcijo.dto.request.PostRequestDto;
import com.spring.nbcijo.dto.response.PostListResponseDto;
import com.spring.nbcijo.dto.response.PostResponseDto;
import com.spring.nbcijo.entity.Post;
import com.spring.nbcijo.entity.User;
import com.spring.nbcijo.global.enumeration.ErrorCode;
import com.spring.nbcijo.global.exception.InvalidInputException;
import com.spring.nbcijo.global.util.PagingUtil;
import com.spring.nbcijo.repository.PostRepository;
import com.spring.nbcijo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostResponseDto createPost(PostRequestDto requestDto, User user) {
        user = userRepository.findById(user.getId())
                .orElseThrow(() -> new InvalidInputException(ErrorCode.USER_NOT_FOUND));

        Post post = Post.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .user(user)
                .build();

        return new PostResponseDto(postRepository.save(post));
    }

    public PostResponseDto getPost(Long postId) {
        Post post = findPost(postId);

        return new PostResponseDto(post);
    }

    public PostListResponseDto getPostList(PostListRequestDto postListRequestDto) {
        if (postListRequestDto.getColumn() == null) {
            postListRequestDto.setColumn("createdDate");
        }

        PageRequest pageRequest = PageRequest.of(postListRequestDto.getPage(),
                postListRequestDto.getPageSize(), postListRequestDto.getSortDirection(),
                postListRequestDto.getColumn());
        Page<PostResponseDto> postList = postRepository.findAllPost(postListRequestDto,
                pageRequest);
        PostListResponseDto postListResponseDto = PostListResponseDto.builder().pagingUtil(
                new PagingUtil(postList.getTotalElements(), postList.getTotalPages(),
                        postList.getNumber(), postList.getSize())).postList(postList.stream().collect(
                Collectors.toList())).build();
        return postListResponseDto;
    }

    public void updatePost(Long postId, PostRequestDto requestDto, User user) {
        Post post = findPost(postId);
        validateUser(post.getUser().getId(), user.getId());

        post.update(requestDto);
    }

    public void deletePost(Long postId, User user) {
        Post post = findPost(postId);
        validateUser(post.getUser().getId(), user.getId());

        postRepository.delete(post);
    }

    private Post findPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new InvalidInputException(ErrorCode.NOT_FOUND_POST));
    }

    private void validateUser(Long writerId, Long inputId) {
        if (!Objects.equals(writerId, inputId)) {
            throw new InvalidInputException(ErrorCode.NOT_VALID_USER);
        }
    }
}
