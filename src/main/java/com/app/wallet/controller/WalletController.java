package com.app.wallet.controller;

import com.app.wallet.controller.request.WalletRequest;
import com.app.wallet.model.AppWallet;
import com.app.wallet.service.AppWalletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/wallet")
@Slf4j
public class WalletController {

    @Autowired
    private AppWalletService walletService;

    @PostMapping(path = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Map<String, Object> addWallet(@RequestBody AppWallet wallet){
        Map<String, Object> map = new HashMap<>();
        return walletService.addAppWallet(map, wallet);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Map<String, Object> getWalletById(@PathVariable("id") Long id){
        log.info("begin method getById and id :" + id);
        Map<String, Object> map = new HashMap<>();
        return walletService.getAppWalletById(map, id);
    }

}
