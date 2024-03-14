package com.spring.nbcijo.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.nbcijo.dto.request.CommentListRequestDto;
import com.spring.nbcijo.dto.response.CommentResponseDto;
import com.spring.nbcijo.dto.response.MyCommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.spring.nbcijo.entity.QComment.comment;

@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<CommentResponseDto> findAllComment(Long postId, CommentListRequestDto commentListRequestDto, Pageable pageable) {
        List<CommentResponseDto> list = jpaQueryFactory
                .select(Projections.constructor(CommentResponseDto.class,
                        comment.id,
                        comment.content,
                        comment.createdAt))
                .from(comment)
                .where(comment.post.id.eq(postId))
                .orderBy(comment.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = jpaQueryFactory
                .select(comment.count())
                .from(comment)
                .fetchOne();
        return new PageImpl<>(list, pageable, count);
    }

    @Override
    public Page<MyCommentResponseDto> findAllMyComment(Long userId, CommentListRequestDto commentListRequestDto, Pageable pageable) {
        List<MyCommentResponseDto> list = jpaQueryFactory
                .select(Projections.constructor(MyCommentResponseDto.class,
                        comment.user.username,
                        comment.post.id,
                        comment.content,
                        comment.createdAt))
                .from(comment)
                .where(comment.user.id.eq(userId))
                .orderBy(comment.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = jpaQueryFactory
                .select(comment.count())
                .from(comment)
                .where(comment.user.id.eq(userId))
                .fetchOne();
        return new PageImpl<>(list, pageable, count);
    }
}
