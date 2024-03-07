package com.spring.nbcijo.repository;

import com.spring.nbcijo.entity.Post;
import com.spring.nbcijo.entity.Praise;
import com.spring.nbcijo.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PraiseRepository extends JpaRepository<Praise,Long> {

    Optional<Praise> findByUserAndPost(User user, Post post);

    void deleteByUserAndPost(User user, Post post);
}
