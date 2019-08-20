package com.app.wallet.utils;

import com.app.wallet.model.BaseModel;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisUtil {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    private static RedisUtil instance;

    public static RedisUtil getInstance() {
        if(instance == null) {
            instance = new RedisUtil();
        }
        return instance;
    }

    public void add(BaseModel o, String key, String key2) {

    }

    public JsonObject get(String key, String key2){
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        String result = hashOperations.get(key,key2);
        if (result == null) {
            return null;
        }
        JsonObject jo = new JsonParser().parse(result).getAsJsonObject();
        return jo;
    }

}
