package com.ccb.lfh.retrofittwo.Api;

import com.ccb.lfh.retrofittwo.Model.NewList;
import com.ccb.lfh.retrofittwo.Model.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by admin on 2017/5/4.
 */

public interface NewsApi {
    @GET("/{chood}/index?key=a076603657a038e2e0779e459bef0a40")
    Call<NewList> getNews(@Path("chood") String chood );
}
