package com.chang.template.utils

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import android.util.DisplayMetrics
import android.widget.Toast

import java.io.InputStream
import java.nio.charset.Charset

/**
 * Created by Howard Chang on 2016/12/20
 */
class AndroidUtils {
    companion object {
        private val VERSION_NAME_DIVIDE = "-"

        fun showDebugInfo(activity: Activity, buildType: String) {
            Toast.makeText(activity, "App Version: " + AndroidUtils.getVersionName(activity) + "\n"
                    + "Build Type: " + buildType + "\n"
                    + "Device: " + Build.MODEL + "\n"
                    + "OS Version: " + Build.VERSION.RELEASE + "\n"
                    + "OS API: " + Build.VERSION.SDK_INT + "\n"
                    + "Screen Size: " + getScreenSizeStr(activity) + "\n"
                    + "Screen Density: " + AndroidUtils.getDPIStr(activity), Toast.LENGTH_LONG).show()
        }

        @TargetApi(17)
        fun getScreenSizeStr(activity: Activity): String {
            val displaymetrics = DisplayMetrics()
            if (Build.VERSION.SDK_INT >= 17) {
            } else {
                activity.windowManager.defaultDisplay.getMetrics(displaymetrics)
            }

            return displaymetrics.widthPixels.toString() + "x" + displaymetrics.heightPixels
        }

        fun getDPIInt(activity: Activity): Int {
            return activity.resources.displayMetrics.densityDpi
        }

        @TargetApi(18)
        fun getDPIStr(activity: Activity): String {
            val dpi = getDPIInt(activity)
            when (dpi) {
                120 -> return "ldpi"
                160 -> return "mdpi"
                240 -> return "hdpi"
                else -> return getDPIStr9(dpi)
            }
        }

        @TargetApi(9)
        private fun getDPIStr9(dpi: Int): String {
            return if (Build.VERSION.SDK_INT >= 9) {
                when (dpi) {
                    320 -> "xhdpi"
                    else -> getDPIStr13(dpi)
                }
            } else {
                "dpi_$dpi"
            }
        }

        @TargetApi(13)
        private fun getDPIStr13(dpi: Int): String {
            return if (Build.VERSION.SDK_INT >= 13) {
                when (dpi) {
                    213 -> "tvdpi"
                    else -> getDPIStr16(dpi)
                }
            } else {
                "dpi_$dpi"
            }
        }

        @TargetApi(16)
        private fun getDPIStr16(dpi: Int): String {
            return if (Build.VERSION.SDK_INT >= 16) {
                when (dpi) {
                    480 -> "xxhdpi"
                    else -> getDPIStr18(dpi)
                }
            } else {
                "dpi_$dpi"
            }
        }

        @TargetApi(18)
        private fun getDPIStr18(dpi: Int): String {
            return if (Build.VERSION.SDK_INT >= 18) {
                when (dpi) {
                    640 -> "xxxhdpi"
                    else -> "dpi_$dpi"
                }
            } else {
                "dpi_$dpi"
            }
        }

        fun getVersionNameWithoutSuffix(context: Context): String {
            return getVersionName(context).split("-".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()[0]
        }

        fun getVersionName(context: Context): String {
            val packageManager = context.packageManager

            try {
                return packageManager.getPackageInfo(context.packageName, 0).versionName
            } catch (var3: PackageManager.NameNotFoundException) {
                var3.printStackTrace()
                return ""
            }

        }

        fun getAndroidId(context: Context): String {
            val id = Settings.Secure.getString(context.contentResolver, "android_id")
            return id ?: "FFFFFFFFFFFFFFFF"
        }

        fun readAssetFile(mContext: Context, file: String, code: String): String {
            var result = ""

            try {
                val e = mContext.assets.open(file)
                val len = e.available()
                val buf = ByteArray(len)
                e.read(buf, 0, len)
                result = String(buf, Charset.forName(code))
            } catch (var7: Exception) {
                var7.printStackTrace()
            }

            return result
        }

        fun getScreenHeight(activity: Activity): Int {
            val displaymetrics = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(displaymetrics)
            return displaymetrics.heightPixels
        }

        fun getScreenWidth(activity: Activity): Int {
            val displaymetrics = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(displaymetrics)
            return displaymetrics.widthPixels
        }
    }
}
