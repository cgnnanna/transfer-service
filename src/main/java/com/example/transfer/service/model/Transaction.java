package com.example.transfer.service.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    private String reference;
    private String accountId;
    private long amount;
    private Type type;

    public Transaction(String accountId, long amount, Type type) {
        this.reference = RandomStringUtils.randomAlphabetic(15);
        this.accountId = accountId;
        this.amount = amount;
        this.type = type;
    }

    public enum Type{
        CREDIT,
        DEBIT
    }
}
