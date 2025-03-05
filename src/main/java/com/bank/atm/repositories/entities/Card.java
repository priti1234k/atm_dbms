package com.bank.atm.repositories.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "Card")
@Data
public class Card implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Customer_Id", referencedColumnName = "Id")
    private Customer customer;

    @Column(name = "Card_No", unique = true)
    private String cardNo;

    @Column(name = "Pin")
    private String pin;

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
