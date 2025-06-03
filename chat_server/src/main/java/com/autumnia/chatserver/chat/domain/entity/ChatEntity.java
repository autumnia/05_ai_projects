package com.autumnia.chatserver.chat.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;

@Slf4j
@Entity
@Table(name="chat")
@Getter @Builder
@AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED )
public class ChatEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String sender;

    @Column
    private String receiver;

    @Column
    private String message;

    @Column
    private Timestamp created_at;
}
