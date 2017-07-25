package com.chang.template.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.widget.Toast;

import java.io.InputStream;

/**
 * Created by Howard Chang on 2016/12/20
 */
public class AndroidUtils {
    private static final String VERSION_NAME_DIVIDE = "-";

    public AndroidUtils() {
    }

    public static void showDebugInfo(Activity activity, String buildType) {
        Toast.makeText(activity, "App Version: " + AndroidUtils.getVersionName(activity) + "\n"
                + "Build Type: " + buildType + "\n"
                + "Device: " + Build.MODEL + "\n"
                + "OS Version: " + Build.VERSION.RELEASE + "\n"
                + "OS API: " + Build.VERSION.SDK_INT + "\n"
                + "Screen Size: " + getScreenSizeStr(activity) + "\n"
                + "Screen Density: " + AndroidUtils.getDPIStr(activity), Toast.LENGTH_LONG).show();
    }

    @TargetApi(17)
    public static String getScreenSizeStr(Activity activity) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= 17) {
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(displaymetrics);
        } else {
            activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        }

        return displaymetrics.widthPixels + "x" + displaymetrics.heightPixels;
    }

    public static int getDPIInt(Activity activity) {
        return activity.getResources().getDisplayMetrics().densityDpi;
    }

    @TargetApi(18)
    public static String getDPIStr(Activity activity) {
        int dpi = getDPIInt(activity);
        switch (dpi) {
            case 120:
                return "ldpi";
            case 160:
                return "mdpi";
            case 240:
                return "hdpi";
            default:
                return getDPIStr9(dpi);
        }
    }

    @TargetApi(9)
    private static String getDPIStr9(int dpi) {
        if (Build.VERSION.SDK_INT >= 9) {
            switch (dpi) {
                case 320:
                    return "xhdpi";
                default:
                    return getDPIStr13(dpi);
            }
        } else {
            return "dpi_" + dpi;
        }
    }

    @TargetApi(13)
    private static String getDPIStr13(int dpi) {
        if (Build.VERSION.SDK_INT >= 13) {
            switch (dpi) {
                case 213:
                    return "tvdpi";
                default:
                    return getDPIStr16(dpi);
            }
        } else {
            return "dpi_" + dpi;
        }
    }

    @TargetApi(16)
    private static String getDPIStr16(int dpi) {
        if (Build.VERSION.SDK_INT >= 16) {
            switch (dpi) {
                case 480:
                    return "xxhdpi";
                default:
                    return getDPIStr18(dpi);
            }
        } else {
            return "dpi_" + dpi;
        }
    }

    @TargetApi(18)
    private static String getDPIStr18(int dpi) {
        if (Build.VERSION.SDK_INT >= 18) {
            switch (dpi) {
                case 640:
                    return "xxxhdpi";
                default:
                    return "dpi_" + dpi;
            }
        } else {
            return "dpi_" + dpi;
        }
    }

    public static String getVersionNameWithoutSuffix(Context context) {
        return getVersionName(context).split("-")[0];
    }

    public static String getVersionName(Context context) {
        PackageManager packageManager = context.getPackageManager();

        try {
            return packageManager.getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException var3) {
            var3.printStackTrace();
            return "";
        }
    }

    public static String getAndroidId(Context context) {
        String id = Settings.Secure.getString(context.getContentResolver(), "android_id");
        return id != null ? id : "FFFFFFFFFFFFFFFF";
    }

    public static String readAssetFile(Context mContext, String file, String code) {
        String result = "";

        try {
            InputStream e = mContext.getAssets().open(file);
            int len = e.available();
            byte[] buf = new byte[len];
            e.read(buf, 0, len);
            result = new String(buf, code);
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        return result;
    }

    public static int getScreenHeight(Activity activity) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.heightPixels;
    }

    public static int getScreenWidth(Activity activity) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.widthPixels;
    }
}
