package ru.j0schi.springTemplate.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.j0schi.springTemplate.dao.Models.AccountTypeModel;

public interface AccountTypeRepository extends JpaRepository<AccountTypeModel, Long> {
}
