package com.heima.jyl.redboy.config;

import android.content.Context;

import java.io.File;

/**
 * Created by 46321 on 2016/6/15/015.
 */
public class NetConfig {
    public File RESPONSE_CACHE;//缓存文件
    public int RESPONSE_CACHE_SIZE;//缓存文件大小
    public int CONNET_TIMEOUT;//连接超时
    public int READ_TIMEOUT;//读取新链接超时
    public int MAX_CACHE_AGE;//从文档被访问后的存活时间
    public Context context;

    public NetConfig(File RESPONSE_CACHE,int RESPONSE_CACHE_SIZE,int CONNET_TIMEOUT,int READ_TIMEOUT,int MAX_CACHE_AGE,Context context){
        this.RESPONSE_CACHE = RESPONSE_CACHE;
        this.RESPONSE_CACHE_SIZE = RESPONSE_CACHE_SIZE;
        this.CONNET_TIMEOUT = CONNET_TIMEOUT;
        this.READ_TIMEOUT = READ_TIMEOUT;
        this.MAX_CACHE_AGE = MAX_CACHE_AGE;
        this.context = context;
    }
}
