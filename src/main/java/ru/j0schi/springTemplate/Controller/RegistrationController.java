package ru.j0schi.springTemplate.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.j0schi.springTemplate.service.AuthorizationService;
import ru.j0schi.springTemplate.service.UserDto;

@RequiredArgsConstructor
@RestController
@RequestMapping("/registration")
public class RegistrationController {
    private final AuthorizationService authorizationService;


    @PostMapping
    String createNewUser(@RequestBody UserDto userDto) {

        if (authorizationService.createUser(
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail(),
                userDto.getPassword())
        )
            return "success";
        else return "try again";
    }
}
