package com.app.wallet.utils;

import com.app.wallet.model.DWalletType;
import com.app.wallet.utils.enums.WalletTypes;

import java.util.ArrayList;
import java.util.List;

public class WalletTypeUtil {

    public static List<DWalletType> getList(String filter){
        List<DWalletType> ls = new ArrayList<>();
        if(StringUtils.isNotEmpty(filter)){
            for(WalletTypes wt: WalletTypes.values()){
                if("wallet".equals(wt.getType())){
                    DWalletType dwt = new DWalletType();
                    dwt.setId(wt.getId());
                    dwt.setDTypeName(wt.getdTypeName());
                    ls.add(dwt);
                }
            }
        }else{
            for(WalletTypes wt: WalletTypes.values()){
                DWalletType dwt = new DWalletType();
                dwt.setId(wt.getId());
                dwt.setDTypeName(wt.getdTypeName());
                ls.add(dwt);
            }
        }
        return ls;
    }

    public static List<DWalletType> getList(){
        return getList("");
    }

}
