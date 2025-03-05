package com.bank.atm.repositories.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "Admin")
@Data
public class Admin implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Username", unique = true)
    private String userName;

    @Column(name = "Password")
    private String password;

    @Column(name = "Created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "Updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;

}
