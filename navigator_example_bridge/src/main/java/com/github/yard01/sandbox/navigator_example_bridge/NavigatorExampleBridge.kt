package com.github.yard01.sandbox.navigator_example_bridge

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import com.github.yard01.sandbox.cheatsheet.ExampleBridge
import com.github.yard01.sandbox.cheatsheet.viewmodel.IconProvider
import com.github.yard01.sandbox.cheatsheet.viewmodel.ScreenshotProvider
import com.github.yard01.sandbox.navigator_example.MainActivity

class NavigatorExampleBridge(var context: Context) : ExampleBridge, IconProvider, ScreenshotProvider {

    override fun executeExample() {
        val intent = Intent(context, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }

    override fun getIcon(): Drawable? {
        return getDrawable(R.drawable.ic_launcher_background)
    }

    override fun getInfo(): String {
        return context.getString(R.string.navigator_app_name)
    }

    private fun getDrawable(id: Int): Drawable? {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            return context.getDrawable(id)
        return context.resources.getDrawable(id)
    }

    override fun getScreenshotCount(): Int = 2

    override fun getScreenshot(index: Int): Drawable? {
        when (index) {
            0 -> return getDrawable(R.drawable.screenshot_1)
            1 -> return getDrawable(R.drawable.screenshot_2)
            2 -> return getDrawable(R.drawable.screenshot_3)
        }

        return null
//        return context.getDrawable(R.drawable.ic_menu_gallery)!!
        //Array<Drawable?>?
    }


}