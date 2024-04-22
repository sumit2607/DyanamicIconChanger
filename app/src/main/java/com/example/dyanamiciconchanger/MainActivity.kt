package com.example.dyanamiciconchanger

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.pm.PackageManager
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.dyanamiciconchanger.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var shortcutManager: ShortcutManager
    private lateinit var binding: ActivityMainBinding
    @RequiresApi(Build.VERSION_CODES.N_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        shortcutManager = getSystemService(ShortcutManager::class.java)

        // Example: Change app icon to a specific predefined drawable
        binding.apply {
            changeIcon.setOnClickListener {

                packageManager?.setComponentEnabledSetting(
                    ComponentName(applicationContext.packageName, applicationContext.packageName + ".MainActivityAlias1"),
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP
                )

                packageManager?.setComponentEnabledSetting(
                    ComponentName(applicationContext.packageName, applicationContext.packageName + ".MainActivityAlias2"),
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP
                )

            }


            changeIcon2.setOnClickListener {
                packageManager?.setComponentEnabledSetting(
                    ComponentName(applicationContext.packageName, applicationContext.packageName + ".MainActivityAlias1"),
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP
                )

                packageManager?.setComponentEnabledSetting(
                    ComponentName(applicationContext.packageName, applicationContext.packageName + ".MainActivityAlias2"),
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP
                )
            }
        }
       
    }


}

