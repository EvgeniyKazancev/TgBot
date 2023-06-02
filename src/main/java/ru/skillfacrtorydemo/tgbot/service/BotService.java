package ru.skillfacrtorydemo.tgbot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.skillfacrtorydemo.tgbot.dto.ValuteCursOnDate;
import ru.skillfacrtorydemo.tgbot.entity.ActiveChat;
import ru.skillfacrtorydemo.tgbot.repository.ActiveChatRepository;
import ru.skillfacrtorydemo.tgbot.repository.StatsRepository;
import ru.skillfacrtorydemo.tgbot.service.CentralRussianBankService;

import javax.annotation.PostConstruct;
import javax.xml.datatype.DatatypeConfigurationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
@RequiredArgsConstructor
@Service
public class BotService extends TelegramLongPollingBot {
    private static final String CURRENT_RATES = "/currentrates";
    private static final String ADD_INCOME = "/addincome";
    private static final String ADD_SPEND = "/addspend";

    private final FinanceService financeService;
    private final CentralRussianBankService centralRussianBankService;
    @Value("${bot.api.key}")
    private String apiKey;
    @Value("${bot.name}")
    private String name;
    private List<StatsService> income = new ArrayList<>();

    private List<StatsService> spend = new ArrayList<>();


    private final ActiveChatRepository activeChatRepository;
    private Map<Long, List<String>> previousCommands = new ConcurrentHashMap<>();

    private void putPreviousCommand(Long chatId, String command) {
        if (previousCommands.get(chatId) == null) {
            List<String> commands = new ArrayList<>();
            commands.add(command);
            previousCommands.put(chatId, commands);
        } else {
            previousCommands.get(chatId).add(command);
        }
    }

    private String getPreviousCommand(Long chatId) {
        return previousCommands.get(chatId).get(previousCommands.get(chatId).size() - 1);
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();//Этой строчкой мы получаем сообщение от пользователя
        try {
            SendMessage response = new SendMessage();
            Long chatId = message.getChatId();
            response.setChatId(String.valueOf(chatId));
            if (CURRENT_RATES.equalsIgnoreCase(message.getText())) {
                for (ValuteCursOnDate valuteCursOnDate : centralRussianBankService.getCurrenciesFromCbr()) {
                    response.setText(StringUtils.defaultIfBlank(response.getText(), "") + valuteCursOnDate.getName() + " - " + valuteCursOnDate.getCourse() + "\n");

                }
            } else if (ADD_INCOME.equalsIgnoreCase(message.getText())) {
                response.setText("Отправьте мне сумму полученного дохода");
            } else if (ADD_SPEND.equalsIgnoreCase(message.getText())) {
                response.setText("Отправьте мне сумму расходов");
            } else {
                response.setText(financeService.addFinanceOperation(getPreviousCommand(message.getChatId()), message.getText(), message.getChatId()));
            }

            putPreviousCommand(message.getChatId(), message.getText());
            execute(response);
            if (activeChatRepository.findActiveChatByChatId(chatId).isEmpty()) {
                ActiveChat activeChat = new ActiveChat();
                activeChat.setChatId(chatId);
                activeChatRepository.save(activeChat);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        } catch (Exception e) {
            log.error("Возникла проблема при получении данных от сервиса ЦБ РФ ", e);
        }
    }


    public void sendNotificationToAllActiveChats(String message, Set<Long> chatIds) {
        for (Long id : chatIds) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(String.valueOf(id));
            sendMessage.setText(message);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }



    @PostConstruct
    public void start() {
        log.info("username: {}, token: {}", name, apiKey);
    }

    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public String getBotToken() {
        return apiKey;
    }


}
