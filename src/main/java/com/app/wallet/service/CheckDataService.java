package com.app.wallet.service;

import com.app.wallet.model.BaseModel;
import com.app.wallet.utils.MessageUtil;
import com.app.wallet.utils.RedisUtil;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Map;

@Service
@Slf4j
public class CheckDataService extends AppCommonService{

    public <T extends BaseModel> Map<String, Object> checkBefore(Map<String,Object> map, Principal user, T t, RedisTemplate<String, String> template) {
        return checkBefore(map, user, t.getUserId(), template);
    }

    public Map<String, Object> checkBefore(Map<String,Object> map, Principal user, Long userId, RedisTemplate<String, String> template) {
        if(!check(map)) return map;
        JsonObject jo = RedisUtil.getInstance().get(RedisUtil.CACHE, user.getName(), template);
        if(jo==null){
            return retFail(map, MessageUtil.SYS_ERROR);
        }
        if(userId != Long.parseLong((jo.get("id").toString()))){
            log.info("Wallet Id is {}" ,userId);
            log.info("User Id is {}" ,jo.get("id").toString());
            return retFail(map, MessageUtil.USER_WORN);
        }

        return map;
    }

    public <T extends BaseModel> Map<String, Object> checkAfter(Map<String, Object> map, Principal user, T t, RedisTemplate<String, String> template) {
        if(!check(map)) return map;
        if(t==null){
            return retFail(map, MessageUtil.USER_WORN);
        }
        return checkBefore(map, user, t, template);
    }
}
