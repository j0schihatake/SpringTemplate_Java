package ru.j0schi.springTemplate.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.j0schi.springTemplate.service.*;
import ru.j0schi.springTemplate.service.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
@Service
@RequiredArgsConstructor
public class UserMenu {
    private static Scanner scanner = new Scanner(System.in);
    private final CreateAccountService createAccountService;
    private final AccountService accountService;
    private final AuthorizationService authorizService;
    private final CreateTransactionService createTransactionService;


    protected boolean createNewUser() {
        String firstName;
        String lastName;
        String email;
        String password;
        System.out.println("__________________________");
        System.out.println("|Создание нового аккаунта|");
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println();
        System.out.println("Введите имя:");
        firstName = scanner.nextLine();
        System.out.println("Введите Фамилию");
        lastName = scanner.nextLine();
        System.out.println("Введите e-mail. Внимание - он будет необходим при авторизации!");
        email = scanner.nextLine();
        System.out.println("Придумайте пароль");
        password = scanner.nextLine();
        boolean mark = authorizService.createUser(firstName, lastName, email, password);
        if (mark) {
            System.out.println();
            System.out.println("Ваш аккаунт успешно создан! Вы можете войти и начать работать");
            System.out.println();
        }
        return mark;
    }

    private void createNewAccount(long idUser) {
        int typeAcc = 1;
        BigDecimal currBalance = BigDecimal.ZERO;
        Date date = new Date();

        if (createAccountService.verificationNumOfAccount(idUser) == false) {
            System.out.println("Вы исчерпали лимит создания счетов");
        } else {
            System.out.println();
            System.out.println("_____________________");
            System.out.println("---------------------");
            System.out.println("Создание нового счета");
            System.out.println("_____________________");
            System.out.println("---------------------");
            System.out.println();
            System.out.println("Выберите тип создаваемого счета:");
            System.out.println("1 - наличные");
            System.out.println("2 - кредитка");
            boolean ok = false;
            while (!ok) {
                if (scanner.hasNextInt() & typeAcc == 1 || typeAcc == 2) {
                    typeAcc = scanner.nextInt();
                    ok = true;
                } else System.out.println("Введено некорректное значение. Введите 1 или 2");
            }
            ok = false;
            System.out.println();
            System.out.println("Введите начальный баланс счета");
            while (!ok) {
                if (scanner.hasNextInt()) {
                    currBalance = scanner.nextBigDecimal();
                    ok = true;
                } else {
                    System.out.println("Вы ввели некорректное значение. Введите заново");
                    scanner.nextLine();
                    ok = false;
                }
            }

            System.out.println();
            System.out.println("Введите имя для вашего нового счета");
            scanner.nextLine();
            String nameAcc = scanner.nextLine();
            if (nameAcc.equals("")) {
                System.out.println("Имя задано датой создания счета!");
                nameAcc = date.toString();
            }

            try {

                createAccountService.createNewAccount(new AccountDto()
                        .setDtoAccFields(currBalance, idUser, typeAcc, nameAcc));

            } catch (RuntimeException r) {
                System.out.println(r);
            }
        }

    }

    public char accountMenu(long idUser) throws InputMismatchException {
        char markerForNext = 'n';

        System.out.println();
        System.out.println("Ваши счета:");
        System.out.println();
        int x = 1;
        for (AccountDto accountDto : accountService.accountMenuForWork(idUser)) {
            System.out.println("---------------------------------------------------------------------------");
            System.out.println(x + "|   " + "   id: " + accountDto.getIdAccount() +
                    " |  Текущий остаток: " + accountDto.getCurrentBalance() +
                    " |  Имя счёта: " + accountDto.getAccountName());
            x++;
        }
        System.out.println("___________________________________________________________________________");
        System.out.println("---------------------------------------------------------------------------");
        System.out.println();
        System.out.println("1 - Создать новый счет");
        System.out.println("2 - Перейти в меню тразакций");
        System.out.println("3 - Выйти");

        int choice;
        choice = scanner.nextInt();
        switch (choice) {
            case 1: {
                createNewAccount(idUser);
                break;
            }
            case 2: {
                char next = 'n';
                while (next != 'q') {
                    next = menuTransaction(idUser);
                }

                break;
            }
            case 3: {
                markerForNext = 'q';
                break;
            }
            default:
                System.out.println("Вы ввели некорректное занчение. Введите еще раз");
        }
        return markerForNext;
    }

    private char menuTransaction(long idUser) {
        char mark = 'n';
        System.out.println();
        System.out.println("МЕНЮ ТРАНЗАКЦИИ");
        System.out.println();
        int x = 1;
        BigDecimal summ;
        for (AccountDto accountDto : accountService.accountMenuForWork(idUser)) {
            System.out.println("---------------------------------------------------------------------------");
            System.out.println(x + "|   " + "   id: " + accountDto.getIdAccount() +
                    " |  Текущий остаток: " + accountDto.getCurrentBalance() +
                    " |  Имя счёта: " + accountDto.getAccountName());
            x++;
        }
        System.out.println();
        System.out.println("1 - перевод между счетами");
        System.out.println("2 - списание со счета");
        System.out.println("3 - пополнение счета");
        System.out.println("4 - Выйти из меню транзакции");
        int choice;
        choice = scanner.nextInt();
        switch (choice) {
            case 1: {
                int firstAcc = 0, secAcc = 0;
                boolean mark1 = false;
                while (!mark1) {
                    System.out.println("Введите id счета с которого будет сделан перевод:");
                    if (verificationIdAccount(idUser, firstAcc = scanner.nextInt())) mark1 = true;
                    else System.out.println("Неверный ID, попробуйте снова");
                }
                mark1 = false;
                while (!mark1) {
                    System.out.println("Введите id счета на который будет сделан перевод:");
                    if (verificationIdAccount(idUser, secAcc = scanner.nextInt())) mark1 = true;
                    else System.out.println("Неверный ID, попробуйте снова");
                }
                System.out.println("Введите сумму перевода:");
                summ = BigDecimal.valueOf(scanner.nextInt());
                TransactionDto transactionDto = new TransactionDto();
                transactionDto.setToIdAccount(firstAcc);
                transactionDto.setFromIdAccount(secAcc);
                transactionDto.setAmount(summ);
                transactionDto.setIdTransactionType(1);
                createTransactionService.writeTransaction(transactionDto);
                System.out.println("Сделано");
                break;
            }
            case 2: {
                boolean mark1 = false;
                int idAcc = 0, idTypeTrans;
                while (!mark1) {
                    System.out.println("Введите id счета с которого будет сделано списание:");
                    if (verificationIdAccount(idUser, idAcc = scanner.nextInt())) mark1 = true;
                    else System.out.println("Неверный ID, попробуйте снова");
                }
                System.out.println("Выберите тип транзакции");
                idTypeTrans = scanner.nextInt();
                System.out.println("Введите сумму");
                summ = BigDecimal.valueOf(scanner.nextInt());
                TransactionDto transactionDto = new TransactionDto();
                transactionDto.setToIdAccount(idAcc);
                transactionDto.setFromIdAccount(0);
                transactionDto.setAmount(summ);
                transactionDto.setIdTransactionType(idTypeTrans);
                if (createTransactionService.writeTransaction(transactionDto))
                    System.out.println("Сделано");
                break;
            }
            case 3: {
                boolean mark1 = false;
                int idAcc = 0, idTypeTrans;
                while (!mark1) {
                    System.out.println("Введите id счета для пополнения:");
                    if (verificationIdAccount(idUser, idAcc = scanner.nextInt())) mark1 = true;
                    else System.out.println("Неверный ID, попробуйте снова");
                }
                System.out.println("Выберите тип транзакции");
                idTypeTrans = scanner.nextInt();
                System.out.println("Введите сумму");
                summ = BigDecimal.valueOf(scanner.nextInt());
                TransactionDto transactionDto = new TransactionDto();
                transactionDto.setToIdAccount(idAcc);
                transactionDto.setFromIdAccount(0);
                transactionDto.setAmount(summ);
                transactionDto.setIdTransactionType(idTypeTrans);
                if (createTransactionService.writeTransaction(transactionDto))
                    System.out.println("Сделано");
                break;
            }
            case 4: {
                mark = 'q';
                break;
            }
            default:
                System.out.println("Некорректный символ");
        }
        return mark;
    }

    private boolean verificationIdAccount(long idUser, long idAccount) {
        boolean mark = false;
        if (accountService.getAllIdAcc(idUser).contains(idAccount)) mark = true;
        return mark;
    }


}
