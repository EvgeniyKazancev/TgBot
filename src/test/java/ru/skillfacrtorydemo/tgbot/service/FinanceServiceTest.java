package ru.skillfacrtorydemo.tgbot.service;

import org.junit.Assert;


import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;

import ru.skillfacrtorydemo.tgbot.repository.IncomeRepository;
import ru.skillfacrtorydemo.tgbot.repository.SpendRepository;

// �����-���� ��� ������������ FinanceService
//*/
//// ���������, ��� ���� ����� - ��������, ��� ����������� SpringBoot
@SpringBootTest
// ���������, ��� ������� ����� �������� �� ���� ����� (�.�. ��� ��������� ���� �������)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FinanceServiceTest {
    // ���������� ������ ������� � ����������, ������� ��������� ������
    // � �������� �������� ������ ������������,
    // ��� ��� � ������ �� ��� ��������� ����������������, � ������ � �����
    @InjectMocks
    private FinanceService financeService;
    // ���������, ��� ���� ����� ���� ����������� (�� ������������ � FinanceService)
    @Mock
    private SpendRepository spendRepository;
    @Mock
    private IncomeRepository incomeRepository;

    // ������� �����, ����� ������� ������ ����
    @BeforeEach
    public void beforeAll() {
        System.out.println(System.currentTimeMillis());
    }

    // ������� �����, ����� ���������� ������ ����
    @AfterEach
    public void afterAll() {
        System.out.println(System.currentTimeMillis());
    }

    // �������� �����, �������� ��� ���������� @Test
    // �������� ����� ��� ������� �����
    @DisplayName("ADD_INCOME_test")
    @Test
    public void addFinanceOperationAddIncomeTest() {
        // ���������� ������������ �������� ���������� ��� �������� � �����
        String price = "150.0";
        // ���������� � ������ � ������������� ����������� � ��������� ��������� � ����������
        String message = financeService.addFinanceOperation("/addincome", price, 500L);
        // ����������, ��� �������� ��������� ���������
        Assert.assertEquals("Доход в размере " + price + " был успешно добавлен", message);
    }

    @DisplayName("non_ADD_INCOM_test")
    @Test
    public void addFinanceOperationElseBranchTest() {
        // ����� ��� �������� ����������
        String price = "200";
        // ����� ���������� � ������ � ������� �����������
        String message = financeService.addFinanceOperation("/nan", price, 250L);
        // ����������, ��� �������� ��������� ���������
        Assert.assertEquals("Расход в размере " + price + " успешно добавлен", message);
    }


}
