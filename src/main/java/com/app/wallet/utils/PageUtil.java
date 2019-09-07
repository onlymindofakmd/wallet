package com.app.wallet.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

@Getter
@Setter
public class PageUtil implements Serializable {
    private int page;
    private int perPage;
    private String orderType;
    private String orderStr;

    public PageRequest getPage(){
        if(StringUtils.isEmpty(orderStr)){
            Sort sort = Sort.by(Sort.Direction.DESC, "id");
            return PageRequest.of(page, perPage, sort);
        }
        return PageRequest.of(page, perPage, Sort.by(Sort.Direction.fromString(orderType), orderStr));
    }


}
