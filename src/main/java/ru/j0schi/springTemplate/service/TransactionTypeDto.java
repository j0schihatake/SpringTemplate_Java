package ru.j0schi.springTemplate.service;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionTypeDto {
    private Long transactionTypeId;
    private String transactionTypeName;
}
