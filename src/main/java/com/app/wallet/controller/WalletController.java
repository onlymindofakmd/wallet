package com.app.wallet.controller;

import com.app.wallet.controller.request.WalletRequest;
import com.app.wallet.model.AppWallet;
import com.app.wallet.model.AppWalletAvailable;
import com.app.wallet.model.AppWalletItem;
import com.app.wallet.model.DWalletType;
import com.app.wallet.service.AppWalletAvailableService;
import com.app.wallet.service.AppWalletItemService;
import com.app.wallet.service.AppWalletService;
import com.app.wallet.service.DWalletTypeService;
import com.app.wallet.utils.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(path = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Map<String, Object> addWallet(@RequestBody AppWallet wallet){
        Map<String, Object> map = new HashMap<>();
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
    public Map<String, Object> getWalletById(Long id){
        log.info("begin method getById and id :" + id);
        Map<String, Object> map = new HashMap<>();
        return walletService.getAppWalletById(map, id);
    }

    @PostMapping("/getWallets")
    @ResponseBody
    public Map<String, Object> getWalletByUserId(Long userId){
        Map<String, Object> map = new HashMap<>();
        return walletService.getAppWalletByUserId(map, userId);
    }

    @PostMapping("/addWalletItemAndReduce")
    @ResponseBody
    public Map<String, Object> addWalletItemAndReduce(AppWalletItem walletItem){
        Map<String, Object> map = new HashMap<>();
        walletItemService.saveOrUpdateAppWalletItem(map, walletItem);
        walletService.reduceWalletBalance(map, walletItem.getWalletId(), walletItem.getItemPrice());
        return map;
    }

    @PostMapping("/addWalletItemAndAdd")
    @ResponseBody
    public Map<String, Object> addWalletItemAndAdd(AppWalletItem walletItem){
        Map<String, Object> map = new HashMap<>();
        walletItemService.saveOrUpdateAppWalletItem(map, walletItem);
        walletService.addWalletBalance(map, walletItem.getWalletId(), walletItem.getItemPrice());
        return map;
    }

    @PostMapping("/addWalletType")
    @ResponseBody
    public Map<String, Object> addWalletType(DWalletType walletType){
        Map<String, Object> map = new HashMap<>();
        walletTypeService.saveWalletType(map, walletType);
        return map;
    }

    @PostMapping("getAllType")
    @ResponseBody
    public Map<String, Object> getAllType(Long userId){
        Map<String, Object> map = new HashMap<>();
        walletTypeService.findAllTypeByUserId(map, userId);
        return map;
    }

    @PostMapping("getWalletType")
    @ResponseBody
    public Map<String, Object> getWalletType(Long userId){
        Map<String, Object> map = new HashMap<>();
        walletTypeService.findWalletTypeByUserId(map, userId);
        return map;
    }

    @PostMapping("/getWalletItem")
    @ResponseBody
    public Map<String, Object> getWalletItem(Long id) {
        Map<String, Object> map = new HashMap<>();
        walletItemService.findById(map, id);
        return map;
    }

    @PostMapping("/getWalletItems")
    @ResponseBody
    public Map<String, Object> getWalletItems(AppWalletItem awi, PageUtil page) {
        Map<String, Object> map = new HashMap<>();
        walletItemService.dynamicQuery(map, awi, page);
        return map;
    }

}
