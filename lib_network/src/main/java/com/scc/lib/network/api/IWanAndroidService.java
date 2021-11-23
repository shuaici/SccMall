package com.scc.lib.network.api;

import com.scc.lib.network.bean.ResponseData;
import com.scc.lib.network.bean.HomeBanner;
import com.scc.lib.network.bean.RegisterData;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IWanAndroidService {
    @GET("banner/json")
    Observable<ResponseData<List<HomeBanner>>> homeBanner();

    @POST("user/register")
    @FormUrlEncoded
    Observable<ResponseData<RegisterData>> register(@FieldMap Map<String,String> map);

}
