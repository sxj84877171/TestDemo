package com.happy.travel.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.happy.travel.R;
import com.happy.travel.data.local.SpUtil;
import com.happy.travel.util.TUtil;

import butterknife.ButterKnife;


/**
 * Created by elvissun on 8/12/2016.
 */
public abstract class BaseActivity<P extends BasePresenter, M extends BaseModel> extends AppCompatActivity {

    public boolean isNight;
    public P mPresenter;
    public M mModel;
    public Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isNight = SpUtil.isNight();
        setTheme(isNight ? R.style.AppThemeNight : R.style.AppThemeDay);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        mContext = this;
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        this.initView();
        if (this instanceof BaseView) mPresenter.setMV(mModel, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isNight != SpUtil.isNight()) reload();
    }


    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        if (layoutResID == R.layout.activity_main) {
            super.setContentView(layoutResID);
        } else {
            super.setContentView(getContainer());
            View view = LayoutInflater.from(this).inflate(layoutResID, null);
            view.setBackgroundColor(getResources().getColor(R.color.window_background));
//            swipeBackLayout.addView(view);
        }
    }

    private View getContainer() {
        RelativeLayout container = new RelativeLayout(this);
        /*swipeBackLayout = new SwipeBackLayout(this);
        swipeBackLayout.setDragEdge(SwipeBackLayout.DragEdge.LEFT);
        ivShadow = new ImageView(this);
        ivShadow.setBackgroundColor(getResources().getColor(R.color.theme_black_7f));
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        container.addView(ivShadow, params);
        container.addView(swipeBackLayout);
        swipeBackLayout.setOnSwipeBackListener((fa, fs) -> ivShadow.setAlpha(1 - fs));*/
        return container;
    }


    public abstract int getLayoutId();

    public abstract void initView();
}
