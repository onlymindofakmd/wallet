package com.app.wallet.service;

import com.app.wallet.model.AppWalletAvailable;
import com.app.wallet.repository.AppWalletAvailableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AppWalletAvailableService extends AppCommonService {
    @Autowired
    private AppWalletAvailableRepository jpa;

    public Map<String, Object> findAllByUserId(Map<String, Object> map, Long userId){
        if(!check(map)) return map;
        List<AppWalletAvailable> ls = jpa.findAllByUserId(userId);
        return retSuccess(map, ls);
    }

    public Map<String, Object> findByWalletTypeAndUserId(Map<String, Object> map, Integer walletType, Long userId){
        if(!check(map)) return map;
        AppWalletAvailable awa = jpa.findByWalletTypeAndUserId(walletType, userId);
        return retSuccess(map, awa);
    }

}
