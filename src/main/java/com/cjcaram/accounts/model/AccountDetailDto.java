package com.cjcaram.accounts.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDetailDto {
    private String number;
    private String type;
    private BigDecimal balance;
    private Set<ClientDto> clients;
    private List<TransactionDto> transactions;
}
