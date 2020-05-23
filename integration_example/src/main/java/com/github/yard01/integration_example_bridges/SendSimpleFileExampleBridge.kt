package com.github.yard01.integration_example_bridges

import android.content.Context
import android.content.Intent
import com.github.yard01.integration_example.IntegrationActivity
import com.github.yard01.integration_example.R
import com.github.yard01.integration_example.SendSimpleFileFragment
import com.github.yard01.sandbox.cheatsheet.ExampleBridge
import com.github.yard01.sandbox.cheatsheet.viewmodel.IconProvider
import com.github.yard01.sandbox.cheatsheet.viewmodel.ScreenshotProvider

class SendSimpleFileExampleBridge (var context: Context): ExampleBridge, IconProvider, ScreenshotProvider {
    override fun executeExample() {
        val intent = Intent(context, IntegrationActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            putExtra(IntegrationActivity.FRAGMENT_CLASSNAME, SendSimpleFileFragment::class.java.name)
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
        return context.getString(R.string.send_simple_file_name)
    }

    override fun getDescription(): String {
        return context.getString(R.string.send_simple_file_description)
    }

    override fun getScreenshotCount(): Int {
        //   TODO("Not yet implemented")
        return 2
    }

    override fun getScreenshotId(index: Int): Int {
        when (index) {
            0 -> return R.drawable.integration_sendfile_1
            1 -> return R.drawable.integration_sendfile_2
        }
        return -1 //R.drawable.sensorlist_screenshot_0
    }
}