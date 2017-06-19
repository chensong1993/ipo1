package com.ccb.lfh.retrofittwo;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by admin on 2017/5/3.
 */

public interface Services {
    @GET("/")
    Call <String> getBaidu();
}
