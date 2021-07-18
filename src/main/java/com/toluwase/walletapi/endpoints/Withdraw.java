package com.toluwase.walletapi.endpoints;

import com.toluwase.walletapi.requestEntities.TransferRequest;
import com.toluwase.walletapi.requestEntities.WithdrawRequest;
import com.toluwase.walletapi.responseEntities.LoginResponse;
import com.toluwase.walletapi.services.serviceImpl.AccountService;
import com.toluwase.walletapi.services.serviceImpl.AuthServiceImpl;
import com.toluwase.walletapi.services.serviceImpl.FindUserImpl;
import com.toluwase.walletapi.services.serviceImpl.VerificationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/withdraw")
public class Withdraw {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthServiceImpl authService;

    @Autowired
    private FindUserImpl findUser;

    @Autowired
    private VerificationServiceImpl verificationService;


    @PostMapping("/money")
    public ResponseEntity<LoginResponse> sendMoneyByEmail (@RequestBody TransferRequest transferRequest,  WithdrawRequest withdrawRequest,Long id) {
        accountService.withdrawMoney(withdrawRequest, id, transferRequest);
        LoginResponse response = new LoginResponse();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
