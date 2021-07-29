package com.toluwase.walletapi.endpoints;

import com.toluwase.walletapi.models.Account;
import com.toluwase.walletapi.models.User;
import com.toluwase.walletapi.requestEntities.TransferRequest;
import com.toluwase.walletapi.responseEntities.LoginResponse;
import com.toluwase.walletapi.services.serviceImpl.AccountService;
import com.toluwase.walletapi.services.serviceImpl.AuthServiceImpl;
import com.toluwase.walletapi.services.serviceImpl.FindUserImpl;
import com.toluwase.walletapi.services.serviceImpl.VerificationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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


    @PostMapping("/send-by-mail")
    public ResponseEntity<LoginResponse> sendMoneyByEmail (@RequestBody TransferRequest transferRequest, Long id) {
        accountService.transferByAccount(transferRequest, id);
        LoginResponse response = new LoginResponse();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/account")
    public  ResponseEntity<LoginResponse> sendMoneyByAccountNumber (@RequestBody TransferRequest transferRequest, Long id) {
        accountService.transferByAccount(transferRequest, id);
        LoginResponse loginResponse =new LoginResponse();
        return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
    }

    @GetMapping("/{email}")
    public User findUserByEmail (String email) {
        User user = new User();
        user = findUser.getUserByEmail(email);
        return user;
    }


}
