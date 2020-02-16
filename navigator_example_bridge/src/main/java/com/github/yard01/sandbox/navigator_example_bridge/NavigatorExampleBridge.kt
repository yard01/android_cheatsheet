package com.github.yard01.sandbox.navigator_example_bridge

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.github.yard01.sandbox.cheatsheet.ExampleBridge
import com.github.yard01.sandbox.cheatsheet.viewmodel.IconProvider
import com.github.yard01.sandbox.navigator_example.MainActivity

class NavigatorExampleBridge(var context: Context) : ExampleBridge, IconProvider {
    /*
    override var link: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}

    override fun loadDescription(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadInfo(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadIcon(): Bitmap {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
*/
    override fun executeExample() {
        val intent = Intent(context, MainActivity::class.java).apply {
        //    putExtra(ID_PARAMETER, "VALUE")
        }
        context.startActivity(intent)
    }

    override fun getIcon(): Bitmap {
        return BitmapFactory.decodeResource(context.resources, R.drawable.ic_launcher_foreground)
    }


}