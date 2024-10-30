package com.getapplist

import android.content.pm.ApplicationInfo
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.WritableArray
import com.facebook.react.bridge.WritableMap

class GetAppListModule(reactContext: ReactApplicationContext) :
        ReactContextBaseJavaModule(reactContext) {

  override fun getName(): String {
    return NAME
  }

  @ReactMethod
  fun getInstalledApps(promise: Promise) {
    try {
      val pm = reactApplicationContext.packageManager
      val packages = pm.getInstalledPackages(0)
      val apps: WritableArray = Arguments.createArray()

      for (packageInfo in packages) {
        val applicationInfo = packageInfo.applicationInfo
        if (applicationInfo != null && (applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM) == 0
        ) {
          val app: WritableMap = Arguments.createMap()
          app.putString("packageName", packageInfo.packageName)
          app.putString("versionName", packageInfo.versionName)
          app.putString("appName", pm.getApplicationLabel(applicationInfo).toString())

          apps.pushMap(app)
        }
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
