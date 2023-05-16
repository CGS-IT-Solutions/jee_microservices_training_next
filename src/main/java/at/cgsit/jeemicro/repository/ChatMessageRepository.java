package at.cgsit.jeemicro.repository;


import at.cgsit.jeemicro.entity.ChatMessageEntity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;

@ApplicationScoped
public class ChatMessageRepository {

    @Inject
    EntityManager em;

    public ChatMessageEntity readChatMessage(Long id) {
        // direkte Verwendung der find methode des Entity Managers für eine ID
        ChatMessageEntity chatMessageEntity = em.find(ChatMessageEntity.class, id);
        return chatMessageEntity;
    }


    @Transactional
    public String createChatMessageDBAndReturnCount(String echoIn) {

        ChatMessageEntity entity = new ChatMessageEntity();
        entity.setChatMessage(echoIn);
        entity.setChatRoom("room1");
        entity.setUserName("username");
        entity.setCreationTime(LocalDateTime.now());

        em.persist(entity);

        Query query = em.createQuery("select count(e) from ChatMessageEntity e");
        Long singleResult = (Long) query.getSingleResult();
        return "count is: " + singleResult.toString();
    }


    @Transactional(Transactional.TxType.REQUIRED)
    public void insertChatMessage(ChatMessageEntity newObject) {
        em.persist(newObject);
    }


}
