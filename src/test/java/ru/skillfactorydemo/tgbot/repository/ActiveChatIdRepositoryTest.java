package ru.skillfactorydemo.tgbot.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.skillfacrtorydemo.tgbot.entity.ActiveChat;
import ru.skillfacrtorydemo.tgbot.repository.ActiveChatRepository;

import javax.annotation.Resource;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ActiveChatIdRepositoryTest {
    @Resource
    private ActiveChatRepository activeChatRepository;

    @Test
    private void testRepo_found(){
        // создаЄм экземпл€р
        final ActiveChat activeChat = new ActiveChat();
        // заполн€ем атрибут каким-нибудь значением
        activeChat.setChatId(12345L);
        // сохран€ем объект в базу
        activeChatRepository.save(activeChat);
        // ищем объект кастомным методом по chatId
        Optional<ActiveChat> activeChatByChatId = activeChatRepository.findActiveChatByChatId(12345L);
        // провер€ем, что объект найден
        assertTrue(activeChatByChatId.isPresent());
        // и что он имеет нужное значение
        assertEquals(12345L, activeChatByChatId.get().getChatId());
    }
    @Test
    public void testRepo_notFound() {
        final ActiveChat activeChat = new ActiveChat();
        activeChat.setChatId(12345L);
        activeChatRepository.save(activeChat);
        Optional<ActiveChat> activeChatByChatId = activeChatRepository.findActiveChatByChatId(54321L);
        // мы искали несуществующий объект, поэтому провер€ем, что он не нашЄлс€
        assertFalse(activeChatByChatId.isPresent());
    }
}
