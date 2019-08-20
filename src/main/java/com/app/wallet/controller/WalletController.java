package com.app.wallet.controller;

import com.app.wallet.model.AppWallet;
import com.app.wallet.model.AppWalletItem;
import com.app.wallet.service.*;
import com.app.wallet.utils.DateUtils;
import com.app.wallet.utils.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
@Slf4j
public class WalletController {
    @Autowired
    private AppWalletService walletService;
    @Autowired
    private AppWalletItemService walletItemService;
    @Autowired
    private CheckDataService checkDataService;

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public Map<String, Object> addWallet(@Valid AppWallet wallet, Principal user){
        Map<String, Object> map = new HashMap<>();
        checkDataService.checkBefore(map, user, wallet);
        walletService.addAppWallet(map, wallet);
        return map;
    }

    @PostMapping("/getWalletById")
    @ResponseBody
    public Map<String, Object> getWalletById(Long id, Principal user){
        log.info("begin method getById and id :" + id);
        Map<String, Object> map = new HashMap<>();
        walletService.getAppWalletById(map, id);
        AppWallet wallet = (AppWallet)walletService.getProp(map);
        checkDataService.checkAfter(map, user, wallet);
        return map;
    }

    @PostMapping("/getWallets")
    @ResponseBody
    public Map<String, Object> getWalletByUserId(Long userId, Principal user){
        Map<String, Object> map = new HashMap<>();
        checkDataService.checkBefore(map, user, userId);
        return walletService.getAppWalletByUserId(map, userId);
    }

    @PostMapping("/addWalletItem")
    @ResponseBody
    @Transactional
    public Map<String, Object> addWalletItem(AppWalletItem walletItem, Principal user){
        Map<String, Object> map = new HashMap<>();
        checkDataService.checkBefore(map, user, walletItem);
        walletItem.setCreateTime(DateUtils.format("yyyyMMdd hhmmss", new Date()));
        walletItem.setUpdateTime(DateUtils.format("yyyyMMdd hhmmss", new Date()));
        walletItemService.saveOrUpdateAppWalletItem(map, walletItem);
        if (walletItemService.addItem(walletItem)){
            walletService.addWalletBalance(map, walletItem.getWalletId(), walletItem.getItemPrice());
        }else
            walletService.reduceWalletBalance(map, walletItem.getWalletId(), walletItem.getItemPrice());

        walletItemService.checkTransaction(map);
        return map;
    }

    @PostMapping("/getWalletItem")
    @ResponseBody
    public Map<String, Object> getWalletItem(Long id, Principal user) {
        Map<String, Object> map = new HashMap<>();
        walletItemService.findById(map, id);
        AppWalletItem walletItem = (AppWalletItem) walletItemService.getProp(map);
        checkDataService.checkAfter(map, user, walletItem);
        return map;
    }

    @PostMapping("/getWalletItems")
    @ResponseBody
    public Map<String, Object> getWalletItems(AppWalletItem awi, PageUtil page, Principal user) {
        Map<String, Object> map = new HashMap<>();
        checkDataService.checkBefore(map, user, awi);
        walletItemService.dynamicQuery(map, awi, page);
        return map;
    }

}
