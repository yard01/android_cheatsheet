package com.github.yard01.fabfilter_example_bridge

import android.content.Context
import android.content.Intent
import com.github.yard01.sandbox.cheatsheet.ExampleBridge
import com.github.yard01.sandbox.cheatsheet.viewmodel.IconProvider
import com.github.yard01.sandbox.cheatsheet.viewmodel.ScreenshotProvider
import com.nikhilpanju.fabfilter.main.MainActivity

class FabfilterExampleBridge(val context: Context): ExampleBridge, IconProvider, ScreenshotProvider {
    override fun executeExample() {
        val intent = Intent(context, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }

    override fun getIconId(): Int {
       return R.drawable.ui_fabfilter_icon
    }

    override fun getIconBackgroundId(): Int {
        return R.drawable.ui_fabfilter_icon
    }

    override fun getName(): String {
        return context.getString(R.string.fabfilter_app_name)
    }

    override fun getDescription(): String {
        return context.getString(R.string.fabfilter_app_description)
    }

    override fun getScreenshotCount(): Int {
        return 4
    }

    override fun getScreenshotId(index: Int): Int {
        when (index) {
            0 -> return R.drawable.ui_fabfilter_screenshot_0
            1 -> return R.drawable.ui_fabfilter_screenshot_1
            2 -> return R.drawable.ui_fabfilter_screenshot_2
            3 -> return R.drawable.ui_fabfilter_screenshot_3
        }
        return R.drawable.ui_fabfilter_screenshot_0
    }

}