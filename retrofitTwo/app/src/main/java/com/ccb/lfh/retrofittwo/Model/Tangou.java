package com.ccb.lfh.retrofittwo.Model;

import android.app.Service;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 2017/5/3.
 */

public class Tangou  {

    @SerializedName("count")
    private int count;//访问次数
    @SerializedName("description")
    private String description;//描述
    @SerializedName("fcount")
    private int fcount;
    @SerializedName("food")
    private String food;
    @SerializedName("id")
    private int id;
    @SerializedName("images")
    private String images;
    @SerializedName("img")
    private String img;
    @SerializedName("keywords")
    private String keywords;
    @SerializedName("name")
    private String name;
    @SerializedName("rcount")
    private int rcount;//评论的读数

    public void setCount(int count){
        this.count = count;
    }
    public int getCount(){
        return this.count;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }
    public void setFcount(int fcount){
        this.fcount = fcount;
    }
    public int getFcount(){
        return this.fcount;
    }
    public void setFood(String food){
        this.food = food;
    }
    public String getFood(){
        return this.food;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setImages(String images){
        this.images = images;
    }
    public String getImages(){
        return this.images;
    }
    public void setImg(String img){
        this.img = img;
    }
    public String getImg(){
        return this.img;
    }
    public void setKeywords(String keywords){
        this.keywords = keywords;
    }
    public String getKeywords(){
        return this.keywords;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setRcount(int rcount){
        this.rcount = rcount;
    }
    public int getRcount(){
        return this.rcount;
    }
}
