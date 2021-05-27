package com.example.transfer.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransferDto {
    @NotEmpty(message = "fromAccount cannot be empty")
    private String fromAccount;
    @NotEmpty(message = "toAccount cannot be empty")
    private String toAccount;
    @Min(value = 1, message = "amount cannot be less than 1")
    private long amount;
}
