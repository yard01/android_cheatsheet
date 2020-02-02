package com.github.yard01.sandbox.ui_cheatsheet

import android.content.Context
import com.github.yard01.sandbox.cheatsheet.CheatSheetProvider
import com.github.yard01.sandbox.cheatsheet.CheatSheetProviderFactory

class UICheatSheetProviderFactory (override val context : Context): CheatSheetProviderFactory {
    override val id: String = this.javaClass.name
    override val providerClassName: String = "com.github.yard01.sandbox.ui_cheatsheet.UICheatSheetProvider"
    override val descriptionString: String = context.getString(R.string.ui_cheatsheet_app_name)

    override fun createProvider(): CheatSheetProvider {
        return UICheatSheetProvider(context) //UICheatSheetProviderFactory::class.java.javaClass.classLoader?.loadClass(providerClassName)?.getConstructor(Context::class.java)?.newInstance(context) as CheatSheetProvider
    }

    init {

    }

}