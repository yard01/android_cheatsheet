package com.github.yard01.sandbox.navigator_example_bridge
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import com.github.yard01.sandbox.cheatsheet.ExampleBridge
import com.github.yard01.sandbox.cheatsheet.viewmodel.IconProvider
import com.github.yard01.sandbox.navigator_example.MainActivity

class NavigatorExampleBridge(var context: Context) : ExampleBridge, IconProvider {

    override fun executeExample() {
        val intent1: Intent = Intent().apply {
            //addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            setComponent(ComponentName("com.github.yard01.androidcheatsheet","com.github.yard01.sandbox.navigator_example.MainActivity"))
        }
        val intent2: Intent = Intent().apply {
            //addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            setClassName("com.github.yard01.sandbox.navigator_example","MainActivity")
            //setComponent(ComponentName("com.github.yard01.sandbox.navigator_example","MainActivity"))
        }

        val intent = Intent(context, MainActivity::class.java).apply {
        //    putExtra(ID_PARAMETER, "VALUE")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent1)
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