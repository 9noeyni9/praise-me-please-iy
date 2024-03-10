package com.spring.nbcijo.service;

import static java.util.stream.Collectors.toList;

import com.spring.nbcijo.dto.request.PostRequestDto;
import com.spring.nbcijo.dto.response.PostListResponseDto;
import com.spring.nbcijo.dto.response.PostResponseDto;
import com.spring.nbcijo.dto.response.PostResponseDtoConverter;
import com.spring.nbcijo.entity.Post;
import com.spring.nbcijo.entity.User;
import com.spring.nbcijo.global.dto.request.ListRequestDto;
import com.spring.nbcijo.global.enumeration.ErrorCode;
import com.spring.nbcijo.global.exception.InvalidInputException;
import com.spring.nbcijo.global.util.PagingUtil;
import com.spring.nbcijo.repository.PostRepository;
import com.spring.nbcijo.repository.UserRepository;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

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

    public PostListResponseDto getPostList(ListRequestDto listRequestDto) {
        if (listRequestDto.getColumn() == null) {
            listRequestDto.setColumn("createdDate");
        }

        PageRequest pageRequest = PageRequest.of(listRequestDto.getPage(),
            listRequestDto.getPageSize(), listRequestDto.getSortDirection(),
            listRequestDto.getColumn());
        Page<Post> postList = postRepository.findAll(pageRequest);
        List<PostResponseDto> postResponseDtoList = PostResponseDtoConverter.convertPostToPostResponseDtoList(postList.getContent());

        return PostListResponseDto.builder()
            .pagingUtil(new PagingUtil(postList.getTotalElements(), postList.getTotalPages(),
                postList.getNumber(), postList.getSize()))
            .postResponseDtoList(postResponseDtoList)
            .build();
    }

    @Transactional
    public void updatePost(Long postId, PostRequestDto requestDto, User user) {
        Post post = findPost(postId);
        validateUser(post.getUser().getId(), user.getId());

        post.update(requestDto);
    }

    @Transactional
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
