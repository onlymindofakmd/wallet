package com.app.wallet.service;

import com.app.wallet.model.BaseModel;
import com.app.wallet.utils.MessageUtil;
import com.app.wallet.utils.RedisUtil;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Map;

@Service
@Slf4j
public class CheckDataService extends AppCommonService{
    private final String CACHE = "ZYF_APP_USER";

    public <T extends BaseModel> Map<String, Object> checkBefore(Map<String,Object> map, Principal user, T t) {
        return checkBefore(map, user, t.getUserId());
    }

    public Map<String, Object> checkBefore(Map<String,Object> map, Principal user, Long userId) {
        if(!check(map)) return map;
        JsonObject jo = RedisUtil.getInstance().get(CACHE, user.getName());
        if(jo==null){
            return retFail(map, MessageUtil.SYS_ERROR);
        }
        if(userId != Long.parseLong((jo.get("id").toString()))){
            log.info("Wallet Id is {}" ,userId);
            log.info("User Id is {]" ,jo.get("id").toString());
            return retFail(map, MessageUtil.USER_WORN);
        }

        return map;
    }

    public <T extends BaseModel> Map<String, Object> checkAfter(Map<String, Object> map, Principal user, T t) {
        if(!check(map)) return map;
        if(t==null){
            return retFail(map, MessageUtil.USER_WORN);
        }
        return checkBefore(map, user, t);
    }
}
