package ru.j0schi.springTemplate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.j0schi.springTemplate.converter.ConverterDtoToDao;
import ru.j0schi.springTemplate.dao.repository.UserRepository;

import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class AuthorizationService {


    private final DigestService digestService;
    private static UserDto userDto = new UserDto();
    private final UserRepository userRepository;

    public long authoriz(String name, String pass) {
        String hashPass = digestService.hash(pass);
        try {
            return userRepository.findByEmailAndPassword(name, hashPass).getId();
        } catch (NullPointerException e) {
            return 0L;
        }


    }

    public boolean createUser(String firstName, String lastName,
                              String email, String password) {
        userDto.setFirstName(firstName);
        userDto.setLastName(lastName);
        userDto.setEmail(verifyEmail(email));
        userDto.setPassword(digestService.hash(password));
        long id = 0;
        try {
            id = userRepository.save(ConverterDtoToDao.convert(userDto)).getId();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        if (id > 0) return true;
        else System.out.println("Что-то пошло не так, попробуйте еще раз");
        return false;
    }

    private String verifyEmail(String email) {
        while (true) {
            if (userRepository.findByEmail(email) == null) break;
            else {
                System.out.println("Указанный e-mail уже занят. Введите другой:");
                Scanner scanner = new Scanner(System.in);
                email = scanner.nextLine();
            }
        }
        return email;
    }


}
