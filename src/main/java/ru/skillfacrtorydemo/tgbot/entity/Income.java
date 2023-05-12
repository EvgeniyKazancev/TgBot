package ru.skillfacrtorydemo.tgbot.entity;

import lombok.Data;
import lombok.Getter;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "INCOMES")
@Data
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CHAT_ID")
    private Long chatId;

    @Column(name = "INCOME")
    private BigDecimal income;


}
