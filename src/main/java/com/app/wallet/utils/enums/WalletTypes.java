package com.app.wallet.utils.enums;

public enum WalletTypes {
    COMMON(1, "通用"),
    TRAVEL(2, "旅游"),
    CHILDREN(3, "孩子"),
    RESTAURANT(4, "餐馆"),
    DRINK(5, "饮料"),
    SNACKS(6, "零食"),
    CLOTHES(7, "衣服"),
    HOUSE(8, "住房"),
    TICKET(9, "车票"),
    GIFT(10, "礼物"),
    SURPRISE(12, "惊喜"),
    TRANSFER(13, "转账"),
    BORROW(14, "借款"),
    BORROW_B(15, "还款"),
    LEND(16, "借给"),
    LEND_B(17, "已还");

    private Integer id;
    private String dTypeName;

    private WalletTypes(Integer i,String str){
        this.id = i;
        this.dTypeName=str;
    }

    public Integer getId() {
        return id;
    }

    public String getdTypeName() {
        return dTypeName;
    }
}
