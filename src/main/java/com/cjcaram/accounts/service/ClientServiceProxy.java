package com.cjcaram.accounts.service;

import com.cjcaram.accounts.model.ClientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ClientServiceProxy {

    private final RestTemplate restTemplate;

    @Value("${microservice.client.url}")
    private String clientServiceUrl;

    // TODO: add logs
    public Set<ClientDto> getClientsByIds(Set<Long> clientIds) {
        String url = clientServiceUrl + "/clients/by-ids";
        ResponseEntity<Set<ClientDto>> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(clientIds),
                new ParameterizedTypeReference<>() {}
        );

        return response.getBody();
    }
}
