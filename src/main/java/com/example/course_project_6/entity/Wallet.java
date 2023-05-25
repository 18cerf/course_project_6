package com.example.course_project_6.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "wallet")
public class Wallet {
    public Wallet() {
    }

    public Wallet(double balance) {
        this.balance = balance;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double balance;

    @PrePersist
    public void setDefaultValues() {
        if (balance == 0.0) {
            balance = 100.0; // устанавливаем значение по умолчанию
        }
    }
}
