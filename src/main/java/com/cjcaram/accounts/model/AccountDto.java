package com.cjcaram.accounts.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    @Size(min = 3, max = 40, message = "Account number must be size > 3 and < 40")
    private String number;

    @NotBlank(message = "Account type is required, valid values = SAVINGS|CHECKING")
    private String type;

    private BigDecimal balance;

    private Set<Long> clientIds;

    private Set<ClientDto> clients;
}
