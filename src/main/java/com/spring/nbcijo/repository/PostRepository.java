package com.spring.nbcijo.repository;

import com.spring.nbcijo.entity.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {

    List<Post> findAllByUserIdOrderByCreatedAtDesc(Long userId);
}
