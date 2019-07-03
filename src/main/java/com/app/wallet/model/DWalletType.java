package com.app.wallet.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="D_WALLET_TYPE")
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class DWalletType implements BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String dTypeName;
}
