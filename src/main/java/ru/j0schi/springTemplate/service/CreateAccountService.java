package ru.j0schi.springTemplate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.j0schi.springTemplate.converter.ConverterDtoToDao;
import org.springframework.stereotype.Service;
import ru.j0schi.springTemplate.dao.repository.AccountRepository;
import ru.j0schi.springTemplate.dao.repository.AccountTypeRepository;
import ru.j0schi.springTemplate.dao.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CreateAccountService {
    private final AccountRepository accountRepository;
    private final ConverterDtoToDao converterDtoToDao;
    private final UserRepository userRepository;
    private final AccountTypeRepository accountTypeRepository;


    @Transactional
    public Boolean createNewAccount(AccountDto accountDto) {
        long backId =
                accountRepository
                        .save(converterDtoToDao
                                .convert(accountDto,
                                        accountTypeRepository.getOne(accountDto.getAccountType()),
                                        userRepository.getOne(accountDto.getIdUser()))).getId();
        return backId > 0;
    }


    public boolean verificationNumOfAccount(long idUser) {
        return accountRepository.findAccountModelsByUserModelId(idUser).size() < 5;
    }

}

