package com.spring.nbcijo.service;

import com.spring.nbcijo.entity.Post;
import com.spring.nbcijo.entity.Praise;
import com.spring.nbcijo.entity.User;
import com.spring.nbcijo.global.enumeration.ErrorCode;
import com.spring.nbcijo.global.exception.InvalidInputException;
import com.spring.nbcijo.repository.PostRepository;
import com.spring.nbcijo.repository.PraiseRepository;
import com.spring.nbcijo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PraiseService {

    private final PraiseRepository praiseRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public void praisePost(User user, Post post) {
        user = userRepository.findById(user.getId()).orElseThrow(() -> new InvalidInputException(
            ErrorCode.USER_NOT_FOUND));
        post = postRepository.findById(post.getId())
            .orElseThrow(() -> new InvalidInputException(ErrorCode.NOT_FOUND_POST));

        if (praiseRepository.findByUserAndPost(user, post).isPresent()) {
            throw new InvalidInputException(ErrorCode.ALREADY_EXISTS);
        }
        Praise praise = Praise.builder()
            .user(user)
            .post(post)
            .build();

        praiseRepository.save(praise);
    }

    public void cancelToPraise(User user, Post post) {
        user = userRepository.findById(user.getId()).orElseThrow(() -> new InvalidInputException(
            ErrorCode.USER_NOT_FOUND));
        post = postRepository.findById(post.getId())
            .orElseThrow(() -> new InvalidInputException(ErrorCode.NOT_FOUND_POST));

        if (praiseRepository.findByUserAndPost(user, post).isEmpty()) {
            throw new InvalidInputException(ErrorCode.NOT_EXISTS);
        }
        praiseRepository.deleteByUserAndPost(user, post);
    }
}
