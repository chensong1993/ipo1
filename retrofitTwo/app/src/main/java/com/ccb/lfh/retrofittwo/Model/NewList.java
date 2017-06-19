package com.ccb.lfh.retrofittwo.Model;

import java.util.List;

/**
 * Created by admin on 2017/5/4.
 */

public class NewList {
    private String stat;

    private List<News> data ;

    public void setStat(String stat){
        this.stat = stat;
    }
    public String getStat(){
        return this.stat;
    }
    public void setData(List<News> data){
        this.data = data;
    }
    public List<News> getData(){
        return this.data;
    }
}
