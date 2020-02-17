package com.github.yard01.sandbox.navigator_example_bridge
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import com.github.yard01.sandbox.cheatsheet.ExampleBridge
import com.github.yard01.sandbox.cheatsheet.viewmodel.IconProvider
import com.github.yard01.sandbox.navigator_example.MainActivity

class NavigatorExampleBridge(var context: Context) : ExampleBridge, IconProvider {

    override fun executeExample() {
        val intent = Intent(context, MainActivity::class.java).apply {
        //    putExtra(ID_PARAMETER, "VALUE")
        }
        context.startActivity(intent)
    }

    override fun getIcon(): Drawable? {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            return context.getDrawable(R.drawable.ic_launcher_background)
        return context.resources.getDrawable(R.drawable.ic_launcher_background)
    }

    override fun getInfo(): String {
        return context.getString(R.string.navigator_app_name)
    }


}