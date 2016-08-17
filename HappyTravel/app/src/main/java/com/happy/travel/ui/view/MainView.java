package com.happy.travel.ui.view;

import com.happy.travel.base.BaseFragment;
import com.happy.travel.base.BaseView;
import com.happy.travel.entity.Category;

import java.util.List;

/**
 * Created by elvissun on 8/16/2016.
 */
public interface MainView extends BaseView {

//    public void showView(List<Category> list);

    public void showFragment(BaseFragment fragment);
}
