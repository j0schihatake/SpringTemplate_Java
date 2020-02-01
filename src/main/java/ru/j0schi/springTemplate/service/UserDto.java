package ru.j0schi.springTemplate.service;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private long idUser;
    private String userRole;
}
