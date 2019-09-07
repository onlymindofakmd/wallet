package com.app.wallet.utils.enums;

public enum WalletTypes {
    TRANSFER(0, "存款"),
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
    BORROW(13, "借入"),
    BORROW_B(14, "还款"),
    LEND(15, "借出"),
    LEND_B(16, "已还");

    private Integer id;
    private String dTypeName;

    WalletTypes(Integer i,String str){
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
