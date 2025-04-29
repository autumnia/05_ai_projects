package com.autumnia.chat.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="credentials")
@AllArgsConstructor @NoArgsConstructor( access = AccessLevel.PROTECTED )
@Setter @Builder
public class UserCredentialsEntity {
    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Column(nullable = false)
    private String hashed_password;
}