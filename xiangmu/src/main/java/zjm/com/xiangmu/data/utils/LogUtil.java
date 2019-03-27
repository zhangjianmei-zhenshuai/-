package zjm.com.xiangmu.data.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;

import zjm.com.xiangmu.BuildConfig;

public class LogUtil {
    private static boolean DEBUG = BuildConfig.DEBUG;

    public static void d(String tag, String msg) {
        Log.d(tag, "d-->DEBUG=" + DEBUG);
        if (DEBUG) {
            Log.d(tag, msg);
        }
    }
    public static void init(Context context) {
        DEBUG = context.getApplicationInfo() != null &&
                (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }
}
