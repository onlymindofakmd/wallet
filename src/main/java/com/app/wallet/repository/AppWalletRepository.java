package com.app.wallet.repository;

import com.app.wallet.model.AppWallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppWalletRepository extends JpaRepository<AppWallet, Long> {
    List<AppWallet> findAllByUserId(Long userId);
    AppWallet findByUserId(Long userId);
}
