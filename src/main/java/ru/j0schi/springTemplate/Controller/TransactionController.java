package ru.j0schi.springTemplate.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.j0schi.springTemplate.dao.Models.AccountModel;
import ru.j0schi.springTemplate.dao.repository.AccountRepository;
import ru.j0schi.springTemplate.service.AccountDto;
import ru.j0schi.springTemplate.service.CreateTransactionService;
import ru.j0schi.springTemplate.service.TransactionDto;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private final CreateTransactionService createTransactionService;
    private final AccountRepository accountRepository;

    @GetMapping
    List<TransactionDto> getallTransactions(Principal principal) {
        return createTransactionService.getAllTransactionsForUser(principal.getName());
    }

    @PostMapping("/account_id")
    List<TransactionDto> getTranactionsForAccount(@RequestBody AccountDto accountDto, Principal principal) {
        return createTransactionService.getAllTransactionsForAccount(accountDto.getIdAccount(), principal.getName());
    }

    @PostMapping("/create_transaction")
    void createTransaction(@RequestBody TransactionDto transactionDto, Principal principal) {
        List<Long> listAccountId = new ArrayList<>();
        for (AccountModel accountModel : accountRepository.findAllByUserModelEmail(principal.getName())) {
            listAccountId.add(accountModel.getId());
        }
        createTransactionService.writeTransaction(transactionDto);
    }

}
