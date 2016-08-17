package com.happy.travel.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.happy.travel.util.TUtil;

import butterknife.ButterKnife;

/**
 * Created by elvissun on 8/16/2016.
 */
public abstract class BaseFragment<P extends Presenter,M extends Modle> extends Fragment {

    private P present ;
    private M model ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        present = TUtil.getT(this, 0);
        model = TUtil.getT(this, 1);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutID(),container);
        ButterKnife.bind(this,rootView);
        return rootView;
    }

    public abstract int getLayoutID();
}
