package com.github.yard01.sensor_example_bridges

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.github.yard01.sandbox.cheatsheet.ExampleBridge
import com.github.yard01.sandbox.cheatsheet.viewmodel.IconProvider
import com.github.yard01.sandbox.cheatsheet.viewmodel.ScreenshotProvider
import com.github.yard01.sensor_examples.SensorActivity

class SensorListExampleBridge (var context: Context): ExampleBridge, IconProvider, ScreenshotProvider {
    override fun executeExample() {
        val intent = Intent(context, SensorActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            putExtra(SensorActivity.FRAGMENT_CLASSNAME, SensorListFragment::class.java.name)
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
        return context.getString(R.string.sensorlist_example_name)
    }

    override fun getDescription(): String {
        return context.getString(R.string.sensorlist_description)
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