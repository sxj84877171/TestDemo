package com.happy.travel.ui;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.happy.travel.R;
import com.happy.travel.base.BaseActivity;
import com.happy.travel.base.BaseFragment;
import com.happy.travel.data.local.SpUtil;
import com.happy.travel.entity.Category;
import com.happy.travel.ui.model.MainModel;
import com.happy.travel.presenter.MainPresenter;
import com.happy.travel.ui.view.MainView;
import com.happy.travel.util.ToastUtil;

import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity<MainPresenter,MainModel> implements MainView{

    @Bind(R.id.toolbar)
    public Toolbar toolbar;

    @Bind(R.id.fab)
    public FloatingActionButton fab;

    @Bind(R.id.nv_main_navigation)
    public NavigationView nvMainNavigation;

    @Bind(R.id.dl_main_drawer)
    public  DrawerLayout dlMainDrawer;

    @Bind(R.id.contentPanel)
    public FrameLayout contentPanel ;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public void initView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, dlMainDrawer, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        dlMainDrawer.setDrawerListener(mDrawerToggle);

        fab.setOnClickListener(v -> Snackbar.make(v, "Snackbar comes out", Snackbar.LENGTH_LONG).setAction("action", vi -> ToastUtil.show(this, "ok")).show());

        View headerView = nvMainNavigation.inflateHeaderView(R.layout.nav_header_main);

        nvMainNavigation.setNavigationItemSelectedListener(item -> {
            item.setChecked(true);
            dlMainDrawer.closeDrawers();
            switch (item.getItemId()) {
                case R.id.nav_manage:
//                    startActivity(new Intent(mContext, SettingsActivity.class));
                    break;
                case R.id.nav_share:
//                    startActivity(new Intent(mContext, LoginActivity.class));
                    break;
                case R.id.nav_send:
                    SpUtil.setNight(mContext, !SpUtil.isNight());
                    break;
            }
            return true;
        });

    }


    @Override
    public void showFragment(BaseFragment fragment) {
//        getSupportFragmentManager().
    }
}
