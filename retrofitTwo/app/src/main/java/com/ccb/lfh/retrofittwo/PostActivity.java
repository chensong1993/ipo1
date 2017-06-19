package com.ccb.lfh.retrofittwo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.ccb.lfh.retrofittwo.Adapter.RMyAdapter;
import com.ccb.lfh.retrofittwo.Api.TngouApi;
import com.ccb.lfh.retrofittwo.Model.Root;
import com.ccb.lfh.retrofittwo.Model.Tangou;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostActivity extends AppCompatActivity {
    private RMyAdapter myAdapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://www.tngou.net")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TngouApi api=retrofit.create(TngouApi.class);
        Call<Root> call=api.getRoot("cook",0,1);
        recyclerView= (RecyclerView) findViewById(R.id.cacheRv);
        myAdapter=new RMyAdapter(this,new ArrayList<Tangou>());
        recyclerView.setAdapter(myAdapter);
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                List<Tangou> list=response.body().getTngou();
                myAdapter.addAll(list);
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Toast.makeText(PostActivity.this, "请求错", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
