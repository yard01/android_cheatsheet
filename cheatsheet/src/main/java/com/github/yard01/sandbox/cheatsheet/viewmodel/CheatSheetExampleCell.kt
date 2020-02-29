package com.github.yard01.sandbox.cheatsheet.viewmodel

import android.graphics.Bitmap
import com.github.yard01.sandbox.cheatsheet.ExampleBridge
import java.util.function.Supplier

class CheatSheetExampleCell(var bridge: ExampleBridge) {
    var info = ""
    var bitmap: Bitmap? = null
    //val iconProvider: KFunction = bridge::getIcon
    init {

        //iconProvider.getIcon()
        //var iconProvider: IconProvider = { context: Context -> bridge.loadIcon(context) }
        //iconPovider = { context: Context -> bridge.loadIcon(context) }
        //iconPovider.
    }

    internal interface FI {
        fun myFunc(a: String)
    }

    fun test() {
        //val t = bridge::
        //val fi = object: FI { s: String? -> println(s) }
    }
}

