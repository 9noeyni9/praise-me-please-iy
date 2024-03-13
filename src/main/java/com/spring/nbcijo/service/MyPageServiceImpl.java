package com.spring.nbcijo.service;

import com.spring.nbcijo.dto.request.PostListRequestDto;
import com.spring.nbcijo.dto.request.UpdateDescriptionRequestDto;
import com.spring.nbcijo.dto.request.UpdatePasswordRequestDto;
import com.spring.nbcijo.dto.response.CommentResponseDto;
import com.spring.nbcijo.dto.response.MyInformResponseDto;
import com.spring.nbcijo.dto.response.MyPostListResponseDto;
import com.spring.nbcijo.dto.response.MyPostResponseDto;
import com.spring.nbcijo.entity.Comment;
import com.spring.nbcijo.entity.PasswordHistory;
import com.spring.nbcijo.entity.User;
import com.spring.nbcijo.global.enumeration.ErrorCode;
import com.spring.nbcijo.global.exception.InvalidInputException;
import com.spring.nbcijo.global.util.PagingUtil;
import com.spring.nbcijo.repository.CommentRepository;
import com.spring.nbcijo.repository.PasswordHistoryRepository;
import com.spring.nbcijo.repository.PostRepository;
import com.spring.nbcijo.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MyPageServiceImpl implements MyPageService{

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PasswordEncoder passwordEncoder;
    private final PasswordHistoryRepository passwordHistoryRepository;

    @Override
    public MyInformResponseDto getMyInform(User user) {
        user = userRepository.findById(user.getId())
            .orElseThrow(() -> new InvalidInputException(ErrorCode.USER_NOT_FOUND));
        return MyInformResponseDto.builder()
            .user(user).build();
    }

    @Override
    public void updateMyDescription(User user,
        UpdateDescriptionRequestDto updateDescriptionRequestDto) {
        user = userRepository.findById(user.getId())
            .orElseThrow(() -> new InvalidInputException(ErrorCode.USER_NOT_FOUND));
        if (isPasswordMatches(user.getPassword(), updateDescriptionRequestDto.getPassword())) {
            user.updateDescription(user, updateDescriptionRequestDto);
        } else {
            throw new InvalidInputException(ErrorCode.INVALID_PASSWORD);
        }
    }

    @Override
    public void updateMyPassword(User user, UpdatePasswordRequestDto updatePasswordRequestDto) {
        String encryptNewPassword = passwordEncoder.encode(
            updatePasswordRequestDto.getNewPassword());
        PasswordHistory passwordHistory = new PasswordHistory();

        user = userRepository.findById(user.getId())
            .orElseThrow(() -> new InvalidInputException(ErrorCode.USER_NOT_FOUND));

        // 비밀 번호 사용자 입력값과 DB의 저장된 값 비교
        if (!isPasswordMatches(user.getPassword(), updatePasswordRequestDto.getCurrentPassword())) {
            throw new InvalidInputException(ErrorCode.INVALID_PASSWORD);
        }

        // 최근 3번 안에 사용된 비밀번호 재사용 제한
        if (!isPasswordPreviouslyUsed(user, updatePasswordRequestDto)) {
            throw new InvalidInputException(ErrorCode.REUSED_PASSWORD);
        }

        // 수정한 비밀번호 User와 PasswordHistory에 저장
        user.updatePassword(encryptNewPassword);
        PasswordHistory newPasswordHistory = passwordHistory.toPasswordHistory(user,
            encryptNewPassword);
        passwordHistoryRepository.save(newPasswordHistory);
    }

    @Override
    public MyPostListResponseDto getMyPosts(User user, PostListRequestDto postListRequestDto) {
        if (postListRequestDto.getColumn() == null) {
            postListRequestDto.setColumn("createdDate");
        }

        PageRequest pageRequest = PageRequest.of(postListRequestDto.getPage(),
            postListRequestDto.getPageSize(), postListRequestDto.getSortDirection(),
            postListRequestDto.getColumn());
        Page<MyPostResponseDto> myPostList = postRepository.findAllMyPost(user.getId(),
            postListRequestDto, pageRequest);
        return MyPostListResponseDto.builder()
            .pagingUtil(new PagingUtil(myPostList.getTotalElements(), myPostList.getTotalPages(),
                myPostList.getNumber(), myPostList.getSize()))
            .myPostList(myPostList.stream().collect(Collectors.toList()))
            .build();
    }

    @Override
    public List<CommentResponseDto> getMyComments(User user) {
        List<Comment> list = commentRepository.findAllByUserIdOrderByCreatedAtDesc(user.getId());
        return list.stream().map(CommentResponseDto::new).collect(
            Collectors.toList());
    }

    private boolean isPasswordMatches(String passwordInDB, String inputPassword) {
        return passwordEncoder.matches(inputPassword, passwordInDB);
    }

    private boolean isPasswordPreviouslyUsed(User user,
        UpdatePasswordRequestDto updatePasswordRequestDto) {
        List<PasswordHistory> passwordList = passwordHistoryRepository.findTop3ByUserIdOrderByCreatedAtDesc(
            user.getId());
        for (PasswordHistory password : passwordList) {
            if (isPasswordMatches(password.getPassword(),
                updatePasswordRequestDto.getNewPassword())) {
                return false;
            }
        }
        return true;
    }

}
