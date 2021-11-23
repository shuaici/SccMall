package com.scc.lib.componentbase.service;

import com.alibaba.android.arouter.facade.template.IProvider;

public interface IAccountService extends IProvider {

    /**
     * 是否已经登录
     * @return
     */
    boolean isLogin();

    /**
     * 获取登录用户的 AccountId
     * @return
     */
    String getAccountId();
}