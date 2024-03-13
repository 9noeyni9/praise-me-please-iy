package com.spring.nbcijo.repository;

import static com.spring.nbcijo.entity.QPost.post;
import static org.apache.logging.log4j.util.Strings.isEmpty;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.nbcijo.dto.request.PostListRequestDto;
import com.spring.nbcijo.dto.response.MyPostResponseDto;
import com.spring.nbcijo.dto.response.PostResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<PostResponseDto> findAllPost(PostListRequestDto postListRequestDto,
        Pageable pageable) {
        List<PostResponseDto> list = jpaQueryFactory
            .select(Projections.constructor(PostResponseDto.class,
                post.title,
                post.content,
                post.user.username,
                post.createdAt))
            .from(post)
            .where(postTitleEq(postListRequestDto.getTitle()),
                postContentEq(postListRequestDto.getContent()))
            .orderBy(post.createdAt.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long count = jpaQueryFactory
            .select(post.count())
            .from(post)
            .fetchOne();
        return new PageImpl<>(list, pageable, count);
    }

    @Override
    public Page<MyPostResponseDto> findAllMyPost(Long userId, PostListRequestDto postListRequestDto,
        Pageable pageable) {
        List<MyPostResponseDto> list = jpaQueryFactory
            .select(Projections.constructor(MyPostResponseDto.class,
                post.title,
                post.content,
                post.createdAt,
                post.user.username))
            .from(post)
            .where(post.user.id.eq(userId),
                postTitleEq(postListRequestDto.getTitle()),
                postContentEq(postListRequestDto.getContent()))
            .orderBy(post.createdAt.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long count = jpaQueryFactory
            .select(post.count())
            .from(post)
            .where(post.user.id.eq(userId))
            .fetchOne();
        return new PageImpl<>(list, pageable, count);
    }

    private BooleanExpression postTitleEq(String title) {
        return isEmpty(title) ? null : post.title.contains(title);
    }

    private BooleanExpression postContentEq(String content) {
        return isEmpty(content) ? null : post.content.contains(content);
    }
}
