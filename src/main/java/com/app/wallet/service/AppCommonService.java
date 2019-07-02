package com.app.wallet.service;

import com.app.wallet.model.AppWalletItem;
import com.app.wallet.model.BaseModel;
import com.app.wallet.utils.MessageUtil;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AppCommonService {


    public boolean check(Map<String, Object> map){
        if(map.get("success")==null||(int)map.get("success")==0) return true;
        return false;
    }

    public Map<String, Object> retSuccess(Map<String, Object> map, Object obj){
        map.put("success", 0);
        map.put("msg", MessageUtil.SUCCESS_MSG);
        map.put("data", obj);
        return map;
    }

    public Map<String, Object> retSuccess(Map<String, Object> map, Object obj, String key){
        map.put("success", 0);
        map.put("msg", MessageUtil.SUCCESS_MSG);
        map.put("data", obj);
        return map;
    }

    public Map<String, Object> retFail(Map<String, Object> map, String msg){
        map.put("success", 1);
        map.put("msg", msg);
        return map;
    }

    public Map<String, Object> retFail(Map<String, Object> map){
        return retFail(map, MessageUtil.FAIL_MSG);
    }

    public Object getProp(Map<String,Object> map){
        return  getProp(map, "data");
    }

    public Object getProp(Map<String,Object> map, String key){
        Object o = map.get(key);
        if(o==null){
            Map<String,Object> temp = (Map<String, Object>) map.get("data");
            if(temp==null){
                return null;
            }
            return temp.get(key);
        }
        return o;
    }

    public <T extends BaseModel> List<T> getModels(JpaSpecificationExecutor jpa, Map<String, Object> paraMap, Class<T> classz){
        Specification<AppWalletItem> querySpecifi = new Specification<AppWalletItem>() {
            @Override
            public Predicate toPredicate(Root<AppWalletItem> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                paraMap.forEach((str,obj)->{
                    if(obj!=null){
                        String[] strs = str.split(",");
                        String s = strs[0];String t = strs[1];
                        if(obj instanceof Number){
                            predicates.add(cb.greaterThan(root.get(str).as(String.class),(String)obj));
                            Expression<Number> ex = root.get(s).as(Number.class);
                            if("eq".equals(t)){
                                predicates.add(cb.equal(ex, obj));
                            } else if("gt".equals(t)){
                                predicates.add(cb.gt(ex,(Number) obj));
                            } else if("ge".equals(t)){
                                predicates.add(cb.ge(ex,(Number) obj));
                            } else if("lt".equals(t)){
                                predicates.add(cb.lt(ex,(Number) obj));
                            } else if("le".equals(t)){
                                predicates.add(cb.le(ex,(Number) obj));
                            }
                        }else{
                            if("eq".equals(t))
                                predicates.add(cb.equal(root.get(str).as(String.class),obj));
                            else{
                                predicates.add(cb.like(root.get(str).as(String.class),obj.toString()));
                            }
                        }
                    }
                });
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }


        };
        return jpa.findAll(querySpecifi);
    }

    public <T extends BaseModel> List<T> getModels(JpaRepository jpa, T t){
        Example<T> e = Example.of(t);
        return jpa.findAll(e);
    }

    public <T extends BaseModel> Page<T> getModels(JpaRepository jpa, T t, Pageable page){
        Example<T> e = Example.of(t);
        return jpa.findAll(e,page);
    }

}
