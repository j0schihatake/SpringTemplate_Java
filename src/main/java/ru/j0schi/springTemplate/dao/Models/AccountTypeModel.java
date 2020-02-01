package ru.j0schi.springTemplate.dao.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "account_type")
public class AccountTypeModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_account_type")
    private Long id;

    @Column(name = "account_type_name")
    private String accountTypeName;

    @Override
    public String toString() {
        return "AccountTypeModel{" +
                "id=" + id +
                ", accountTypeName='" + accountTypeName + '\'' +
                '}';
    }
}
