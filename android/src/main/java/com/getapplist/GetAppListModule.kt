package com.getapplist

import android.content.pm.ApplicationInfo
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.WritableArray
import com.facebook.react.bridge.WritableMap
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Base64
import java.io.ByteArrayOutputStream

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


          val iconDrawable = pm.getApplicationIcon(applicationInfo)
          val base64Icon = encodeIconToBase64(iconDrawable)

          app.putString("icon", base64Icon)
          apps.pushMap(app)
        }
      }
      promise.resolve(apps)
    } catch (e: Exception) {
      promise.reject("Error", e)
    }
  }

  private fun encodeIconToBase64(iconDrawable: Drawable): String {
      val bitmap: Bitmap = if (iconDrawable is BitmapDrawable) {
          iconDrawable.bitmap
      } else {
          val bitmap = Bitmap.createBitmap(iconDrawable.intrinsicWidth, iconDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
          val canvas = Canvas(bitmap)
          iconDrawable.setBounds(0, 0, canvas.width, canvas.height)
          iconDrawable.draw(canvas)
          bitmap
      }
      val byteArrayOutputStream = ByteArrayOutputStream()
      bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
      val byteArray = byteArrayOutputStream.toByteArray()
      return Base64.encodeToString(byteArray, Base64.DEFAULT)
  }


  companion object {
    const val NAME = "GetAppList"
  }
}
