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
    private int size;
    private String orderType;
    private String orderStr;

    public PageRequest getPage(){
        return PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(orderType), orderStr));
    }


}
