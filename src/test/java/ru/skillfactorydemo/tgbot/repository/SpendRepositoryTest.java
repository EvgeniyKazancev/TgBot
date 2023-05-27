package ru.skillfactorydemo.tgbot.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;


import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.skillfacrtorydemo.tgbot.entity.Spend;
import ru.skillfacrtorydemo.tgbot.repository.SpendRepository;



import java.math.BigDecimal;
import java.util.Optional;
@DataJpaTest

public class SpendRepositoryTest {
   @Autowired
    private SpendRepository spendRepository;

    @Test
    public void testData(){
        Optional<Spend> spendOptional = spendRepository.findById(44L);
        Assertions.assertTrue(spendOptional.isPresent());
        Assertions.assertEquals(new BigDecimal("5000.00"), spendOptional.get().getSpend());

    }

}
