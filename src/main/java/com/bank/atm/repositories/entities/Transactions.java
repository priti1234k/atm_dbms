package com.bank.atm.repositories.entities;

import com.bank.atm.models.TransactionType;
import com.bank.atm.models.converter.TransactionTypeConverter;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "Transaction")
@Data
public class Transactions implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Balance")
    private Double balance;

    @Column(name = "TransactionType")
    @Convert(converter = TransactionTypeConverter.class)
    private TransactionType transactionType;

    @JoinColumn(name = "Card_No", referencedColumnName = "Card_No")
    private Card card;

    @Column(name = "Created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "Updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;

}
