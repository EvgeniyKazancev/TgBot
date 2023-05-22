package ru.skillfacrtorydemo.tgbot.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class StatsRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public int getCountOfIncomesThatGreaterThan(BigDecimal amount) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("amount", amount);
        return namedParameterJdbcTemplate.queryForObject("SELECT count(*) FROM INCOMES WHERE INCOME > :amount", parameters, new StatsRowMapper());
        //return   jdbcTemplate.queryForObject("SELECT count(*) FROM INCOMES WHERE INCOME >?", Integer.class, amount);
    }

    public int getCountSpendThatGreaterThan(Long amount) {
        Map<String, Object> param = new HashMap<>();
        param.put("amount", amount);
        return namedParameterJdbcTemplate.queryForObject("SELECT count(*) FROM SPEND WHERE spend > :amount", param, new StatsRowMapper());

    }

    private static final class StatsRowMapper implements RowMapper<Integer> {

        @Override
        public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getInt("COUNT");
        }
    }

}
