package ru.skillfacrtorydemo.tgbot.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.skillfacrtorydemo.tgbot.dto.ValuteCursOnDate;
import ru.skillfacrtorydemo.tgbot.entity.Income;
import ru.skillfacrtorydemo.tgbot.entity.Spend;
import ru.skillfacrtorydemo.tgbot.repository.IncomeRepository;
import ru.skillfacrtorydemo.tgbot.repository.SpendRepository;
import ru.skillfacrtorydemo.tgbot.service.CentralRussianBankService;
import ru.skillfacrtorydemo.tgbot.service.FilterIncomesAndSpend;
import ru.skillfacrtorydemo.tgbot.service.StatsService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CurrencyController {
    private final CentralRussianBankService centralRussianBankService;
    private final StatsService statsService;
   private final FilterIncomesAndSpend filterIncomesAndSpend;

    @GetMapping("/")
    public List<ValuteCursOnDate> getValuteCursOnDate() throws Exception{
        return centralRussianBankService.getCurrenciesFromCbr();
    }
    @GetMapping("/getstats")
    @ApiOperation(value = "Получение количества пополнений, которые превышают определенную сумму")
    public int getStatsAboutIncomesThatGreater(@RequestParam(value = "amount")BigDecimal amount){
        return statsService.getCountOfIncomesThatGreater(amount);
    }
    @GetMapping("/getSpend")
    @ApiOperation(value = "Получение количества пополнений,которые выше определенной суммы")
    public  int getSpendAboutCertainAmount(@RequestParam(value = "amount")Long amount){
        return statsService.getCountSpendThatGreater(amount);
    }
    @GetMapping("/getIncomesFilter")
    @ApiOperation(value = "Получение дохода выше, определенной суммы")
    public List<Income> getFilterIncomes(@RequestParam(value = "amount")Long amount,Long chatId){
        return filterIncomesAndSpend.filterIncomes(chatId,amount);
    }
    @GetMapping("/getSpendFilter")
    @ApiOperation(value = "Получение расхода выше, определенной суммы")
    public List<Spend> getFilterSpend(@RequestParam(value = "amount")Long amount,Long chatId){
        return filterIncomesAndSpend.filterSpends(chatId,amount);
    }

}
