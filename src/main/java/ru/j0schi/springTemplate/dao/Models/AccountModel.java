package ru.j0schi.springTemplate.dao.Models;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@Accessors(chain = true)
@Data
@Entity
@Table(name = "account")
public class AccountModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_account")
    private Long id;

    @Column(name = "current_balance")
    private BigDecimal currentBalance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    UserModel userModel;

    @ManyToOne
    @JoinColumn(name = "account_type", referencedColumnName = "id_account_type")
    AccountTypeModel accountTypeModel;

    @Column(name = "account_name")
    private String accountName;

    @Override
    public String toString() {
        return "AccountModel{" +
                "id=" + id +
                ", currentBalance=" + currentBalance +
                ", userModel=" + userModel +
                ", accountTypeModel=" + accountTypeModel +
                ", accountName='" + accountName + '\'' +
                '}';
    }
}