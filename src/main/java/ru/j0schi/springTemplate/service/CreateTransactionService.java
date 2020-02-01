package ru.j0schi.springTemplate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.j0schi.springTemplate.converter.ConverterDtoToDao;
import org.springframework.stereotype.Service;
import ru.j0schi.springTemplate.dao.Models.AccountModel;
import ru.j0schi.springTemplate.dao.Models.TransactionsModel;
import ru.j0schi.springTemplate.dao.repository.AccountRepository;
import ru.j0schi.springTemplate.dao.repository.TransactionsRepository;
import ru.j0schi.springTemplate.dao.repository.TransctionTypeRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateTransactionService {

    private final AccountRepository accountRepository;
    private final TransctionTypeRepository transctionTypeRepository;
    private final TransactionsRepository transactionsRepository;


    @Transactional
    public boolean writeTransaction(TransactionDto transactionDto) {
        transactionDto.setTransactionDate(new Date());
        accountRepository.save(setSum(accountRepository.findById(transactionDto.getToIdAccount()).get()
                , transactionDto.getAmount()));
        if (transactionDto.getFromIdAccount() > 0)
            accountRepository.save(setSum(accountRepository.findById(transactionDto.getFromIdAccount()).get()
                    , transactionDto.getAmount().negate()));
        TransactionsModel transactionsModel =
                ConverterDtoToDao.convert(transactionDto,
                        transctionTypeRepository.findAllById(Collections.singleton(transactionDto.getIdTransactionType())));
        transactionsModel.setToIdAccount(accountRepository.findById(transactionDto.getToIdAccount()).get());
        if (transactionDto.getFromIdAccount() > 0)
            transactionsModel.setFromIdAccount(accountRepository.findById(transactionDto.getFromIdAccount()).get());
        transactionsRepository.save(transactionsModel);
        return true;
    }

    public List<TransactionDto> getAllTransactionsForUser(String email) {
        List<TransactionDto> list = new ArrayList<>();
        for (AccountModel accountModel : accountRepository.findAllByUserModelEmail(email)) {
            for (TransactionsModel transactionsModel : transactionsRepository.findAllByToIdAccountId(accountModel.getId())) {
                list.add(ConverterDtoToDao.convert(transactionsModel));
            }
        }
        return list;
    }

    public List<TransactionDto> getAllTransactionsForAccount(Long id, String email) {
        List<TransactionDto> list = new ArrayList<>();
        List<Long> listAccountId = new ArrayList<>();
        for (AccountModel accountModel : accountRepository.findAllByUserModelEmail(email)) {
            listAccountId.add(accountModel.getId());
        }
        if (listAccountId.contains(id)) {
            for (TransactionsModel transactionsModel : transactionsRepository.findAllByToIdAccountId(id)) {
                list.add(ConverterDtoToDao.convert(transactionsModel));
            }
        }
        return list;
    }

    public AccountModel setSum(AccountModel accountModel, BigDecimal sum) {
        accountModel.setCurrentBalance(accountModel.getCurrentBalance().add(sum));
        return accountModel;
    }
}
