package com.app.wallet.utils.enums;

public enum WalletTypes {
    COMMON(1l, "通用"),
    TRAVEL(2L, "旅游"),
    CHILDREN(3L, "孩子"),
    RESTAURANT(4L, "餐馆"),
    DRINK(5L, "饮料"),
    SNACKS(6L, "零食"),
    CLOTHES(7L, "衣服"),
    HOUSE(8L, "住房"),
    TICKET(9L, "车票"),
    GIFT(10L, "礼物"),
    SURPRISE(12L, "惊喜"),
    TRANSFER(13L, "转账", "items"),
    BORROW(14L, "借款", "items"),
    BORROW_B(15L, "还款", "items"),
    LEND(16L, "借给", "items"),
    LEND_B(17L, "已还", "items'");

    private Long id;
    private String dTypeName;
    private String type;
    private WalletTypes(Long i,String str,String type){
        this.id = i;
        this.dTypeName=str;
        this.type = type;
    }

    private WalletTypes(Long i,String str){
        this.id = i;
        this.dTypeName=str;
        this.type = "wallet";
    }

    public Long getId() {
        return id;
    }

    public String getdTypeName() {
        return dTypeName;
    }

    public String getType() {
        return type;
    }
}
