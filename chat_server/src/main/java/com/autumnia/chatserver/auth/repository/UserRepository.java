package com.autumnia.chatserver.auth.repository;

import com.autumnia.chatserver.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByName(String name);
    Boolean existsByName(String name);

}
