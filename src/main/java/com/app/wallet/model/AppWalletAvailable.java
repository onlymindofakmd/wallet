package com.app.wallet.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="APP_WALLET_AVAILABLE")
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class AppWalletAvailable implements BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long walletId;
    private Long userId;
    private String walletName;
    private Integer walletType;
}
