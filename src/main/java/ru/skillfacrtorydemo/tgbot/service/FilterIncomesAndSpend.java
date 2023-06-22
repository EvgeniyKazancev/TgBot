package ru.skillfacrtorydemo.tgbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.skillfacrtorydemo.tgbot.entity.Income;
import ru.skillfacrtorydemo.tgbot.entity.Spend;
import ru.skillfacrtorydemo.tgbot.repository.IncomeRepository;
import ru.skillfacrtorydemo.tgbot.repository.SpendRepository;


import java.util.List;


@Service
@RequiredArgsConstructor
public class FilterIncomesAndSpend {
    private final IncomeRepository incomeRepository;
    private final SpendRepository spendRepository;


    public List<Income> filterIncomes(Long chatId, Long amount) {
        return incomeRepository.getAllByChatId(chatId).stream()
                .filter(income -> income.getIncome().longValue() > amount)
                .toList();
    }

    public List<Spend> filterSpends(Long chatId, Long amount) {
        return spendRepository.getSpendByChatId(chatId).stream()
                .filter(spend -> spend.getSpend().longValue() > amount)
                .toList();

    }
}
