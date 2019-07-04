package com.app.wallet.service;

import com.app.wallet.model.AppWalletItem;
import com.app.wallet.repository.AppWalletItemRepository;
import com.app.wallet.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AppWalletItemService extends AppCommonService {
    @Autowired
    private AppWalletItemRepository jpa;

    public Map<String, Object> findById(Map<String, Object> map, Long id){
        if(!check(map)) return map;
        AppWalletItem awi = jpa.findById(id).get();
        return retSuccess(map, awi);
    }

    public Map<String, Object> saveOrUpdateAppWalletItem(Map<String, Object> map, AppWalletItem walletItem){
        if(!check(map)) return map;
        walletItem  = jpa.save(walletItem);
        return retSuccess(map, walletItem);
    }

    public Map<String, Object> dynamicQuery(Map<String ,Object> map, AppWalletItem awi, PageUtil pageUtil){
        if(!check(map)) return map;
        Page<AppWalletItem> page = getModels(jpa, awi, pageUtil.getPage());
        return retSuccess(map, page);
    }

}
