package com.toluwase.walletapi.services.serviceImpl;

import com.toluwase.walletapi.models.Level;
import com.toluwase.walletapi.models.User;
import com.toluwase.walletapi.repositories.AuthRepository;
import com.toluwase.walletapi.requestEntities.TopupRequest;
import com.toluwase.walletapi.requestEntities.TransferRequest;
import com.toluwase.walletapi.requestEntities.WithdrawRequest;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AccountService {
    private AuthRepository authRepository;
    public String generateAccountNumber(){
//        ArrayList<Integer> list = new ArrayList<>();
//        for (int i=1; i<100000; i++) {
//            list.add(i);
//        }
//        Collections.shuffle(list);
//        String accountNumber = null;
//        for (int i=0; i<1; i++) {
//            accountNumber = ("00000"+list.get(i));
//            return accountNumber;
//        }
//        return accountNumber;
        String [] num = {"0","1","2","3","4","5","6","7","8","9"};
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < 10; i++) {
            int n = random.nextInt(num.length);
            sb.append(num[n]);
        }
        return sb.toString();
    }

    public String transferByAccount(TransferRequest transferRequest, Long id){
        User user = authRepository.findById(id).get();
        User receivingUserAccount = authRepository.findUserByAccountNumber(transferRequest.getDestinationAccount());
        if(!transferRequest.getPin().equals(user.getPin())){
            return "Transfer failed as pin is incorrect";
        }
        if(receivingUserAccount == null){
            return "No user with account no: " +transferRequest.getDestinationAccount() +" found";
        }
        if(user.getBalance() > transferRequest.getTransferAmount()){
            return "Insufficient funds";
        }
        user.setBalance(user.getBalance() - transferRequest.getTransferAmount());
        receivingUserAccount.setBalance(receivingUserAccount.getBalance() + transferRequest.getTransferAmount());
        return "Transfer of " +transferRequest.getTransferAmount() +" was successful";
    }

    public String withdrawMoney(WithdrawRequest withdrawRequest, Long id) {
        User user = authRepository.findById(id).get();
        Double withdrawalRequest = withdrawRequest.getWithDrawAmount();
        Double balance = user.getBalance()-withdrawalRequest;
        //check pin
        if (!user.getPin().equals(withdrawRequest.getPin())) {
            return "Transfer failed as pin is incorrect";
        }
        //check balance
        if (!(withdrawRequest.getWithDrawAmount()<= user.getBalance())) {
            return "Insufficient balance";
        }
        //execute remove balance to required accounts
        user.setBalance(balance);
        return "Your withdraw Request of " + withdrawalRequest+"is successful." +
                " Your new account balance is "+ balance;
    }

    public String transferByEmail (TransferRequest transferRequest, Long id) {
        User user = authRepository.findById(id).get();
        User receivingUser = authRepository.findUserByEmail(transferRequest.getEmail());
        //check if email exist
        if (receivingUser == null){
            return "User with email "+transferRequest.getEmail()+ "does not exist";
        }
        //check if pin is correct
        if (!user.getPin().equals(transferRequest.getPin())) {
            return "Transfer failed as pin is incorrect";
        }
        
        //check balance
        if (transferRequest.getTransferAmount()>user.getBalance()) {
            return "Insufficient funds";
        }
        WithdrawRequest withdrawRequest = new WithdrawRequest();
        setsTransferAndWithdrawalLimit(transferRequest, withdrawRequest, id);
        //add to balance
        Double balance = user.getBalance()- transferRequest.getTransferAmount();
        user.setBalance(balance);

       return "Your new account balance is " + balance;
    }

        public String setsTransferAndWithdrawalLimit (TransferRequest transferRequest, WithdrawRequest withdrawRequest, Long id) {
            User user = authRepository.findById(id).get();
            if (user.getLevels()== Level.LEVEL_ONE) {
                if (transferRequest.getTransferAmount()>10_000d) {
                    return "You have exceeded transfer limit for your level";
                }
                if (withdrawRequest.getWithDrawAmount()>2_000d) {
                    return "You have exceeded withdrawal limit for your level";
                }
            }

            if (user.getLevels()== Level.LEVEL_TWO) {
                if (transferRequest.getTransferAmount()>50_000d) {
                    return "You have exceeded transfer limit for your level";
                }
                if (withdrawRequest.getWithDrawAmount()>20_000d) {
                    return "You have exceeded withdrawal limit for your level";
                }
            }

            if (user.getLevels()== Level.LEVEL_THREE) {
                if (transferRequest.getTransferAmount()>9_999_999_999_9999d) {
                    return "You have exceeded transfer limit for your level";
                }
                if (withdrawRequest.getWithDrawAmount()>1_000_000_000_000d) {
                    return "excessive withdrawal";
                }
            }
            return "Withdrawal successful";
        }

    public String topUpMyAccount (TopupRequest topupRequest, Long id) {
        User user = authRepository.findById(id).get();
        Double topUpAmount = topupRequest.getTopupAmount();
        Double newBalance = topUpAmount+ user.getBalance();
        user.setBalance(newBalance);
        return "your new balance is " + newBalance;
    }




}