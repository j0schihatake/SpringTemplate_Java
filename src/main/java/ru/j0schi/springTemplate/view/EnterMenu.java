package ru.j0schi.springTemplate.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.j0schi.springTemplate.service.AuthorizationService;

import java.util.InputMismatchException;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class EnterMenu {
    private static Scanner scanner = new Scanner(System.in);
    private static long idUser;
    private static int choice;

    private final AuthorizationService authorizService;
    private final UserMenu userMenu;


    public long helloFirst() {
        System.out.println("Выберите дейстие:");
        System.out.println("1 - чтобы войти с вашей учетной записью");
        System.out.println("2 - чтобы создать новую учетную запись");
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Неккорректный ввод");
        }

        scanner.nextLine();
        switch (choice) {

            case 1:
                enterUser();
                break;

            case 2:
                if (userMenu.createNewUser()) {
                    System.out.println("Аккаунт успешно создан. Вы можете войти с новой учетной записью!");
                } else {
                    System.out.println("Извините, не удалось создать новый аккаунт");
                }
                break;
        }
        return idUser;
    }

    private void enterUser() throws InputMismatchException {

        System.out.println("Введите e-mail");
        String str1 = scanner.nextLine();
        System.out.println("Введите пароль");
        String str2 = scanner.nextLine();
        idUser = authorizService.authoriz(str1, str2);
        if (idUser > 0) {
            System.out.println("Успешная авторизация");
        } else {
            System.out.println("------------------------");
            System.out.println("Неверный e-mail/password");
            System.out.println("------------------------");
            System.out.println("Попробуйте ещё раз");
            System.out.println();
        }
    }

}
