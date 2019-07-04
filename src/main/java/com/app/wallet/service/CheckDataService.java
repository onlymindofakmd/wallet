package com.app.wallet.service;

import com.app.wallet.model.AppWallet;
import com.app.wallet.model.BaseModel;
import com.app.wallet.utils.MessageUtil;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Map;

@Service
@Slf4j
public class CheckDataService extends AppCommonService{
    private final String CACHE = "ZYF_APP_USER";

    public <T extends BaseModel> Map<String, Object> checkBefore(Map<String,Object> map, Principal user, RedisTemplate<String, JsonObject> redisTemplate, T t) {
        return checkBefore(map, user, redisTemplate, t.getUserId());
    }

    public <T extends BaseModel> Map<String, Object> checkBefore(Map<String,Object> map, Principal user, RedisTemplate<String, JsonObject> redisTemplate, Long userId) {
        if(!check(map)) return map;
        HashOperations<String, String, JsonObject> hashOperations = redisTemplate.opsForHash();
        if(redisTemplate.hasKey(CACHE)&&hashOperations.hasKey(CACHE,user.getName())){
            return retFail(map, MessageUtil.SYS_ERROR);
        }else{
            if(userId != Long.parseLong((hashOperations.get(CACHE,user.getName()).get("id").toString()))){
                log.info("Wallet Id is {}" ,userId);
                log.info("User Id is {]" ,hashOperations.get(CACHE,user.getName()).get("id").toString());
                return retFail(map, MessageUtil.USER_WORN);
            }
        }
        return map;
    }

    public <T extends BaseModel> Map<String, Object> checkAfter(Map<String, Object> map, Principal user, RedisTemplate<String, JsonObject> redisTemplate, T t) {
        if(!check(map)) return map;
        if(t==null){
            return retFail(map, MessageUtil.USER_WORN);
        }
        return checkBefore(map, user,redisTemplate, t);
    }
}
