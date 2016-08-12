package com.happy.travel;

import android.app.Application;
import android.content.Intent;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by elvissun on 8/12/2016.
 */
public class HappyTravelApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new CrashHandler(uncaughtExceptionHandler));
    }


    /**
     *
     */
    class CrashHandler implements Thread.UncaughtExceptionHandler {


        private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;

        public CrashHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
            this.uncaughtExceptionHandler = uncaughtExceptionHandler;
        }

        @Override
        public void uncaughtException(Thread thread, Throwable ex) {
            if (Environment.getExternalStorageDirectory() != null) {
                String rootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
                String folderPath = rootPath + "/owner/";
                File folder = new File(folderPath);
                if (!folder.exists()) {
                    folder.mkdirs();
                }
                try {
                    Date date = new Date();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    File file = new File(folderPath + "crash_report_" + df.format(date));
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    ex.printStackTrace(new PrintStream(new FileOutputStream(file)));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ex.printStackTrace();
                if (this.uncaughtExceptionHandler != null) {
                    uncaughtExceptionHandler.uncaughtException(thread, ex);
                }

                if (!BuildConfig.DEBUG) {
                    final Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    android.os.Process.killProcess(android.os.Process.myPid());
                }

            }
        }
    }
}
