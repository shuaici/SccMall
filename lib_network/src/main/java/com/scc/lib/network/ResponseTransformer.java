package com.scc.lib.network;


import com.scc.lib.network.bean.ResponseData;
import com.scc.lib.network.exception.ApiException;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ResponseTransformer<T> implements ObservableTransformer<ResponseData<T>, T> {
    public ResponseTransformer() {
    }

    public static <R> ResponseTransformer<R> obtain() {
        return new ResponseTransformer<>();
    }

    @NotNull
    @Override
    public ObservableSource<T> apply(@NotNull Observable<ResponseData<T>> upstream) {
        return upstream.onErrorResumeNext(new Function<Throwable, ObservableSource<? extends ResponseData<T>>>() {
            @Override
            public ObservableSource<? extends ResponseData<T>> apply(@NotNull Throwable throwable) throws Exception {
                return Observable.error(ApiException.handleException(throwable));
            }
        }).flatMap(new Function<ResponseData<T>, ObservableSource<T>>() {
            @Override
            public ObservableSource<T> apply(@NotNull ResponseData<T> responseData) throws Exception {
                //请求成功，开始处理你的逻辑吧
                if (0 == responseData.getErrorCode()) {
                    return Observable.just(responseData.getData());
                }
                //请求异常
                return Observable.error(new ApiException(responseData.getErrorCode(), responseData.getErrorMsg()));
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}

