package com.spring.nbcijo.service;

import com.spring.nbcijo.dto.request.CommentRequestDto;
import com.spring.nbcijo.dto.response.CommentResponseDto;
import com.spring.nbcijo.entity.Comment;
import com.spring.nbcijo.entity.Post;
import com.spring.nbcijo.entity.User;
import com.spring.nbcijo.global.enumeration.ErrorCode;
import com.spring.nbcijo.global.exception.InvalidInputException;
import com.spring.nbcijo.repository.CommentRepository;
import com.spring.nbcijo.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Override
    public void createComment(User user, Long postId, CommentRequestDto requestDto) {
        Post post = findPost(postId);

        Comment comment = Comment.builder()
                .post(post)
                .user(user)
                .content(requestDto.getContent())
                .build();
        commentRepository.save(comment);
    }

    @Override
    public List<CommentResponseDto> getComments(Long postId) {
        findPost(postId);
        List<Comment> comments = commentRepository.findAllByPostIdOrderByCreatedAtDesc(postId);
        return comments.stream()
                .map(CommentResponseDto::new)
                .toList();
    }

    @Override
    public void updateComment(User user, Long postId, Long commentId,
                              CommentRequestDto requestDto) {
        findPost(postId);
        Comment comment = findComment(commentId);

        validateUser(comment.getUser().getId(), user.getId());
        validatePost(comment.getPost().getId(), postId);
        comment.update(requestDto);
    }

    @Override
    public void deleteComment(User user, Long postId, Long commentId) {
        findPost(postId);
        Comment comment = findComment(commentId);

        validateUser(comment.getUser().getId(), user.getId());
        validatePost(comment.getPost().getId(), postId);

        commentRepository.delete(comment);
    }

    private Post findPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(
                () -> new InvalidInputException(ErrorCode.NOT_FOUND_POST)
        );
    }

    private Comment findComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(
                () -> new InvalidInputException(ErrorCode.NOT_VALID_USER)
        );
    }

    private void validateUser(Long writerId, Long inputId) {
        if (!Objects.equals(writerId, inputId)) {
            throw new InvalidInputException(ErrorCode.NOT_VALID_USER);
        }
    }

    private void validatePost(Long postId, Long inputId) {
        if (!Objects.equals(postId, inputId)) {
            throw new InvalidInputException(ErrorCode.NOT_VALID_POST);
        }
    }
}
