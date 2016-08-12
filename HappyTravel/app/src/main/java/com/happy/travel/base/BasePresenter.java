package com.happy.travel.base;

import android.content.Context;

/**
 * Created by elvissun on 8/12/2016.
 */
public abstract class BasePresenter<M, V> {

    public Context mContext;

    public M mModel;
    public V mView;

    public RxManager mRxManager = new RxManager();

    public void setMV(M m, V v) {
        this.mModel = m;
        this.mView = v;
        this.onStart();
    }

    public abstract void onStart();

    public void onDestroy(){
        mRxManager.clear();
    }
}
