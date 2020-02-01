package ru.j0schi.springTemplate.service;

import lombok.RequiredArgsConstructor;
import ru.j0schi.springTemplate.converter.ConverterDtoToDao;
import ru.j0schi.springTemplate.dao.Models.AccountModel;
import org.springframework.stereotype.Service;
import ru.j0schi.springTemplate.dao.repository.AccountRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final ConverterDtoToDao converterDtoToDao;

    public List<AccountDto> accountMenuForWork(Long idUser) {
        List accountDtoList = new ArrayList<>();
        for (AccountModel accountModel : accountRepository.findAccountModelsByUserModelId(idUser)) {
            accountDtoList.add(converterDtoToDao.convert(accountModel));
        }
        return accountDtoList;
    }

    public List<Long> getAllIdAcc(long idUser) {
        List<Long> allIdUsersAccount = new ArrayList<>();
        for (AccountModel accountModel : accountRepository.findAccountModelsByUserModelId(idUser)) {
            allIdUsersAccount.add(accountModel.getId());
        }
        return allIdUsersAccount;
    }
}
