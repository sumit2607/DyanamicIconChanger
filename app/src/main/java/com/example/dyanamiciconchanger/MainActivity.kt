package com.example.dyanamiciconchanger

import android.content.ComponentName
import android.content.pm.PackageManager
import android.content.pm.ShortcutManager
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.dyanamiciconchanger.adapter.ImageSliderAdapter
import com.example.dyanamiciconchanger.databinding.ActivityMainBinding
import java.util.Timer
import java.util.TimerTask

class MainActivity : AppCompatActivity() {
    private lateinit var shortcutManager: ShortcutManager
    private lateinit var binding: ActivityMainBinding

    private lateinit var imageSliderAdapter: ImageSliderAdapter
    private val images = listOf(R.drawable.p1, R.drawable.p2, R.drawable.p3)


    @RequiresApi(Build.VERSION_CODES.N_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        shortcutManager = getSystemService(ShortcutManager::class.java)
        // Example: Change app icon to a specific predefined drawable
        imageSliderAdapter = ImageSliderAdapter(this, images)
        binding.apply {


            viewPager.adapter = imageSliderAdapter

            val timer = Timer()
            timer.scheduleAtFixedRate(SliderTimerTask(), 3000, 3000)




        }

//        clickAction()
    }



//    private fun clickAction() {
//        binding.apply {
//            changeIcon.setOnClickListener {
//                packageManager?.setComponentEnabledSetting(
//                    ComponentName(
//                        applicationContext.packageName,
//                        applicationContext.packageName + ".MainActivityAlias1"
//                    ),
//                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP
//                )
//
//                packageManager?.setComponentEnabledSetting(
//                    ComponentName(
//                        applicationContext.packageName,
//                        applicationContext.packageName + ".MainActivityAlias2"
//                    ),
//                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP
//                )
//
//            }
//
//
//            changeIcon2.setOnClickListener {
//                packageManager?.setComponentEnabledSetting(
//                    ComponentName(
//                        applicationContext.packageName,
//                        applicationContext.packageName + ".MainActivityAlias1"
//                    ),
//                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP
//                )
//
//                packageManager?.setComponentEnabledSetting(
//                    ComponentName(
//                        applicationContext.packageName,
//                        applicationContext.packageName + ".MainActivityAlias2"
//                    ),
//                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP
//                )
//            }
//            infoIcon.setOnClickListener {
//                showPremiumDialog()
//            }
//        }
//
//    }

    private fun showPremiumDialog() {
        // Create an AlertDialog.Builder instance
        val builder = AlertDialog.Builder(this)

        // Set dialog title
        builder.setTitle("Special Premium Offer")

        // Create a bulleted list of premium features
        val features = listOf(
            "Ad-Free Experience",
            "Low Price Guarantee",
            "Customizable App Icons"
        )

        // Format the features into a bulleted list
        val formattedFeatures = formatBulletList(features)

        // Set dialog message with formatted bullet points
        builder.setMessage(formattedFeatures)

        // Set positive button (OK)
        builder.setPositiveButton("Claim Offer") { dialog, which ->
            // Handle claim offer action here (e.g., navigate to upgrade screen)
            // For demonstration, we'll just show a toast message
         //   showToast(context, "Redirecting to claim offer...")
            packageManager?.setComponentEnabledSetting(
                ComponentName(
                    applicationContext.packageName,
                    applicationContext.packageName + ".MainActivityAlias1"
                ),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP
            )

            packageManager?.setComponentEnabledSetting(
                ComponentName(
                    applicationContext.packageName,
                    applicationContext.packageName + ".MainActivityAlias2"
                ),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP
            )
            Toast.makeText(this, "App icon changed successfully ", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        // Set negative button (Cancel)
        builder.setNegativeButton("Maybe Later") { dialog, which ->
            dialog.dismiss()
        }

        // Create and show the AlertDialog
        val dialog = builder.create()
        dialog.show()
    }

    // Function to format a list of items into a bulleted list
    fun formatBulletList(items: List<String>): Spanned {
        val stringBuilder = StringBuilder()
        stringBuilder.append("<ul>")
        for (item in items) {
            stringBuilder.append("<li>")
            stringBuilder.append(item)
            stringBuilder.append("</li>")
        }
        stringBuilder.append("</ul>")
        return Html.fromHtml(stringBuilder.toString())
    }


    inner class SliderTimerTask : TimerTask() {
        override fun run() {
            runOnUiThread {
               binding.viewPager.currentItem = (binding.viewPager.currentItem + 1) % images.size
            }
        }
    }

}


