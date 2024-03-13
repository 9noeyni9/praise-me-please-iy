package com.spring.nbcijo.post;

import com.spring.nbcijo.entity.Post;
import com.spring.nbcijo.entity.User;
import java.time.LocalDateTime;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.SerializationUtils;

public class PostTestUtils {

    public static Post get(Post post, User user) {
        return get(post, 1L, LocalDateTime.now(), user);
    }

    public static Post get(
        Post post,
        Long id,
        LocalDateTime createdAt,
        User user
    ) {
        var newPost = SerializationUtils.clone(post);
        ReflectionTestUtils.setField(newPost, Post.class, "id", id, Long.class);
        ReflectionTestUtils.setField(newPost, Post.class, "createdAt", createdAt,
            LocalDateTime.class);
        ReflectionTestUtils.setField(newPost, "user", user, User.class);
        return newPost;

    }

}
