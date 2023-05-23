package ru.skillfacrtorydemo.tgbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skillfacrtorydemo.tgbot.entity.Spend;

import java.util.List;

@Repository
public interface SpendRepository extends JpaRepository<Spend,Long> {
    List<Spend> getSpendByChatId(Long chatId);
}
