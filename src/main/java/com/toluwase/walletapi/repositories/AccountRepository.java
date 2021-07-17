package com.toluwase.walletapi.repositories;

import com.toluwase.walletapi.models.Account;
import com.toluwase.walletapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<User, Account> {
//    Account findAccountByAccountNumber();
    User findUserByAccountNumber(String account);
}
