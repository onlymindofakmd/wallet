package com.app.wallet.service;

import com.app.wallet.model.AppWallet;
import com.app.wallet.repository.AppWalletRepository;
import com.app.wallet.utils.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class AppWalletService extends AppCommonService {
    @Autowired
    private AppWalletRepository jpa;

    public Map<String, Object> addAppWallet(Map<String, Object> map, AppWallet wallet){
        checkExist(map, wallet);
        if(!check(map)) return map;
        wallet.setIsValid(0);
        wallet = jpa.save(wallet);
        return retSuccess(map, wallet);
    }

    public Map<String, Object> checkExist(Map<String, Object> map, AppWallet wallet) {
        if(!check(map)) return map;
        AppWallet temp = jpa.findByUserId(wallet.getUserId());
        if(temp==null){
            return map;
        }else{
            return retFail(map, MessageUtil.WALLET_TYPE_EXIST);
        }
    }

    public Map<String, Object> getAppWalletById(Map<String, Object> map, Long id){
        if(!check(map)) return map;
        AppWallet aw = jpa.findById(id).get();
        return retSuccess(map, aw);
    }

    public Map<String, Object> updateAppWallet(Map<String, Object> map, AppWallet wallet){
        if(!check(map)) return map;
        AppWallet aw = jpa.save(wallet);
        return retSuccess(map, aw);
    }

    public Map<String, Object> reduceWalletBalance(Map<String, Object> map, Long walletId, Money itemPrice){
        if(!check(map)) return map;
        AppWallet aw = jpa.getOne(walletId);
        aw.setBalance(aw.getBalance().minus(itemPrice));
        jpa.save(aw);
        return retSuccess(map, aw);
    }

    public Map<String, Object> addWalletBalance(Map<String, Object> map, Long walletId, Money itemPrice){
        if(!check(map)) return map;
        AppWallet aw = jpa.getOne(walletId);
        aw.setBalance(aw.getBalance().plus(itemPrice));
        jpa.save(aw);
        return retSuccess(map, aw);
    }

    public Map<String, Object> getAppWalletByUserId(Map<String, Object> map, Long userId) {
        if(!check(map)) return map;
        List<AppWallet> ls = jpa.findAllByUserId(userId);
        return retSuccess(map, ls);
    }


}
