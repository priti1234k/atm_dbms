package com.bank.atm.repositories.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "Bank")
@Data
public class Bank implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Branch")
    private String branch;

    @Column(name = "IFSC_Code", unique = true)
    private String ifscCode;

    @Column(name = "Created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "Updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;

}
