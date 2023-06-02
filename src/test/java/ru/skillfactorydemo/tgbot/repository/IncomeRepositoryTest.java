package ru.skillfactorydemo.tgbot.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.skillfacrtorydemo.tgbot.entity.Income;
import ru.skillfacrtorydemo.tgbot.repository.IncomeRepository;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

@DataJpaTest
public class IncomeRepositoryTest {
    @Autowired
    private IncomeRepository incomeRepository;

    @Test
    public void testRepo(){
        for (int i = 0; i < 10; i++,incomeRepository.save(new Income())) ;
        final List<Income> found = incomeRepository.findAll();

        assertEquals(10,found.size());

    }
}
