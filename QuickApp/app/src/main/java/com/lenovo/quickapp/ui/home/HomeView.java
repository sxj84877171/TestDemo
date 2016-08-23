package com.lenovo.quickapp.ui.home;

import com.lenovo.quickapp.base.BaseView;
import com.lenovo.quickapp.data.local.db.bean.Note;

import java.util.List;

/**
 * Created by elvissun on 8/19/2016.
 */
public interface HomeView extends BaseView {

    public void showResult(List<Note> notes);

    public void showSuccess();

    public void showFail();

}
