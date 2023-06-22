package ru.skillfactorydemo.tgbot.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import ru.skillfacrtorydemo.tgbot.entity.Spend;
import ru.skillfacrtorydemo.tgbot.repository.IncomeRepository;
import ru.skillfacrtorydemo.tgbot.repository.SpendRepository;
import ru.skillfacrtorydemo.tgbot.service.FinanceService;

import static org.mockito.Mockito.when;

@DataJpaTest
public class SpendRepositoryTest {

    @Autowired
    private SpendRepository spendRepository;

//    @Test
//    public void testData() {
//        // �������� ���-������� Spend
//        Spend expectedSpend = new Spend();
//        //expectedSpend.setSpend(new BigDecimal("5000.00"));
//
//        // ������� ��������� ���-������� spendRepository
//        when(spendRepository.findById(44L)).thenReturn(Optional.of(expectedSpend));
//
//        // ����� ������������ ������
//        Optional<Spend> spendOptional = spendRepository.getSpendByChatId(44L);
//
//        // �������� ����������
//        Assertions.assertTrue(spendOptional.isPresent());
//        Assertions.assertEquals(new BigDecimal("5000.00"), spendOptional.get().getSpend());
//    }
    @Test
    public void spendRepTest(){
        for (int i = 0; i < 10; i++,spendRepository.save(new Spend()));
            final List<Spend> found = spendRepository.findAll();

        assertEquals(10,found.size());



    }

}
