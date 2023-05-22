package ru.skillfacrtorydemo.tgbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skillfacrtorydemo.tgbot.entity.Income;

import java.util.ArrayList;
import java.util.List;

@Repository

public interface IncomeRepository extends JpaRepository<Income,Long> {
    List<Income> getAllByChatId(Long chatId);

    List<Income> findByChatIdOrderByIncomeAsc(Long chatId);

}
