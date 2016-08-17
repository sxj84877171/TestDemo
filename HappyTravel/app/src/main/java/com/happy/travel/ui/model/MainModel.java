package com.happy.travel.ui.model;

import com.happy.travel.R;
import com.happy.travel.base.BaseModel;
import com.happy.travel.entity.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elvissun on 8/12/2016.
 */
public class MainModel extends BaseModel {

    public List<Category> getTabs(){
        List<Category> list = new ArrayList<>();
        list.add(new Category(getString(R.string.every_day_happy), R.drawable.ttl_bg));
        list.add(new Category(getString(R.string.navigation), R.drawable.dh));
        list.add(new Category(getString(R.string.car_position), R.drawable.dwcc));

        list.add(new Category(getString(R.string.insurance_claim), R.drawable.bxlp));
        list.add(new Category(getString(R.string.illegal_query), R.drawable.wzcx));
        list.add(new Category(getString(R.string.performance_query), R.drawable.xncx));

        list.add(new Category(getString(R.string.maintenance), R.drawable.wxby));
        list.add(new Category(getString(R.string.refueling_car_wash), R.drawable.jyxc));
        list.add(new Category(getString(R.string.trouble_rescue), R.drawable.gzjy));

        list.add(new Category(getString(R.string.car_rental), R.drawable.zcyc));
        list.add(new Category(getString(R.string.group_tour), R.drawable.ztly));
        list.add(new Category(getString(R.string.chat_dating), R.drawable.ltjy));

        list.add(new Category(getString(R.string.electronic_license), R.drawable.ltjy));
        list.add(new Category(getString(R.string.vehicle_alarm), R.drawable.ltjy));
        list.add(new Category(getString(R.string.system_settings), R.drawable.ltjy));

        return list;
    }
}
