package com.spring.nbcijo.dto.response;

import com.spring.nbcijo.entity.User;
import com.spring.nbcijo.entity.UserRoleEnum;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class MyInformResponseDto {

    private Long id;
    private String username;
    private UserRoleEnum role;
    private String description;

    @Builder
    public MyInformResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.role = user.getRole();
        this.description = user.getDescription();
    }
}
