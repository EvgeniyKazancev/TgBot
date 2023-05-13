package ru.skillfacrtorydemo.tgbot.entity;

import lombok.Data;
import lombok.ToString;


import javax.persistence.*;
@ToString
@Data
@Entity// Данный класс является JPA сущностью
@Table(name = "ACTIVE_CHAT")//И хранится в таблице ACTIVE_CHAT
public class ActiveChat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //Уникальный идентификатор в системе нашего бота

    @Column
    private Long chatId;//Уникальный идентификатор в системе Telegram
}
