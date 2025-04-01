package com.cjcaram.accounts.controller;

import com.cjcaram.accounts.entity.Account;
import com.cjcaram.accounts.model.AccountDetailDto;
import com.cjcaram.accounts.model.AccountDto;
import com.cjcaram.accounts.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Account", description = "API used to manage account information")
@RestController
@RequestMapping("api/accounts")
@RequiredArgsConstructor
public class AccountController {
    
    private final AccountService accountService;

    @Operation(summary = "Create new account")
    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@Valid @RequestBody AccountDto account) {
        AccountDto newAccount = accountService.saveAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAccount);
    }

    @Operation(summary = "Get account by ID")
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable Long id) {
        AccountDto account = accountService.getAccountById(id);
        return ResponseEntity.ok(account);
    }

    // TODO: List should be post with SearchDto used as RequestBody
    @Operation(summary = "List all accounts")
    @GetMapping
    public ResponseEntity<List<AccountDto>> listAllAccounts() {
        List<AccountDto> accounts = accountService.findAll();
        return ResponseEntity.ok(accounts);
    }

    @Operation(summary = "Update account by ID")
    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @Valid @RequestBody AccountDto account) {
        Account updatedAccount = accountService.updateAccount(id, account);
        return ResponseEntity.ok(updatedAccount);
    }

    // TODO: PathMapping

    @Operation(summary = "Disable account by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> disableAccountById(@PathVariable Long id) {
        accountService.disableAccount(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get account by clientId")
    @GetMapping("/client/{id}")
    public ResponseEntity<List<AccountDto>> getClientsByIds(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccountsByClientId(id));
    }

    @GetMapping("/{accountId}/details")
    public ResponseEntity<AccountDetailDto> getAccountDetails(@PathVariable Long accountId) {
        AccountDetailDto accountDetails = accountService.getAccountDetails(accountId);
        return ResponseEntity.ok(accountDetails);
    }
}
