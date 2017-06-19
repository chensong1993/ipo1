package com.ccb.lfh.retrofittwo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ccb.lfh.retrofittwo.Model.News;
import com.ccb.lfh.retrofittwo.Model.Tangou;
import com.ccb.lfh.retrofittwo.R;
import com.ccb.lfh.retrofittwo.XinwenActivity;
import com.squareup.picasso.Picasso;

import java.util.Collection;
import java.util.List;

/**
 * Created by admin on 2017/5/3.
 */

public class NewsAdapter extends BaseAdapter implements AdapterView.OnItemClickListener{
    private Context context;
    private List<News> list;
     News item;
    public NewsAdapter(Context context, List<News> list){
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
            convertView.setTag(new ViewHolder(convertView));
        }
            ViewHolder holder= (ViewHolder) convertView.getTag();
            News News=list.get(position);
            holder.mtext.setText(News.getDate());
            holder.mtitle.setText(News.getTitle());
            Picasso.with(context).load(News.getThumbnail_pic_s()).into(holder.mimageView);
        return convertView;

    }

    public  void addAll(Collection<? extends News> collection ){
        list.addAll(collection);
        notifyDataSetChanged();


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(context, XinwenActivity.class);
        intent.putExtra(XinwenActivity.NEW_URL,item.getUrl());
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
