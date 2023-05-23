package ru.skillfacrtorydemo.tgbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skillfacrtorydemo.tgbot.entity.Income;
import ru.skillfacrtorydemo.tgbot.entity.Spend;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FilterIncomesAndSpend {
    private List<Income> IncomeRepository;
    private List<Spend> SpendRepository;
    private List<Integer> filterIncome = new ArrayList<>();
    private List<Integer> filterSpend = new ArrayList<>();


    public List<Integer> filterIncomes(Long amount) {
        if (IncomeRepository.isEmpty()) {
            return filterIncome;
        } else {
            for (int i = 0; i < IncomeRepository.size(); i++) {
                ;
                if (i > amount) {
                    filterIncome.add(i);
                } else {
                    i = 0;
                }
            }
        }
        return filterIncome;
    }

    public List<Integer> filterSpends(Long amount) {
        if (SpendRepository.isEmpty()) {
            return filterSpend;
        } else {
            for (int i = 0; i < SpendRepository.size(); i++) {
                List<Integer> filterSpend = new ArrayList<>();
                if (i > amount) {
                    filterSpend.add(i);
                } else {
                    i = 0;
                }
            }
        }
        return filterSpend;
    }
}
