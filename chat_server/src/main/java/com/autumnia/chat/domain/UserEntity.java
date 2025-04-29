package com.autumnia.chat.domain;

import lombok.*;
import jakarta.persistence.*;
import static jakarta.persistence.FetchType.LAZY;

import java.sql.Timestamp;


@Entity @Table(name="user")
@AllArgsConstructor @NoArgsConstructor( access = AccessLevel.PROTECTED )
@Setter @Builder
public class UserEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column
    private Timestamp created_at;

    @OneToOne(mappedBy="userEntity", cascade = CascadeType.ALL)
    private UserCredentialsEntity credentials;

    public void set_user_credentials(UserCredentialsEntity userCredentials) {
        this.credentials = userCredentials;
    }
}
