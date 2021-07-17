package com.toluwase.walletapi.services.serviceImpl;

import com.toluwase.walletapi.models.Level;
import com.toluwase.walletapi.models.LevelOneInfo;
import com.toluwase.walletapi.models.User;
import com.toluwase.walletapi.repositories.AuthRepository;
import com.toluwase.walletapi.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private AccountService accountService;


    @Override
    public User login(String email, String password) {
      User user = authRepository.findByEmailAndPassword(email, password);
      return user;
    }

    @Override
    public User signup(LevelOneInfo levelOne) {
        User user = new User();
        user.setFirstName(levelOne.getFirstName());
        user.setLastName(levelOne.getLastName());
        user.setPhone(levelOne.getPhone());
        user.setEmail(levelOne.getEmail());
        user.setPassword(levelOne.getPassword());
        user.setPin(levelOne.getPin());
        user.setBalance(0d);
        String accountNumber = accountService.generateAccountNumber();
        while(authRepository.findUserByAccountNumber(accountNumber) != null){
            accountNumber = accountService.generateAccountNumber();
        }
        user.setAccountNumber(accountNumber);
        user.setLevels(Level.LEVEL_ONE);
        return authRepository.save(user);
    }
}
