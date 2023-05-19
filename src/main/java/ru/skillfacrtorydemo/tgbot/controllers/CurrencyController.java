package ru.skillfacrtorydemo.tgbot.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.skillfacrtorydemo.tgbot.dto.ValuteCursOnDate;
import ru.skillfacrtorydemo.tgbot.service.CentralRussianBankService;
import ru.skillfacrtorydemo.tgbot.service.StatsService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CurrencyController {
    private final CentralRussianBankService centralRussianBankService;
    private final StatsService statsService;
    @GetMapping("/")
    public List<ValuteCursOnDate> getValuteCursOnDate() throws Exception{
        return centralRussianBankService.getCurrenciesFromCbr();
    }
    @GetMapping("/getstats")
    @ApiOperation(value = "Получение количества пополнений, которые превышают определенную сумму")
    public int getStatsAboutIncomesThatGreater(@RequestParam(value = "amount")BigDecimal amount){
        return statsService.getCountOfIncomesThatGreater(amount);
    }

}
