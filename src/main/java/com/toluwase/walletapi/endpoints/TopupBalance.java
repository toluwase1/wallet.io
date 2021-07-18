package com.toluwase.walletapi.endpoints;

import com.toluwase.walletapi.models.User;
import com.toluwase.walletapi.requestEntities.LoginRequest;
import com.toluwase.walletapi.requestEntities.TopupRequest;
import com.toluwase.walletapi.responseEntities.LoginResponse;
import com.toluwase.walletapi.services.serviceImpl.AccountService;
import com.toluwase.walletapi.services.serviceImpl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/fund")
public class TopupBalance {
    @Autowired
    private AccountService accountService;


    @Autowired
    private AuthServiceImpl authService;

    @PostMapping("/topup")
    public ResponseEntity<LoginResponse> fundAccountByTopup (@RequestBody TopupRequest topupRequest, Long id) {
        LoginRequest loginRequest = new LoginRequest();
        accountService.topUpMyAccount(topupRequest, id);
        User user = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
        LoginResponse response = new LoginResponse(user.getEmail(),
                user.getPin(), user.getPassword(), user.getAccountNumber());
       return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
