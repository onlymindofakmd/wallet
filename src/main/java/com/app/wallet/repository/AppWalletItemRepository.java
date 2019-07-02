package com.app.wallet.repository;

import com.app.wallet.model.AppWalletItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AppWalletItemRepository extends JpaRepository<AppWalletItem, Long>, JpaSpecificationExecutor<AppWalletItem> {

}
