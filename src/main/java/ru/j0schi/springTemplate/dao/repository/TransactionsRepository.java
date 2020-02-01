package ru.j0schi.springTemplate.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.j0schi.springTemplate.dao.Models.TransactionsModel;

import java.util.List;

public interface TransactionsRepository extends JpaRepository<TransactionsModel, Long> {
    List<TransactionsModel> findAllByToIdAccountId(Long id);
}
