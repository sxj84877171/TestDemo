package com.lenovo.quickapp;

import android.app.Application;

import com.lenovo.quickapp.data.local.db.dao.DaoMaster;
import com.lenovo.quickapp.data.local.db.dao.DaoSession;
import com.lenovo.quickapp.uitl.SpUtil;

import org.greenrobot.greendao.database.Database;


/**
 * Created by elvissun on 8/18/2016.
 */
public class App extends Application {

    private static App app;
    public static final boolean ENCRYPTED = true;

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        SpUtil.init(this);

        String appName = getResources().getString(R.string.app_name);

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? appName + "-db-encrypted" : appName + "-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public static App getApp() {
        return app;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
