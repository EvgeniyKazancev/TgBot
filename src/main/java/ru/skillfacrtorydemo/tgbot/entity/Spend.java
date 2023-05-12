package ru.skillfacrtorydemo.tgbot.entity;

import lombok.Data;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "SPEND")
@Data
public class Spend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "CHAT_ID")
    private Long chatId;

    @Column(name = "SPEND")
    private BigDecimal spend;
}
