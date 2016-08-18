package com.lenovo.quickapp.uitl;

import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.lenovo.quickapp.BuildConfig;

/**
 * Created by elvissun on 7/6/2016.
 */
public class LogUtil {
    public static String customTagPrefix = "HappyTravel";
    private static FileWriter fileWriter;
    public static String LOG_PATH = File.separator + "sdcard" + File.separator + customTagPrefix + File.separator + "log" + File.separator;

    public static String SPEAR_LINE =
            "------------------------------------------------------------------------------------------------------------------------------------------\n" +
                    "------------------------------------------------------------------------------------------------------------------------------------------\n" +
                    "------------------------------------------------------------------------------------------------------------------------------------------\n";

    private LogUtil() {
        throw  new UnsupportedOperationException("No Impl");
    }

    private static void init() {
        if (fileWriter == null) {
            File path = new File(LOG_PATH);
            if (!path.exists()) {
                path.mkdirs();
            }
            File logFile = new File(LOG_PATH + customTagPrefix + ".log");

            if (!logFile.exists()) {
                try {
                    logFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                if (fileWriter == null) {
                    fileWriter = new FileWriter(logFile, true);
                }
            } catch (IOException e) {
            }
        }
    }

    private static String generateTag() {
        StackTraceElement caller = new Throwable().getStackTrace()[2];
        String tag = "%s.%s(L:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
        tag = TextUtils.isEmpty(customTagPrefix) ? tag : customTagPrefix + ":" + tag;
        return tag;
    }

    public static void d(String content) {
        String tag = generateTag();
        d(tag, content);
    }

    public static void d(String tag, String content) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, content);
        } else {
            writeMessageToFile(tag, content);
        }
    }

    public static void d(String content, Throwable tr) {
        String tag = generateTag();
        d(tag, content + getErrorMessage(tr));
    }

    public static void e(String content) {
        String tag = generateTag();
        e(tag, content);
    }

    public static void e(String tag, String content) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, content);
        } else {
            writeMessageToFile(tag, content);
        }
    }

    public static void e(String content, Throwable tr) {
        String tag = generateTag();
        e(tag, content + getErrorMessage(tr));

    }

    public static void i(String content) {
        String tag = generateTag();
        i(tag, content);
    }

    public static void i(String tag, String content) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, content);
        } else {
            writeMessageToFile(tag, content);
        }
    }

    public static void i(String content, Throwable tr) {
        String tag = generateTag();
        i(tag, content + getErrorMessage(tr));
    }

    public static void v(String content) {
        String tag = generateTag();
        v(tag, content);
    }

    public static void v(String tag, String content) {
        if (BuildConfig.DEBUG) {
            Log.v(tag, content);
        } else {
            writeMessageToFile(tag, content);
        }
    }

    public static void v(String content, Throwable tr) {
        String tag = generateTag();
        v(tag, content + getErrorMessage(tr));
    }

    public static void w(String content) {
        String tag = generateTag();
        w(tag, content);
    }

    public static void w(String tag, String content) {
        if (BuildConfig.DEBUG) {
            Log.w(tag, content);
        } else {
            writeMessageToFile(tag, content);
        }
    }

    public static void w(String content, Throwable tr) {
        String tag = generateTag();
        w(tag, content + getErrorMessage(tr));
    }

    public static void w(Throwable tr) {
        String tag = generateTag();
        w(tag, getErrorMessage(tr));
    }


    public static void wtf(String content) {
        String tag = generateTag();
        wtf(tag, content, null);
    }

    public static void wtf(String content, Throwable tr) {
        String tag = generateTag();
        wtf(tag, content, tr);
    }

    public static void wtf(String tag, String content, Throwable tr) {
        if (content == null) {
            content = "log:";
        }
        if (BuildConfig.DEBUG) {
            Log.wtf(tag, content, tr);
        } else {
            writeMessageToFile(tag, content + getErrorMessage(tr));
        }
    }

    public static void wtf(Throwable tr) {
        wtf(null, tr);
    }


    public static void writeToFile(String message) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(date);
        init();
        String tag = generateTag();
        Log.i(tag, message);
        String msg = String.format("%s : %s\n", dateStr, message);
        writeMessageToFile(msg);
    }

    private static void writeMessageToFile(String tag, String message) {
        writeMessageToFile(tag + "| " + message);
    }

    private static void writeMessageToFile(String message) {
        if (fileWriter != null) {
            try {
                fileWriter.write(message);
                fileWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String getErrorMessage(Throwable e) {
        if (e != null) {
            return Log.getStackTraceString(e);
        } else {
            return "";
        }
    }

    public static void writeSpearLine() {
        try {
            init();
            if (fileWriter != null) {
                fileWriter.write(SPEAR_LINE);
                fileWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void close() {
        if (fileWriter != null) {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
