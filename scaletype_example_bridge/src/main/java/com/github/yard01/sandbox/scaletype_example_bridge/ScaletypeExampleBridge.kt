package com.github.yard01.sandbox.scaletype_example_bridge

import android.content.Context
import com.github.yard01.sandbox.cheatsheet.ExampleBridge
import com.github.yard01.sandbox.cheatsheet.viewmodel.IconProvider
import com.github.yard01.sandbox.cheatsheet.viewmodel.ScreenshotProvider

class ScaletypeExampleBridge (var context: Context): ExampleBridge, IconProvider, ScreenshotProvider {
    override fun executeExample() {

    }

    override fun getIconId(): Int = -1

    override fun getIconBackgroundId(): Int = -1

    override fun getName(): String = ""

    override fun getDescription(): String = ""

    override fun getScreenshotCount(): Int = 0

    override fun getScreenshotId(index: Int): Int = -1

}