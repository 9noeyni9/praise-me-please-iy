package com.spring.nbcijo.entity;

import com.spring.nbcijo.dto.request.UpdateDescriptionRequestDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends Timestamped implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @Column
    private String description;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PasswordHistory> passwordHistories = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Praise> praises = new ArrayList<>();

    @Builder
    public User(String username, String password, UserRoleEnum role, String description) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.description = description;
    }

    public void updateDescription(User user,
        UpdateDescriptionRequestDto updateDescriptionRequestDto) {
        this.username = user.username;
        this.password = user.password;
        this.role = user.role;
        this.description = updateDescriptionRequestDto.getUpdateDescription();
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
