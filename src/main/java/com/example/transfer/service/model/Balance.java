package com.example.transfer.service.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@Entity(name = "balance")
@NoArgsConstructor
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(unique = true, nullable = false)
    private String accountId;
    @Column(nullable = false)
    private long balance;

    public Balance(String name) {
        this.name = name;
        this.accountId = RandomStringUtils.randomNumeric(10);
        this.balance = 0;
    }
}
