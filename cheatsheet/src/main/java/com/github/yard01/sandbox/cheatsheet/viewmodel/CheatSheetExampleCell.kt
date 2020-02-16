package com.github.yard01.sandbox.cheatsheet.viewmodel

import android.graphics.Bitmap
import com.github.yard01.sandbox.cheatsheet.ExampleBridge

class CheatSheetExampleCell(bridge: ExampleBridge) {
    var info = ""
    var bitmap: Bitmap? = null
    val iconProvider = bridge::getIcon
    init {
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

