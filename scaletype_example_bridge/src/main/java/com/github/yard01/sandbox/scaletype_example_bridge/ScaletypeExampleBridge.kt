package com.github.yard01.sandbox.scaletype_example_bridge

import android.content.Context
import android.content.Intent
import com.example.matrixscale.EntryActivity
import com.example.matrixscale.MainActivity
import com.github.yard01.sandbox.cheatsheet.ExampleBridge
import com.github.yard01.sandbox.cheatsheet.viewmodel.IconProvider
import com.github.yard01.sandbox.cheatsheet.viewmodel.ScreenshotProvider

class ScaletypeExampleBridge (var context: Context): ExampleBridge, IconProvider, ScreenshotProvider {
    override fun executeExample() {
        val intent = Intent(context, EntryActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }

    override fun getIconId(): Int = -1

    override fun getIconBackgroundId(): Int = -1

    override fun getName(): String = ""

    override fun getDescription(): String = "Scale"

    override fun getScreenshotCount(): Int = 0

    override fun getScreenshotId(index: Int): Int = -1

}