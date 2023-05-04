package ru.skillfacrtorydemo.tgbot.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skillfacrtorydemo.tgbot.dto.ValuteCursOnDate;
import ru.skillfacrtorydemo.tgbot.service.CentralRussianBankService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CurrencyController {
    private final CentralRussianBankService centralRussianBankService;
    @GetMapping("/")
    public List<ValuteCursOnDate> getValuteCursOnDate() throws Exception{
        return centralRussianBankService.getCurrenciesFromCbr();
    }
}
