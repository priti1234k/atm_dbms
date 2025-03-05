package com.bank.atm.repositories.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "ATM")
@Data
public class Atm implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "ATMBranch")
    private String atmBranch;

    @Column(name = "ATMCode", unique = true)
    private String atmCode;

    @ManyToOne
    @JoinColumn(name = "Bank_Id", referencedColumnName = "Id")
    private Bank bank;

    @Column(name = "Balance")
    private Double balance;

    @Column(name = "Created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "Updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;

}
