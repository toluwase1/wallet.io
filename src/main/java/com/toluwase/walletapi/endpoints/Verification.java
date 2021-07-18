package com.toluwase.walletapi.endpoints;

import com.toluwase.walletapi.models.LevelThreeInfo;
import com.toluwase.walletapi.models.LevelTwoInfo;
import com.toluwase.walletapi.models.User;
import com.toluwase.walletapi.requestEntities.LoginRequest;
import com.toluwase.walletapi.responseEntities.LoginResponse;
import com.toluwase.walletapi.services.serviceImpl.AccountService;
import com.toluwase.walletapi.services.serviceImpl.AuthServiceImpl;
import com.toluwase.walletapi.services.serviceImpl.VerificationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verify")
public class Verification {
    @Autowired
    AccountService accountService;

    @Autowired
    VerificationServiceImpl verificationService;

    @Autowired
    AuthServiceImpl authService;

    @PostMapping("/phone")
    public ResponseEntity<LoginResponse> verifyPhone (@RequestBody LevelTwoInfo levelTwoInfo, Long id) {
        verificationService.levelTwoVerification(levelTwoInfo, id);
        LoginRequest loginRequest = new LoginRequest();
        User user = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
        LoginResponse response = new LoginResponse(user.getEmail(),
                user.getPin(), user.getPassword(), user.getAccountNumber());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/bvn")
    public ResponseEntity<LoginResponse> verifyBVN (@RequestBody LevelThreeInfo levelThreeInfo, Long id, LevelTwoInfo levelTwoInfo) {
        verificationService.levelThreeVerification(levelThreeInfo, id, levelTwoInfo);
        LoginRequest loginRequest = new LoginRequest();
        User user = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
        LoginResponse response = new LoginResponse(user.getEmail(),
                user.getPin(), user.getPassword(), user.getAccountNumber());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
