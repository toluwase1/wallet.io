package com.toluwase.walletapi.services.serviceImpl;

import com.toluwase.walletapi.models.User;
import com.toluwase.walletapi.repositories.AccountRepository;
import com.toluwase.walletapi.repositories.AuthRepository;
import com.toluwase.walletapi.services.FindUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindUserImpl implements FindUser {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private AccountService accountService;

    @Override
    public User getUserByEmail(String email) {
        User user = new User();
        user = authRepository.findUserByEmail(email);
        return user;
    }


    @Override
    public User getUserByAccountNumber(String accountNumber) {
        User user = new User();
        user = accountRepository.findUserByAccountNumber(accountNumber);
        return user;
    }


}
