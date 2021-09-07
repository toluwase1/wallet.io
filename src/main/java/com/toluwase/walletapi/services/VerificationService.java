package com.toluwase.walletapi.services;

import com.toluwase.walletapi.models.LevelThreeInfo;
import com.toluwase.walletapi.models.LevelTwoInfo;

public interface VerificationService {
//    void LevelOneVerification(LevelOneInfo levelOneInfo, Long id);
    Boolean levelTwoVerification(LevelTwoInfo levelTwoInfo, Long id);
    String levelThreeVerification(LevelThreeInfo levelThreeInfo, Long id, LevelTwoInfo levelTwoInfo);
}
