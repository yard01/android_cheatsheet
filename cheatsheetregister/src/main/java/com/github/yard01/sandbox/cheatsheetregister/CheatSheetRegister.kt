package com.github.yard01.sandbox.cheatsheetregister

import android.content.Context
import com.github.yard01.sandbox.cheatsheet.CheatSheetProvider
import com.github.yard01.sandbox.cheatsheet.CheatSheetProviderFactory

class CheatSheetRegister {
    companion object {

        private fun createObject(context : Context, className : String): Object = CheatSheetRegister::class.java.classLoader?.loadClass(className)?.getConstructor(Context::class.java)?.newInstance(context) as Object

        fun getProviderFactory(context : Context, factoryName : String) : CheatSheetProviderFactory = createObject(context, factoryName) as CheatSheetProviderFactory

        fun getProvider(context : Context, providerName: String): CheatSheetProvider = createObject(context, providerName) as CheatSheetProvider

        //fun getProvider()
    }
}