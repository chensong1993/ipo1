package com.ccb.lfh.retrofittwo.Api;

import com.ccb.lfh.retrofittwo.Model.Root;
import com.ccb.lfh.retrofittwo.Model.Tangou;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by admin on 2017/5/3.
 */

public interface  TngouApi {
    @GET("/api/{category}/list")
    Call<Root> getRoot(@Path("category") String category, @Query("id") int id, @Query("page") int page);
}
