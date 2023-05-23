package ru.skillfacrtorydemo.tgbot.service;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.jvnet.hk2.annotations.Service;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ru.skillfacrtorydemo.tgbot.repository.IncomeRepository;
import ru.skillfacrtorydemo.tgbot.repository.SpendRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FilterIncomesAndSpendTest {
    @InjectMocks
    private FilterIncomesAndSpend filterIncomesAndSpend;

    @Mock
    private SpendRepository spendRepository;

    @Mock
    private IncomeRepository incomeRepository;

    @DisplayName(value = "FILTER_INCOMES")
    @Test
    public void filterIncomesTest(){
        long amount = 550000;
        long chatId =
        List<Integer> result = new ArrayList<>();
        result.add( filterIncomesAndSpend.filterIncomes(amount));
        Assert.assertArrayEquals("amount",result, filterIncomesAndSpend);
    }

}
