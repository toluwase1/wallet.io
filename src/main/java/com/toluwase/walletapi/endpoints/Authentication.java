package com.toluwase.walletapi.endpoints;

import com.toluwase.walletapi.models.LevelOneInfo;
import com.toluwase.walletapi.models.User;
import com.toluwase.walletapi.requestEntities.LoginRequest;
import com.toluwase.walletapi.responseEntities.LoginResponse;
import com.toluwase.walletapi.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class Authentication {

        @Autowired
        AuthService authService;

        @PostMapping("/signup")
        public ResponseEntity<User> signup (@RequestBody LevelOneInfo levelOneInfo) {
            User user = authService.signup(levelOneInfo);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }

        @PostMapping("/login")
        public ResponseEntity<?> login (@RequestBody LoginRequest loginRequest) {
            System.out.println(loginRequest.getEmail());
            User user = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
            if(user == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid credentials");
            }
            LoginResponse response = new LoginResponse(user.getEmail(),
                    user.getPin(), user.getPassword(), user.getAccountNumber());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

}
