package com.app.wallet.repository;

import com.app.wallet.model.AppWalletAvailable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppWalletAvailableRepository extends JpaRepository<AppWalletAvailable, Long> {
    List<AppWalletAvailable> findAllByUserId(Long userId);
    AppWalletAvailable findByWalletTypeAndUserId(Integer walletType, Long userId);
}
