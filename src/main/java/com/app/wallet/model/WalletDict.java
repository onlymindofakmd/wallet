package com.app.wallet.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class WalletDict {
    private Integer code;
    private String name;
}
