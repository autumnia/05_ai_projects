package com.autumnia.chatserver.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.autumnia.chatserver.chat.domain.entity.ChatEntity;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<ChatEntity, Long> {
    List<ChatEntity> fingTop10BySenderOrReceiverOrderByIdDesc(String sender, String receiver);

//    @Query("SELECT c FROM char AS c WHERE c.sender = :sender or c.receiver :receiver ORDER BY c.id DESC LIMIT 3")
//    List<ChatEntity>  findTop100Chart(@Param("sender") String sender, @Param("receiver") String receiver);



}
