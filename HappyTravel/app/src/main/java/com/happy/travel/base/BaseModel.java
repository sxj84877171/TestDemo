package com.happy.travel.base;

import android.content.Context;

/**
 * Created by elvissun on 8/12/2016.
 */
public abstract class BaseModel implements Modle{

    private Context context ;

    public void setContext(Context context) {
        this.context = context;
    }

    public String getString(int resId){
        if(context != null){
            return context.getResources().getString(resId);
        }
        return null;
    }
}
