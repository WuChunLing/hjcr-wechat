package com.hjcr.wechat.tools;

/**
 * Created by Administrator on 2016/5/1.
 */
public abstract  class JsonEntry {

    public String toJson(){
        return GsonUtil.toJson(this);
    }

}
