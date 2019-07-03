package com.app.wallet.service;

import com.app.wallet.model.DWalletType;
import com.app.wallet.repository.DWalletTypeRepository;
import com.app.wallet.utils.WalletTypeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DWalletTypeService extends AppCommonService {
    @Autowired
    private DWalletTypeRepository jpa;

    public Map<String, Object> saveWalletType(Map<String, Object> map, DWalletType type){
        if(!check(map)) return map;
        type = jpa.save(type);
        return retSuccess(map, type);
    }

    public Map<String, Object> findAllTypeByUserId(Map<String, Object> map, Long userId){
        if(!check(map)) return map;
        List<DWalletType> ls = WalletTypeUtil.getList();
        return retSuccess(map, ls);
    }

    public Map<String, Object> findWalletTypeByUserId(Map<String, Object> map, Long userId){
        if(!check(map)) return map;
        List<DWalletType> ls = WalletTypeUtil.getList("wallet");
        return retSuccess(map, ls);
    }

}
