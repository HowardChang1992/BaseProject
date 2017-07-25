package com.chang.template.utils;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.view.Window;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.chang.template.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Howard Chang on 2016/11/2
 */
public class Utility {

    public static final SimpleDateFormat SERVER_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat DATE_TIME_ROC_FORMAT = new SimpleDateFormat("yyy-MM-dd");
    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");


    public static ProgressDialog newProgressDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setCancelable(true);
        progressDialog.setMessage(context.getString(R.string.g_loading));

        return progressDialog;
    }

    public static boolean hasNetworkConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return !(networkInfo == null || !networkInfo.isConnectedOrConnecting());
    }

    public static String formatDate(SimpleDateFormat src, SimpleDateFormat dest, String date) {
        if (date != null) {
            try {
                return dest.format(src.parse(date));
            } catch (ParseException e) {
                return "";
            }
        }

        return "";
    }

    public static String formatDate(SimpleDateFormat dest, Date date) {
        if (date != null) {
            return dest.format(date);
        }
        return "";
    }

    public static Date formatDate(String date, SimpleDateFormat format) {
        if (date == null) {
            return new Date();
        } else {
            try {
                return format.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
                return new Date();
            }
        }
    }

    public static String getUrlEncode(String paramater) {
        try {
            return URLEncoder.encode(paramater, "UTF-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String MD5Encoding(String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Integer[] getResourceIdArray(Resources resources, int array_rId) {
        TypedArray typedArray = resources.obtainTypedArray(array_rId);
        Integer[] rIds = new Integer[typedArray.length()];
        for (int i = 0; i < typedArray.length(); i++) {
            rIds[i] = typedArray.getResourceId(i, -1);
        }
        typedArray.recycle();
        return rIds;
    }

    public static void confirmExitApp(final Activity activity) {
        new MaterialDialog.Builder(activity)
                .title(R.string.app_name)
                .content(R.string.g_a_exit)
                .positiveText(R.string.g_close)
                .negativeText(R.string.g_cancel)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        activity.finish();
                    }
                }).show();
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static String getUniquePhoneIdentity(Context context) {
        String m_szDevIDShort = "35"
                + (Build.BOARD.length() % 10)
                + (Build.BRAND.length() % 10)
                + (Build.CPU_ABI.length() % 10)
                + (Build.DEVICE.length() % 10)
                + (Build.MANUFACTURER.length() % 10)
                + (Build.MODEL.length() % 10)
                + (Build.PRODUCT.length() % 10);

        String serial;
        try {
            serial = Build.class.getField("SERIAL").get(null).toString();
        } catch (Exception exception) {
            serial = "serial";
        }

        String android_id;
        try {
            android_id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        } catch (Exception exception) {
            android_id = "android_id";
        }

        return new UUID(m_szDevIDShort.hashCode(), (serial + android_id).hashCode()).toString();
    }

    public static boolean haveInstallChrome(Context context) {
        String packageName = "com.android.chrome";
        Intent browserIntent = new Intent();
        browserIntent.setPackage(packageName);
        List<ResolveInfo> activitiesList = context.getPackageManager().queryIntentActivities(
                browserIntent, -1);

        return activitiesList.size() > 0;
    }

    public static void parseUrlScheme(Context context, String scheme) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(Uri.parse(scheme));
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {

        }
    }

    public static String getSHA1(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(text.getBytes("iso-8859-1"), 0, text.length());
        byte[] sha1hash = md.digest();

        // Create Hex String
        StringBuilder hexString = new StringBuilder();
        for (byte aMessageDigest : sha1hash) {
            String h = Integer.toHexString(0xFF & aMessageDigest);
            while (h.length() < 2)
                h = "0" + h;
            hexString.append(h);
        }
        return hexString.toString();
    }

    public static boolean isBarcodeReceiveLeftPart(String code) {
        boolean isLeftPart = false;

        try {
            String invoicePattern = "[a-zA-Z]{2}[0-9]{15}(?=.*=)";
            Pattern regEx = Pattern.compile(invoicePattern);

            // Find instance of pattern matches
            Matcher m = regEx.matcher(code);
            if (m.find()) {
                isLeftPart = true;
            }

        } catch (Exception e) {
            // do nothing.
        }

        return isLeftPart;
    }
}
