package com.app.wallet.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.money.Money;

@Getter
@Setter
@ToString
public class WalletRequest {
    private String walletName;
    private String walletType;
    private Money balance;
    private int allowAdvance;
    private String description;
    private int dataShare;
}
