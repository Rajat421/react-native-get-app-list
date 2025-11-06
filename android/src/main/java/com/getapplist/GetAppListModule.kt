package com.getapplist

import android.content.pm.PackageManager
import android.os.Build
import com.facebook.react.bridge.*

class GetAppListModule(private val reactContext: ReactApplicationContext)
  : ReactContextBaseJavaModule(reactContext) {

  override fun getName(): String = "GetAppList"

  @ReactMethod
  fun getInstalledDeclaredApps(packagesToCheck: ReadableArray, promise: Promise) {
    try {
      val pm = reactContext.packageManager
      val result = Arguments.createArray()

      for (i in 0 until packagesToCheck.size()) {
        val packageName = packagesToCheck.getString(i)

        try {
          val info = pm.getPackageInfo(packageName!!, 0)

          val appName = pm.getApplicationLabel(info.applicationInfo).toString()
          val versionName = info.versionName ?: ""
          val versionCode =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
              info.longVersionCode.toInt()
            else
              info.versionCode

          val map = Arguments.createMap()
          map.putString("packageName", packageName)
          map.putString("appName", appName)
          map.putString("version", versionName)
          map.putInt("versionCode", versionCode)

          result.pushMap(map)
        } catch (_: Exception) {
          // Not installed → ignore
        }
      }

      promise.resolve(result)

    } catch (e: Exception) {
      promise.reject("ERROR", e.message)
    }
  }
}
