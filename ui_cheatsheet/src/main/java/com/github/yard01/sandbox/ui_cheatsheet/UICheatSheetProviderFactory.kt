package com.github.yard01.sandbox.ui_cheatsheet

import android.content.Context
import android.util.Log
import com.github.yard01.sandbox.cheatsheet.CheatSheetProvider
import com.github.yard01.sandbox.cheatsheet.CheatSheetProviderFactory
import com.github.yard01.sandbox.navigator_example_bridge.NavigatorExampleBridge

class UICheatSheetProviderFactory (override val context : Context): CheatSheetProviderFactory {
    override val id: String = this.javaClass.name
    override val providerClassName: String = "com.github.yard01.sandbox.ui_cheatsheet.UICheatSheetProvider"
    override val descriptionString: String = context.getString(R.string.ui_cheatsheet_app_name)

    override fun createProvider(): CheatSheetProvider {
        return Class.forName(providerClassName).getConstructor(Context::class.java).newInstance(context) as CheatSheetProvider
        //UICheatSheetProvider(context) //UICheatSheetProviderFactory::class.java.javaClass.classLoader?.loadClass(providerClassName)?.getConstructor(Context::class.java)?.newInstance(context) as CheatSheetProvider
    }

    init {

    }

}