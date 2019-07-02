package com.app.wallet.repository;

import com.app.wallet.model.AppWallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppWalletRepository extends JpaRepository<AppWallet, Long> {

}
