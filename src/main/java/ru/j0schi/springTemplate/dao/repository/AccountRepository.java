package ru.j0schi.springTemplate.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import ru.j0schi.springTemplate.dao.Models.AccountModel;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountModel, Long> {
    List<AccountModel> findAccountModelsByUserModelId(Long id);

    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<AccountModel> findById(Long aLong);

    List<AccountModel> findAllByUserModelEmail(String name);
}
