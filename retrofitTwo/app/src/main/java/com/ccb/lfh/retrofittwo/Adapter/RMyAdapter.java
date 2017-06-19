package com.ccb.lfh.retrofittwo.Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ccb.lfh.retrofittwo.Model.Tangou;
import com.ccb.lfh.retrofittwo.R;
import com.squareup.picasso.Picasso;

import java.util.Collection;
import java.util.List;

/**
 * Created by admin on 2017/5/3.
 */

public class RMyAdapter extends RecyclerView.Adapter<RMyAdapter.ViewHolder> {
    List<Tangou> tangouList;
    Context context;
    public  RMyAdapter(Context context, List<Tangou> list){
        this.context=context;
        this.tangouList=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
                holder.setItem(tangouList.get(position));
                holder.refreshView();
    }

    @Override
    public int getItemCount() {
        return tangouList.size();

    }

    class ViewHolder extends RecyclerView.ViewHolder{
       ImageView mImageView;
       TextView mtitle,mText;
        Tangou item;
        public ViewHolder(View item) {
            super(item);
            mImageView= (ImageView) item.findViewById(R.id.iv_photo);
            mtitle= (TextView) item.findViewById(R.id.tv_title);
            mText= (TextView) item.findViewById(R.id.tv_text);
        }
       public void setItem(Tangou item) {
           this.item = item;
           notifyDataSetChanged();
       }

        void refreshView() {
            mtitle.setText(item.getName());
            mText.setText(item.getDescription());
            Picasso.with(itemView.getContext()).load("http://tnfs.tngou.net/img"+ item.getImg()).into(mImageView);
        }
    }
    public  void addAll(Collection<? extends Tangou> collection ){
        tangouList.addAll(collection);
        notifyDataSetChanged();


    }
}
