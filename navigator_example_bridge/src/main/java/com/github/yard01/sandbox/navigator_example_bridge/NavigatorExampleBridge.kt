package com.github.yard01.sandbox.navigator_example_bridge

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.github.yard01.sandbox.cheatsheet.ExampleBridge
import com.github.yard01.sandbox.cheatsheet.viewmodel.IconProvider
import com.github.yard01.sandbox.cheatsheet.viewmodel.ScreenshotProvider
import com.github.yard01.sandbox.navigator_example.MainActivity

class NavigatorExampleBridge(var context: Context) : ExampleBridge, IconProvider, ScreenshotProvider {
    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    override fun executeExample() {
        val intent = Intent(context, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }

    //@SuppressLint("RestrictedApi")
    private fun getIcon(): Drawable? {
        //AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        //returAppCompatDrawableManager.get().getDrawable(context, R.drawable.scrolling_icon_background)
        //return ContextCompat.getDrawable(context, R.drawable.scrolling_icon_foreground)// .scrolling_icon_background)
        return getVectorDrawable(R.drawable.navigator_fgr)
        //return setVectorForPreLollipop(R.drawable.scrolling_icon_foreground, context)
    }

    override fun getIconId(): Int {
        return R.drawable.navigator_drawer_icon // .navigator_fgr
    }

    private fun getIconBackground(): Drawable? {
        return null//getVectorDrawable(R.drawable.navigator_bckg)
    }

    override fun getIconBackgroundId(): Int {
        return R.drawable.navigator_drawer_icon // .navigator_bckg
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
        return context.getString(R.string.navigator_app_name) //context.getString(R.string.navigator_app_name)
    }

    override fun getDescription(): String {
        return context.getString(R.string.navigator_app_description)
    }

    private fun getDrawable(id: Int): Drawable? {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            return context.getDrawable(id)
        return context.resources.getDrawable(id)
    }

    override fun getScreenshotCount(): Int = 3

    private fun getScreenshot(index: Int): Drawable? {
        return getDrawable(getScreenshotId(index))
//        return context.getDrawable(R.drawable.ic_menu_gallery)!!
        //Array<Drawable?>?
    }

    override fun getScreenshotId(index: Int): Int {
        when (index) {
            0 -> return R.drawable.navigator_screenshot_1
            1 -> return R.drawable.navigator_screenshot_2
            2 -> return R.drawable.navigator_screenshot_3
        }
        return -1
//        return context.getDrawable(R.drawable.ic_menu_gallery)!!
        //Array<Drawable?>?
    }

}