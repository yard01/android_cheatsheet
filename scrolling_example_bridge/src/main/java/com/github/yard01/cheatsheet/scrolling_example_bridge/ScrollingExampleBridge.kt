package com.github.yard01.cheatsheet.scrolling_example_bridge

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.github.yard01.sandbox.cheatsheet.ExampleBridge
import com.github.yard01.sandbox.cheatsheet.viewmodel.IconProvider
import com.github.yard01.sandbox.cheatsheet.viewmodel.ScreenshotProvider
import com.github.yard01.scrolling_example.ScrollingActivity

class ScrollingExampleBridge (var context: Context): ExampleBridge, IconProvider, ScreenshotProvider {
    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun executeExample() {
        val intent = Intent(context, ScrollingActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }

    override fun getIconId(): Int {
        return R.drawable.scrolling_activity_icon // .scrolling_icon_foreground // .scrolling_icon_foreground
    }

    override fun getIconBackgroundId(): Int {
        return R.drawable.scrolling_activity_icon // .scrolling_icon_background
    }

    private fun getIcon(): Drawable? {
        return getVectorDrawable(R.drawable.scrolling_icon_foreground)
    }


    private fun getIconBackground(): Drawable? {
        return getVectorDrawable(R.drawable.scrolling_icon_background)
    }

    fun getVectorDrawable(
        resourceId: Int
    ): Drawable? {
        return  VectorDrawableCompat.create(
            context.resources,
            resourceId,
            context.theme
        )
//        val icon: Drawable?
//        icon = (if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
//            VectorDrawableCompat.create(
//                context.resources,
//                resourceId,
//                context.theme
//            )
//        } else {
//            context.resources.getDrawable(resourceId, context.theme)
//        }) as Drawable?
//        return icon
    }
    override fun getName(): String {
        return context.getString(R.string.scrolling_app_name)
    }

    override fun getDescription(): String {
        return context.getString(R.string.scrolling_app_description)
    }

    override fun getScreenshotCount(): Int {
        return 2
    }

    private fun getDrawable(id: Int): Drawable? {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            return context.getDrawable(id)
        return context.resources.getDrawable(id)
    }

    private fun getScreenshot(index: Int): Drawable? {
        return getDrawable(getScreenshotId(index))
    }

    override fun getScreenshotId(index: Int): Int {
        ///var drawable: Drawable? = null
        when (index) {
            0 -> return R.drawable.scrolling_screenshot_0
            1 -> return R.drawable.scrolling_screenshot_1
        }
        return -1
    }


}