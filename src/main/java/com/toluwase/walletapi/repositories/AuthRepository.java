package com.toluwase.walletapi.repositories;

import com.toluwase.walletapi.endpoints.Authentication;
import com.toluwase.walletapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository <User, Long> {
    User findByEmailAndPassword (String email, String password);
    User findUserByEmail(String email);
    User findUserByAccountNumber(String accountNumber);
    Optional<User> findById(Long id);
}
