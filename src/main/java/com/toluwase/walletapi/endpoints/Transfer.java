package com.toluwase.walletapi.endpoints;

import com.toluwase.walletapi.models.Account;
import com.toluwase.walletapi.models.User;
import com.toluwase.walletapi.services.serviceImpl.AccountService;
import com.toluwase.walletapi.services.serviceImpl.AuthServiceImpl;
import com.toluwase.walletapi.services.serviceImpl.FindUserImpl;
import com.toluwase.walletapi.services.serviceImpl.VerificationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/send")
public class Transfer {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthServiceImpl authService;

    @Autowired
    private FindUserImpl findUser;

    @Autowired
    private VerificationServiceImpl verificationService;

    @PostMapping("/level-one")
    public  void levelOneTransfer () {

    }

    @PostMapping("/level-two")
    public  void levelTwoTransfer () {

    }

    @PostMapping("/level-three")
    public  void levelThreeTransfer () {

    }

    @PostMapping("/send-by-mail")
    public  void sendMoneyByEmail () {

    }

    @GetMapping("/send-by-account")
    public  void sendMoneyByAccountNumber () {

    }

    @GetMapping("/{email}")
    public User findUserByEmail (String email) {
        User user = new User();
        user = findUser.getUserByEmail(email);
        return user;
    }

//    @GetMapping("/{account}")
//    public Account findUserByAccount (Account accountNumber) {
//
//    }
}
