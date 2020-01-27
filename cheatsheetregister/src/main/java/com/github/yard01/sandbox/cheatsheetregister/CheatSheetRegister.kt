package com.github.yard01.sandbox.cheatsheetregister

import android.content.Context
import com.github.yard01.sandbox.cheatsheet.CheatSheetProviderFactory

class CheatSheetRegister {
    companion object {
        public fun getProviderFactory(context : Context, factoryName : String) : CheatSheetProviderFactory {
            return CheatSheetRegister.javaClass.classLoader?.loadClass(factoryName)?.getConstructor(Context::class.java)?.newInstance(context) as CheatSheetProviderFactory
        }
    }
}