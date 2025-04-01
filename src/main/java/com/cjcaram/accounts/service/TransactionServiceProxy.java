package com.cjcaram.accounts.service;

import com.cjcaram.accounts.model.ClientDto;
import com.cjcaram.accounts.model.TransactionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TransactionServiceProxy {

    private final RestTemplate restTemplate;

    // TODO: add logs
    @Value("${microservice.transaction.url}")
    private String transactionServiceUrl;

    public List<TransactionDto> getTransactionsByAccountId(Long accountId) {
        String url = transactionServiceUrl + "/transactions/account/" + accountId;
        ResponseEntity<TransactionDto[]> response = restTemplate.getForEntity(url,TransactionDto[].class);

        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }
}
