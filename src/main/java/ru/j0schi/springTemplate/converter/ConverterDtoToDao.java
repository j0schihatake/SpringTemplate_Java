package ru.j0schi.springTemplate.converter;

import org.springframework.stereotype.Service;
import ru.j0schi.springTemplate.dao.Models.*;
import ru.j0schi.springTemplate.service.*;
import ru.j0schi.springTemplate.dao.Models.*;
import ru.j0schi.springTemplate.service.*;

import java.util.List;

@Service
public class ConverterDtoToDao {

    public static UserModel convert(UserDto userDto) {
        UserModel userModel = new UserModel();
        UsersRoleModel usersRoleModel = new UsersRoleModel().setIdRole(2).setRoleName("user");
        userModel.setFirstName(userDto.getFirstName());
        userModel.setLastName(userDto.getLastName());
        userModel.setEmail(userDto.getEmail());
        userModel.setId(userDto.getIdUser());
        userModel.setPassword(userDto.getPassword());
        userModel.setUsersRoleModel(usersRoleModel);
        return userModel;
    }

    public static UserDto convert(UserModel userModel) {
        UserDto userDto = new UserDto();
        userDto.setFirstName(userModel.getFirstName());
        userDto.setLastName(userModel.getLastName());
        userDto.setEmail(userModel.getEmail());
        userDto.setPassword(userModel.getPassword());
        userDto.setIdUser(userModel.getId());
        return userDto;
    }

    public static AccountModel convert(AccountDto accountDto, AccountTypeModel accountTypeModel, UserModel userModel) {
        AccountModel accountModel = new AccountModel();
        accountModel.setAccountName(accountDto.getAccountName());
        accountModel.setCurrentBalance(accountDto.getCurrentBalance());
        accountModel.setId(accountDto.getIdAccount());
        accountModel.setUserModel(userModel);
        accountModel.setAccountTypeModel(accountTypeModel);
        return accountModel;
    }


    public static AccountDto convert(AccountModel accountModel) {
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountName(accountModel.getAccountName());
        accountDto.setCurrentBalance(accountModel.getCurrentBalance());
        accountDto.setIdAccount(accountModel.getId());
        return accountDto;
    }

    public static TransactionsModel convert(TransactionDto transactionDto, List<TransactionsTypeModel> ttm) {
        TransactionsModel transactionsModel = new TransactionsModel();
        transactionsModel.setAmount(transactionDto.getAmount());
        transactionsModel.setId(transactionDto.getIdTransactions());
        transactionsModel.setTransactionDate(transactionDto.getTransactionDate());
        transactionsModel.setTypeModels(ttm);
        return transactionsModel;
    }

    public static TransactionDto convert(TransactionsModel transactionsModel) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAmount(transactionsModel.getAmount());
        transactionDto.setIdTransactions(transactionsModel.getId());
        transactionDto.setTransactionDate(transactionsModel.getTransactionDate());
        transactionDto.setToIdAccount(transactionsModel.getToIdAccount().getId());
        if (transactionsModel.getFromIdAccount() == null) transactionDto.setFromIdAccount(0L);
        else
            transactionDto.setFromIdAccount(transactionsModel.getFromIdAccount().getId());
        return transactionDto;
    }

    public static TransactionsTypeModel convert(TransactionTypeDto transactionTypeDto) {
        TransactionsTypeModel transactionsTypeModel = new TransactionsTypeModel();
        transactionsTypeModel.setId(transactionTypeDto.getTransactionTypeId());
        transactionsTypeModel.setTransactionTypeName(transactionTypeDto.getTransactionTypeName());
        return transactionsTypeModel;
    }

    public static TransactionTypeDto convert(TransactionsTypeModel transactionsTypeModel) {
        TransactionTypeDto transactionTypeDto = new TransactionTypeDto();
        transactionTypeDto.setTransactionTypeId(transactionsTypeModel.getId());
        transactionTypeDto.setTransactionTypeName(transactionsTypeModel.getTransactionTypeName());
        return transactionTypeDto;
    }

    public static TattModel convert(TattDto tattDto) {
        TattModel tattModel = new TattModel();
        tattModel.setIdTransactionType(tattDto.getIdTransactionType());
        tattModel.setIdTransaction(tattDto.getIdTransaction());
        return tattModel;
    }

    public static TattDto convert(TattModel tattModel) {
        TattDto tattDto = new TattDto();
        tattDto.setIdTransaction(tattModel.getIdTransaction());
        tattDto.setIdTransactionType(tattModel.getIdTransactionType());
        return tattDto;
    }
}