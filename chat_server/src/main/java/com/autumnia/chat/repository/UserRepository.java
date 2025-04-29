package com.autumnia.chat.repository;

import com.autumnia.chat.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByName(String name);
    Boolean existsByName(String name);

}
