package ru.j0schi.springTemplate.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.j0schi.springTemplate.dao.Models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByEmailAndPassword(String name, String pass);

    UserModel findByEmail(String name);


}
