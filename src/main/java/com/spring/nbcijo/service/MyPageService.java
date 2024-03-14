package com.spring.nbcijo.service;

import com.spring.nbcijo.dto.request.CommentListRequestDto;
import com.spring.nbcijo.dto.request.PostListRequestDto;
import com.spring.nbcijo.dto.request.UpdateDescriptionRequestDto;
import com.spring.nbcijo.dto.request.UpdatePasswordRequestDto;
import com.spring.nbcijo.dto.response.*;
import com.spring.nbcijo.entity.User;

public interface MyPageService {

    MyInformResponseDto getMyInform(User user);

    void updateMyDescription(User user,
        UpdateDescriptionRequestDto updateDescriptionRequestDto);

    void updateMyPassword(User user, UpdatePasswordRequestDto updatePasswordRequestDto);

    MyPostListResponseDto getMyPosts(User user, PostListRequestDto postListRequestDto);

    MyCommentListResponseDto getMyComments(User user, CommentListRequestDto commentListRequestDto);
}
