package com.app.wallet.controller;

import com.app.wallet.controller.request.WalletRequest;
import com.app.wallet.model.AppWallet;
import com.app.wallet.model.AppWalletAvailable;
import com.app.wallet.model.AppWalletItem;
import com.app.wallet.model.DWalletType;
import com.app.wallet.service.*;
import com.app.wallet.utils.DateUtils;
import com.app.wallet.utils.MessageUtil;
import com.app.wallet.utils.PageUtil;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    private AppWalletAvailableService walletAvailableService;
    @Autowired
    private DWalletTypeService walletTypeService;
    @Autowired
    private CheckDataService checkDataService;
    @Autowired
    private RedisTemplate<String, JsonObject> redisTemplate;

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public Map<String, Object> addWallet(@Valid AppWallet wallet, Principal user){
        Map<String, Object> map = new HashMap<>();
        checkDataService.checkBefore(map, user, redisTemplate, wallet);
        walletService.addAppWallet(map, wallet);
        AppWalletAvailable walletAvailable = AppWalletAvailable.builder()
                .walletId(wallet.getId()).walletName(wallet.getWalletName())
                .walletType(wallet.getWalletType()).userId(wallet.getUserId())
                .build();
        walletAvailableService.saveAppWalletAvailable(map,walletAvailable);
        return map;
    }

    @PostMapping("/getWalletById")
    @ResponseBody
    public Map<String, Object> getWalletById(Long id, Principal user){
        log.info("begin method getById and id :" + id);
        Map<String, Object> map = new HashMap<>();
        walletService.getAppWalletById(map, id);
        AppWallet wallet = (AppWallet)walletService.getProp(map);
        checkDataService.checkAfter(map, user, redisTemplate, wallet);
        return map;
    }

    @PostMapping("/getWallets")
    @ResponseBody
    public Map<String, Object> getWalletByUserId(Long userId, Principal user){
        Map<String, Object> map = new HashMap<>();
        checkDataService.checkBefore(map, user, redisTemplate, userId);
        return walletService.getAppWalletByUserId(map, userId);
    }

    @PostMapping("/addWalletItemAndReduce")
    @ResponseBody
    public Map<String, Object> addWalletItemAndReduce(AppWalletItem walletItem, Principal user){
        Map<String, Object> map = new HashMap<>();
        checkDataService.checkBefore(map, user, redisTemplate, walletItem);
        walletItem.setCreateTime(DateUtils.format("yyyyMMdd hhmmss", new Date()));
        walletItem.setUpdateTime(DateUtils.format("yyyyMMdd hhmmss", new Date()));
        walletItemService.saveOrUpdateAppWalletItem(map, walletItem);
        walletService.reduceWalletBalance(map, walletItem.getWalletId(), walletItem.getItemPrice());
        return map;
    }

    @PostMapping("/addWalletItemAndAdd")
    @ResponseBody
    public Map<String, Object> addWalletItemAndAdd(AppWalletItem walletItem, Principal user){
        Map<String, Object> map = new HashMap<>();
        checkDataService.checkBefore(map, user, redisTemplate, walletItem);
        walletItem.setCreateTime(DateUtils.format("yyyyMMdd hhmmss", new Date()));
        walletItem.setUpdateTime(DateUtils.format("yyyyMMdd hhmmss", new Date()));
        walletItemService.saveOrUpdateAppWalletItem(map, walletItem);
        walletService.addWalletBalance(map, walletItem.getWalletId(), walletItem.getItemPrice());
        return map;
    }

    @PostMapping("/addWalletType")
    @ResponseBody
    public Map<String, Object> addWalletType(DWalletType walletType, Principal user){
        Map<String, Object> map = new HashMap<>();
        checkDataService.checkBefore(map, user, redisTemplate, walletType);
        walletTypeService.saveWalletType(map, walletType);
        return map;
    }

    @PostMapping("getAllType")
    @ResponseBody
    public Map<String, Object> getAllType(Long userId, Principal user){
        Map<String, Object> map = new HashMap<>();
        checkDataService.checkBefore(map, user, redisTemplate, userId);
        walletTypeService.findAllTypeByUserId(map, userId);
        return map;
    }

    @PostMapping("getWalletType")
    @ResponseBody
    public Map<String, Object> getWalletType(Long userId, Principal user){
        Map<String, Object> map = new HashMap<>();
        checkDataService.checkBefore(map, user, redisTemplate, userId);
        walletTypeService.findWalletTypeByUserId(map, userId);
        return map;
    }

    @PostMapping("/getWalletItem")
    @ResponseBody
    public Map<String, Object> getWalletItem(Long id, Principal user) {
        Map<String, Object> map = new HashMap<>();
        walletItemService.findById(map, id);
        AppWalletItem walletItem = (AppWalletItem) walletItemService.getProp(map);
        checkDataService.checkAfter(map, user, redisTemplate, walletItem);
        return map;
    }

    @PostMapping("/getWalletItems")
    @ResponseBody
    public Map<String, Object> getWalletItems(AppWalletItem awi, PageUtil page, Principal user) {
        Map<String, Object> map = new HashMap<>();
        checkDataService.checkBefore(map, user, redisTemplate, awi);
        walletItemService.dynamicQuery(map, awi, page);
        return map;
    }

}
