package ru.j0schi.springTemplate.service;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class AccountDto {
    private long idAccount;
    private BigDecimal currentBalance;
    private long idUser;
    private long accountType;
    private String accountName;

    public AccountDto setDtoAccFields(BigDecimal currentBalance, long idUser, long accountType, String accountName) {
        this.currentBalance = currentBalance;
        this.idUser = idUser;
        this.accountType = accountType;
        this.accountName = accountName;
        return this;
    }


}
