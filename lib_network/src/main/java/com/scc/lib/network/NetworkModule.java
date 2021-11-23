package com.scc.lib.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scc.lib.network.api.IWanAndroidService;
import com.scc.lib.network.utils.NetworkSSL;
import com.scc.lib.network.utils.TrustManager;
import com.scc.lib.utils.MLog;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@InstallIn(SingletonComponent.class)
@Module
public class NetworkModule {
    private static int TIME_OUT = 30; //30秒超时断开连接
    @Provides
    @Singleton
    OkHttpClient providesOkHttpClient(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                try {
                    MLog.e("--network--", URLDecoder.decode(message, "utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    MLog.e("--network--", message);
                }
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .sslSocketFactory(new NetworkSSL(TrustManager.trustAllCert), TrustManager.trustAllCert)
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }
    @Singleton
    @Provides
    Retrofit providesRetrofit(OkHttpClient okHttpClient){
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://www.wanandroid.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
    @Singleton
    @Provides
    IWanAndroidService providesWanAndroidService(Retrofit retrofit){
        return retrofit.create(IWanAndroidService.class);
    }

}
