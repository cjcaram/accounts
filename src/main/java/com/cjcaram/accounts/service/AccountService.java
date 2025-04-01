package com.cjcaram.accounts.service;

import com.cjcaram.accounts.entity.Account;
import com.cjcaram.accounts.entity.ClientAccount;
import com.cjcaram.accounts.exception.ResourceNotFoundException;
import com.cjcaram.accounts.model.AccountDto;
import com.cjcaram.accounts.model.AccountType;
import com.cjcaram.accounts.model.ClientDto;
import com.cjcaram.accounts.repository.AccountRepository;
import com.cjcaram.accounts.repository.ClientAccountRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final ClientAccountRepository clientAccountRepository;
    private final ClientServiceProxy clientServiceProxy;
    private final ModelMapper modelMapper;

    public List<AccountDto> findAll() {
        return accountRepository.findAll().stream()
                .map(this::convertToDto)
                .toList();
    }

    public AccountDto getAccountById(Long id) {

        return accountRepository.findById(id)
                .map(account -> modelMapper.map(account, AccountDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + id));
    }

    public List<AccountDto> getAccountsByClientId(Long clientId) {
        return accountRepository.findByClients_ClientId(clientId)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    @Transactional
    public AccountDto saveAccount(@Valid AccountDto accountDto) {

        // we validate that clients exist
        Set<ClientDto> clients = clientServiceProxy.getClientsByIds(accountDto.getClientIds());
        if (clients.size() != accountDto.getClientIds().size()) {
            throw new ResourceNotFoundException("One or more clients not found.");
        }

        // save the new account
        Account account = modelMapper.map(accountDto, Account.class);
        Set<ClientAccount> clientAccounts = clients.stream()
                .map(clientDto -> {
                    ClientAccount clientAccount = new ClientAccount();
                    clientAccount.setClientId(clientDto.getId());
                    clientAccount.setAccount(account);
                    return clientAccount;
                }).collect(Collectors.toSet());
        account.setClients(clientAccounts);
        Account savedAccount = accountRepository.save(account);
        return convertToDto(savedAccount);
    }

    @Transactional
    public Account updateAccount(Long id, AccountDto accountDto) {
        return accountRepository.findById(id)
                .map(existingAccount -> {
                    existingAccount.setBalance(accountDto.getBalance());
                    existingAccount.setType(AccountType.valueOf(accountDto.getType()));
                    return accountRepository.save(existingAccount);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + id));
    }

    @Transactional
    public void disableAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + id));
        account.setActive(false);
        accountRepository.save(account);
    }

    private AccountDto convertToDto(Account account) {
        AccountDto accountDto = modelMapper.map(account, AccountDto.class);
        Set<ClientDto> clients = account.getClients().stream()
                .map(client -> modelMapper.map(client, ClientDto.class))
                .collect(Collectors.toSet());
        accountDto.setClients(clients);
        return accountDto;
    }
}
