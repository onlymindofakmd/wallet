package com.app.wallet.model;

import com.app.wallet.utils.enums.AdvanceEnum;
import com.app.wallet.utils.enums.ShareEnum;
import com.app.wallet.utils.enums.WalletProperty;
import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.money.Money;

import javax.persistence.*;

@Entity
@Table(name = "APP_WALLET")
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class AppWallet implements BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String walletName;
    private Long walletType;
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyMinorAmount",
            parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY")})
    private Money balance;
    @Enumerated
    private AdvanceEnum allowAdvance;
    private Integer isValid;
    private String description;
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyMinorAmount",
            parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY")})
    private Money autoConfigScale;
    private Integer autoConfigType;
    @Enumerated
    private ShareEnum dataShare;
    @Enumerated
    private WalletProperty walletProperty;

}
