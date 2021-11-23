package com.scc.module.login.serviceimpl;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.scc.lib.componentbase.service.IAccountService;
import com.scc.lib.utils.MLog;

//实现接口
@Route(path = "/login/AccountServiceImpl")
public class AccountServiceImpl implements IAccountService {
    @Override
    public boolean isLogin() {
        MLog.e("AccountServiceImpl.isLogin");
        return true;
    }

    @Override
    public String getAccountId() {
        MLog.e("AccountServiceImpl.getAccountId");
        return "1000";
    }

    @Override
    public void init(Context context) {

    }
}
