package com.github.yard01.map_example_bridges
//        com.github.yard01.map_example_bridges.GoogleMapBridge

import android.content.Context
import android.content.Intent
import com.github.yard01.map_example.MapActivity
import com.github.yard01.map_example.R
import com.github.yard01.sandbox.cheatsheet.ExampleBridge
import com.github.yard01.sandbox.cheatsheet.viewmodel.IconProvider
import com.github.yard01.sandbox.cheatsheet.viewmodel.ScreenshotProvider

class GoogleMapBridge (var context: Context) : ExampleBridge, IconProvider, ScreenshotProvider {

    companion object {

    }

    override fun executeExample() {
        val intent = Intent(context, MapActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }

    override fun getIconId(): Int = R.drawable.template_map_activity

    override fun getIconBackgroundId(): Int = -1

    override fun getName(): String = context.getString(R.string.map_example)

    override fun getDescription(): String = context.getString(R.string.map_example)

    override fun getScreenshotCount(): Int = 4

    override fun getScreenshotId(index: Int): Int =
        when (index) {
             0 -> R.drawable.map_screenshot_0
             1 -> R.drawable.map_screenshot_1
             2 -> R.drawable.map_screenshot_2
             3 -> R.drawable.map_screenshot_3
             else -> -1
        }
}