package com.ccb.lfh.retrofittwo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<Tangou> list;

    public  MyAdapter(Context context,List<Tangou> list){
        this.context=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
            convertView.setTag(new ViewHolder(convertView));
        }
            ViewHolder holder= (ViewHolder) convertView.getTag();
            Tangou tangou=list.get(position);
            holder.mtext.setText(tangou.getName());
            holder.mtitle.setText(tangou.getDescription());
            Picasso.with(context).load("http://tnfs.tngou.net/img"+ tangou.getImg()).into(holder.mimageView);
        return convertView;

    }

    public  void addAll(Collection<? extends Tangou> collection ){
        list.addAll(collection);
        notifyDataSetChanged();


    }

    public static class ViewHolder{
        private ImageView mimageView;
        private TextView mtitle,mtext;
        public ViewHolder(View item){
            mimageView= (ImageView) item.findViewById(R.id.iv_photo);
            mtitle= (TextView) item.findViewById(R.id.tv_title);
            mtext= (TextView) item.findViewById(R.id.tv_text);
        }
    }
}
