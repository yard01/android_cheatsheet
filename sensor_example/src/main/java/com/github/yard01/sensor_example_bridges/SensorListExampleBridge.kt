package com.github.yard01.sensor_example_bridges

import android.content.Context
import android.content.Intent
import com.github.yard01.sandbox.cheatsheet.ExampleBridge
import com.github.yard01.sandbox.cheatsheet.viewmodel.IconProvider
import com.github.yard01.sandbox.cheatsheet.viewmodel.ScreenshotProvider
import com.github.yard01.sensor_examples.SensorActivity

class SensorListExampleBridge (var context: Context): ExampleBridge, IconProvider, ScreenshotProvider {
    override fun executeExample() {
        val intent = Intent(context, SensorActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }

    override fun getIconId(): Int {
    //    TODO("Not yet implemented")
        return -1
    }

    override fun getIconBackgroundId(): Int {
    //    TODO("Not yet implemented")
        return -1
    }

    override fun getName(): String {
    //    TODO("Not yet implemented")
        return ""
    }

    override fun getDescription(): String {
    //    TODO("Not yet implemented")
        return ""
    }

    override fun getScreenshotCount(): Int {
     //   TODO("Not yet implemented")
        return 0
    }

    override fun getScreenshotId(index: Int): Int {
    //    TODO("Not yet implemented")
        return -1
    }

}