package ru.j0schi.springTemplate.dao.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "transactions_category")
public class TransactionsTypeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transactions_category")
    private Long id;

    @Column(name = "transactions_category_name")
    private String transactionTypeName;

    @Override
    public String toString() {
        return "TransactionsTypeModel{" +
                "id=" + id +
                ", transactionTypeName='" + transactionTypeName + '\'' +
                '}';
    }
}