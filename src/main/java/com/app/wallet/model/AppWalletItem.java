package com.app.wallet.model;

import com.app.wallet.utils.enums.ItemProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.joda.money.Money;

import javax.persistence.*;

@Entity
@Table(name="APP_WALLET_ITEM")
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class AppWalletItem implements BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyMinorAmount",
            parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY")})
    private Money itemPrice;
    private String itemName;
    private String itemDescription;
    private Integer itemType;
//    private String tags;
    private Long walletId;
    private Long userId;
    private Integer year;
    private Integer month;
    private Integer day;
    private String createTime;
    private String updateTime;
    @Enumerated
    private ItemProperty itemProperty;

}
