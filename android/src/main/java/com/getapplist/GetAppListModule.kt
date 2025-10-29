package com.getapplist

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.facebook.react.bridge.*

class GetAppListModule(reactContext: ReactApplicationContext) :
  ReactContextBaseJavaModule(reactContext) {

  override fun getName(): String = NAME

  @ReactMethod
  fun getInstalledApps(promise: Promise) {
    try {
      val pm = reactApplicationContext.packageManager

      // GET_META_DATA works on all API levels
      val packages = pm.getInstalledPackages(PackageManager.GET_META_DATA)
      val apps: WritableArray = Arguments.createArray()

      for (packageInfo in packages) {
        val appInfo = packageInfo.applicationInfo ?: continue

        // Skip system apps
        if ((appInfo.flags and ApplicationInfo.FLAG_SYSTEM) != 0) continue

        val app: WritableMap = Arguments.createMap()
        app.putString("packageName", packageInfo.packageName)
        app.putString("appName", pm.getApplicationLabel(appInfo).toString())

        apps.pushMap(app)
      }

      promise.resolve(apps)
    } catch (e: Exception) {
      promise.reject("Error", e)
    }
  }

  companion object {
    const val NAME = "GetAppList"
  }
}
