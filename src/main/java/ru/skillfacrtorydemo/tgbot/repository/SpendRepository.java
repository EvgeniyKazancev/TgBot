package ru.skillfacrtorydemo.tgbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skillfacrtorydemo.tgbot.entity.Spend;

@Repository
public interface SpendRepository extends JpaRepository<Spend,Long> {
}
