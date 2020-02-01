package ru.j0schi.springTemplate.service;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Accessors(chain = true)
public class TransactionDto {
    private long idTransactions;
    private Date transactionDate;
    private BigDecimal amount;
    private long toIdAccount;
    private long fromIdAccount;
    private long idTransactionType;
}

