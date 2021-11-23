package com.scc.lib.network.exception;

import org.jetbrains.annotations.NotNull;

import io.reactivex.functions.Consumer;

public abstract class ErrorConsumer implements Consumer<Throwable> {
    @Override
    public void accept(@NotNull Throwable throwable) throws Exception {
        //对异常进行处理
        ApiException exception;
        if (throwable instanceof ApiException) {
            exception = (ApiException) throwable;
        } else {
            exception = ApiException.handleException(throwable);
        }
        //调用error方法
        error(exception);
    }
    //使用时实现error方法即可。
    protected abstract void error(ApiException e);
}
