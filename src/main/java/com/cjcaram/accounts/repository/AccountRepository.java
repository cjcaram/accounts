package com.cjcaram.accounts.repository;


import com.cjcaram.accounts.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByClients_ClientId(Long clientId);


}
