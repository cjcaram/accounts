package com.cjcaram.accounts.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    @Size(min = 3, max = 40, message = "Account number must be size > 3 and < 40")
    private String number;

    @NotBlank(message = "Account type is required")
    private AccountType type;

    private BigDecimal balance;

    private Set<Long> clients;
}
