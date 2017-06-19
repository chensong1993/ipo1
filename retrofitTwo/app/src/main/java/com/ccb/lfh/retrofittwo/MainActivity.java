package com.ccb.lfh.retrofittwo;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ccb.lfh.retrofittwo.Adapter.MyAdapter;
import com.ccb.lfh.retrofittwo.Adapter.NewsAdapter;
import com.ccb.lfh.retrofittwo.Api.TngouApi;
import com.ccb.lfh.retrofittwo.Model.News;
import com.ccb.lfh.retrofittwo.Model.Root;
import com.ccb.lfh.retrofittwo.Model.Tangou;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.ccb.lfh.retrofittwo.R.id.text;
import static com.ccb.lfh.retrofittwo.R.id.tv_title;

public class MainActivity extends AppCompatActivity {
    private MyAdapter mAdapter;
     static Context context;
    //设缓存有效期为1天
    static final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    //查询网络的Cache-Control设置
    //(假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)
    static final String CACHE_CONTROL_NETWORK = "Cache-Control: public, max-age=3600";
    // 避免出现 HTTP 403 Forbidden，参考：http://stackoverflow.com/questions/13670692/403-forbidden-with-java-but-not-web-browser
    static final String AVOID_HTTP403_FORBIDDEN = "User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 指定缓存路径,缓存大小100Mb
        Cache cache = new Cache(new File(this.getCacheDir(), "HttpCache"),
                1024 * 1024 * 100);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().cache(cache)
                .retryOnConnectionFailure(true)
                .addInterceptor(sLoggingInterceptor)
                .addNetworkInterceptor(sRewriteCacheControlInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit=new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://www.tngou.net")
               .addConverterFactory(GsonConverterFactory.create())
                .build();
       TngouApi server=retrofit.create(TngouApi.class);
        Call<Root> call=server.getRoot("cook",0,1);
        ListView listView= (ListView) findViewById(R.id.lv_one);
        mAdapter=new MyAdapter(this,new ArrayList<Tangou>());
        listView.setAdapter(mAdapter);
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                List<Tangou> list=response.body().getTngou();
                mAdapter.addAll(list);
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Toast.makeText(MainActivity.this, "请求错误", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private static final Interceptor sLoggingInterceptor = new Interceptor() {

        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            final Request request = chain.request();
            Buffer requestBuffer = new Buffer();
            if (request.body() != null) {
                request.body().writeTo(requestBuffer);
            } else {
                Log.d("LogTAG", "request.body() == null");
            }
            //打印url信息
          //  Logr.w(request.url() + (request.body() != null ? "?" + _parseParams(request.body(), requestBuffer) : ""));
            final okhttp3.Response response = chain.proceed(request);

            return response;
        }
    };
    private static final Interceptor sRewriteCacheControlInterceptor = new Interceptor() {

        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetUtil.isNetworkAvailable(context)) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                Log.e("haha","no network");
            }
            okhttp3.Response originalResponse = chain.proceed(request);

            if (NetUtil.isNetworkAvailable(context)) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, " + CACHE_CONTROL_CACHE)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };
}
