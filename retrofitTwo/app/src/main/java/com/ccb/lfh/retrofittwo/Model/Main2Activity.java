package com.ccb.lfh.retrofittwo.Model;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ccb.lfh.retrofittwo.R;

public class Main2Activity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mRecyclerView= (RecyclerView) findViewById(R.id.cacheRv);

    }

    class MyAdapter extends RecyclerView.Adapter<ViewHolder>{

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mtitle,mtext;
        ImageView mImageView;
        public ViewHolder(View itemView) {
            super(itemView);
            mtitle= (TextView) itemView.findViewById(R.id.tv_title);
            mtext= (TextView) itemView.findViewById(R.id.tv_text);

        }

    }
}
