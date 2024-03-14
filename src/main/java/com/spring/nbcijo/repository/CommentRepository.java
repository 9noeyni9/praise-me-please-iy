package com.spring.nbcijo.repository;

import com.spring.nbcijo.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom {

//    List<Comment> findAllByPostIdOrderByCreatedAtDesc(Long postId);
//
//    List<Comment> findAllByUserIdOrderByCreatedAtDesc(Long userId);
}
