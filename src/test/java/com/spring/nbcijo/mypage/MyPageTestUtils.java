package com.spring.nbcijo.mypage;

import com.spring.nbcijo.entity.PasswordHistory;
import com.spring.nbcijo.entity.User;
import java.time.LocalDateTime;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.SerializationUtils;

public class MyPageTestUtils {

    public static PasswordHistory get(PasswordHistory passwordHistory, User user) {
        return get(passwordHistory, 1L, LocalDateTime.now(), user);
    }

    public static PasswordHistory get(PasswordHistory passwordHistory, Long id,
        LocalDateTime createdAt, User user) {
        var newPasswordHistory = SerializationUtils.clone(passwordHistory);
        ReflectionTestUtils.setField(newPasswordHistory, "id", id, Long.class);
        ReflectionTestUtils.setField(newPasswordHistory, "createdAt", createdAt,
            LocalDateTime.class);
        ReflectionTestUtils.setField(newPasswordHistory, "user", user, User.class);
        return newPasswordHistory;
    }

    public static User get(User user) {
        return get(user, 1L, LocalDateTime.now());
    }

    public static User get(User user, long id, LocalDateTime createdAt) {
        var newUser = SerializationUtils.clone(user);
        ReflectionTestUtils.setField(newUser, "id", id, Long.class);
        ReflectionTestUtils.setField(newUser, "createdAt", createdAt, LocalDateTime.class);
        return newUser;
    }

}
