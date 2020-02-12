package com.github.yard01.sandbox.navigator_example_bridge

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import com.github.yard01.sandbox.cheatsheet.ExampleBridge
import com.github.yard01.sandbox.navigator_example.MainActivity

class NavigatorExampleBridge() : ExampleBridge {
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
    override fun executeExample(context: Context) {
        val intent = Intent(context, MainActivity::class.java).apply {
        //    putExtra(ID_PARAMETER, "VALUE")
        }
        context.startActivity(intent)
    }
}