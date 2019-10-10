package com.app.wallet.utils.enums;

public enum WalletTypes {
    FOOD(0, "食物"),
    TRAFFIC(1, "交通"),
    ENTERTAINMENT(2, "娱乐");

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
