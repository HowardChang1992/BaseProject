package com.chang.template.utils


import android.app.Activity
import android.app.ProgressDialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.ResolveInfo
import android.content.res.Resources
import android.content.res.TypedArray
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.view.Window

import com.afollestad.materialdialogs.DialogAction
import com.afollestad.materialdialogs.MaterialDialog
import com.chang.template.R

import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.UUID
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by Howard Chang on 2016/11/2
 */
object Utility {

    val SERVER_TIME_FORMAT = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val DATE_TIME_FORMAT = SimpleDateFormat("yyyy-MM-dd")
    val DATE_TIME_ROC_FORMAT = SimpleDateFormat("yyy-MM-dd")
    val DEFAULT_DATE_FORMAT = SimpleDateFormat("yyyy/MM/dd")


    fun newProgressDialog(context: Context): ProgressDialog {
        val progressDialog = ProgressDialog(context)
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        progressDialog.setCancelable(true)
        progressDialog.setMessage(context.getString(R.string.g_loading))

        return progressDialog
    }

    fun hasNetworkConnection(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        return !(networkInfo == null || !networkInfo.isConnectedOrConnecting)
    }

    fun formatDate(src: SimpleDateFormat, dest: SimpleDateFormat, date: String?): String {
        if (date != null) {
            try {
                return dest.format(src.parse(date))
            } catch (e: ParseException) {
                return ""
            }

        }

        return ""
    }

    fun formatDate(dest: SimpleDateFormat, date: Date?): String {
        return if (date != null) {
            dest.format(date)
        } else ""
    }

    fun formatDate(date: String?, format: SimpleDateFormat): Date {
        return if (date == null) {
            Date()
        } else {
            try {
                format.parse(date)
            } catch (e: ParseException) {
                e.printStackTrace()
                Date()
            }

        }
    }

    fun getUrlEncode(paramater: String): String {
        try {
            return URLEncoder.encode(paramater, "UTF-8").replace("+", "%20")
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
            return ""
        }

    }

    fun MD5Encoding(s: String): String {
        val MD5 = "MD5"
        try {
            // Create MD5 Hash
            val digest = MessageDigest
                    .getInstance(MD5)
            digest.update(s.toByteArray())
            val messageDigest = digest.digest()

            // Create Hex String
            val hexString = StringBuilder()
            for (aMessageDigest in messageDigest) {
                var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
                while (h.length < 2)
                    h = "0$h"
                hexString.append(h)
            }
            return hexString.toString()

        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        return ""
    }

    fun getResourceIdArray(resources: Resources, array_rId: Int): Array<Int?> {
        val typedArray = resources.obtainTypedArray(array_rId)
        val rIds = arrayOfNulls<Int>(typedArray.length())
        for (i in 0 until typedArray.length()) {
            rIds[i] = typedArray.getResourceId(i, -1)
        }
        typedArray.recycle()
        return rIds
    }

    fun confirmExitApp(activity: Activity) {
        MaterialDialog.Builder(activity)
                .title(R.string.app_name)
                .content(R.string.g_a_exit)
                .positiveText(R.string.g_close)
                .negativeText(R.string.g_cancel)
                .onPositive { dialog, which -> activity.finish() }.show()
    }

    fun getStatusBarHeight(context: Context): Int {
        var result = 0
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    fun getUniquePhoneIdentity(context: Context): String {
        val m_szDevIDShort = ("35"
                + Build.BOARD.length % 10
                + Build.BRAND.length % 10
                + Build.CPU_ABI.length % 10
                + Build.DEVICE.length % 10
                + Build.MANUFACTURER.length % 10
                + Build.MODEL.length % 10
                + Build.PRODUCT.length % 10)

        var serial: String
        try {
            serial = Build::class.java!!.getField("SERIAL").get(null).toString()
        } catch (exception: Exception) {
            serial = "serial"
        }

        var android_id: String
        try {
            android_id = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        } catch (exception: Exception) {
            android_id = "android_id"
        }

        return UUID(m_szDevIDShort.hashCode().toLong(), (serial + android_id).hashCode().toLong()).toString()
    }

    fun haveInstallChrome(context: Context): Boolean {
        val packageName = "com.android.chrome"
        val browserIntent = Intent()
        browserIntent.setPackage(packageName)
        val activitiesList = context.packageManager.queryIntentActivities(
                browserIntent, -1)

        return activitiesList.size > 0
    }

    fun parseUrlScheme(context: Context, scheme: String) {
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.addCategory(Intent.CATEGORY_BROWSABLE)
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        intent.data = Uri.parse(scheme)
        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {

        }

    }

    @Throws(NoSuchAlgorithmException::class, UnsupportedEncodingException::class)
    fun getSHA1(text: String): String {
        val md = MessageDigest.getInstance("SHA-1")
        md.update(text.toByteArray(charset("iso-8859-1")), 0, text.length)
        val sha1hash = md.digest()

        // Create Hex String
        val hexString = StringBuilder()
        for (aMessageDigest in sha1hash) {
            var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
            while (h.length < 2)
                h = "0$h"
            hexString.append(h)
        }
        return hexString.toString()
    }

    fun isBarcodeReceiveLeftPart(code: String): Boolean {
        var isLeftPart = false

        try {
            val invoicePattern = "[a-zA-Z]{2}[0-9]{15}(?=.*=)"
            val regEx = Pattern.compile(invoicePattern)

            // Find instance of pattern matches
            val m = regEx.matcher(code)
            if (m.find()) {
                isLeftPart = true
            }

        } catch (e: Exception) {
            // do nothing.
        }

        return isLeftPart
    }
}
