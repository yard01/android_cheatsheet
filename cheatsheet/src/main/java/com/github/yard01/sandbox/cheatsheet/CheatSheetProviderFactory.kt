package com.github.yard01.sandbox.cheatsheet

import android.content.Context

interface CheatSheetProviderFactory {
    val fragmentClassName : String
    val descriptionString : String
    val context : Context


}
