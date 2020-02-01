package ru.j0schi.springTemplate.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.j0schi.springTemplate.dao.repository.AccountRepository;
import ru.j0schi.springTemplate.dao.repository.UserRepository;
import ru.j0schi.springTemplate.service.AccountDto;
import ru.j0schi.springTemplate.service.CreateAccountService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountRepository accountRepository;
    private final CreateAccountService createAccountService;
    private final UserRepository userRepository;

    @GetMapping
    List<AccountDto> getUserAccounts(Principal principal) {
        return accountRepository.findAllByUserModelEmail(principal.getName())
                .stream().map(accountModel -> new AccountDto()
                        .setAccountName(accountModel.getAccountName())
                        .setCurrentBalance(accountModel.getCurrentBalance())
                        .setIdAccount(accountModel.getId())
                        .setIdUser(accountModel.getUserModel().getId())).collect(Collectors.toList());
    }

    @PostMapping("/create_new_account")
    String createNewAccount(@RequestBody AccountDto accountDto, Principal principal) {
        accountDto.setIdUser(userRepository.findByEmail(principal.getName()).getId());
        if (createAccountService.verificationNumOfAccount(userRepository.findByEmail(principal.getName()).getId())) {
            if (createAccountService.createNewAccount(accountDto)) return "success";
            else return "try again";
        } else return "to many account";
    }
}
