package ru.j0schi.springTemplate.dao.Models;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Accessors(chain = true)
@Data
@Entity
@Table(name = "users_role")
public class UsersRoleModel {

    @Id
    @Column(name = "id_role")
    Integer idRole;

    @Column(name = "role_name")
    String roleName;
}
