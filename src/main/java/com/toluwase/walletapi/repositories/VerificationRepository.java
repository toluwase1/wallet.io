package com.toluwase.walletapi.repositories;

import com.toluwase.walletapi.endpoints.Verification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationRepository extends JpaRepository<Verification, Long> {

}
