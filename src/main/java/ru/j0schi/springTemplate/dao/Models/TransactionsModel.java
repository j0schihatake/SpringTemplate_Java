package ru.j0schi.springTemplate.dao.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "transactions")
public class TransactionsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaction")
    private Long id;

    @Column(name = "transaction_date")
    private Date transactionDate;

    @Column(name = "amount")
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "to_account_id", referencedColumnName = "id_account")
    AccountModel toIdAccount;

    @ManyToOne
    @JoinColumn(name = "from_account_id", referencedColumnName = "id_account")
    AccountModel fromIdAccount;

    @ManyToMany
    @JoinTable(name = "trans_and_trans_category", joinColumns = @JoinColumn(name = "id_transaction",
            referencedColumnName = "id_transaction"),
            inverseJoinColumns = @JoinColumn(name = "id_transaction_category",
                    referencedColumnName = "id_transactions_category"))
    private List<TransactionsTypeModel> typeModels;

    @Override
    public String toString() {
        return "TransactionsRepository{" +
                "id=" + id +
                ", transactionDate=" + transactionDate +
                ", amount=" + amount +
                ", toIdAccount=" + toIdAccount +
                ", fromIdAccount=" + fromIdAccount +
                ", typeModels=" + typeModels +
                '}';
    }
}

