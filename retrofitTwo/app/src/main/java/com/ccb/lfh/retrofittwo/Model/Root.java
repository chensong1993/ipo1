package com.ccb.lfh.retrofittwo.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 2017/5/3.
 */

public class Root {
    @SerializedName("status")
    private boolean status;
    @SerializedName("total")
    private int total;
    @SerializedName("tngou")
    private List<Tangou> tngou ;

    public void setStatus(boolean status){
        this.status = status;
    }
    public boolean getStatus(){
        return this.status;
    }
    public void setTotal(int total){
        this.total = total;
    }
    public int getTotal(){
        return this.total;
    }
    public void setTngou(List<Tangou> tngou){
        this.tngou = tngou;
    }
    public List<Tangou> getTngou(){
        return this.tngou;
    }
}
