package com.github.yard01.sandbox.map_cheatsheet

import android.content.Context
import com.github.yard01.map_cheatsheet.R
import com.github.yard01.sandbox.cheatsheet.CheatSheetProvider
import com.github.yard01.sandbox.cheatsheet.CheatSheetProviderFactory

class MapCheatsheetProviderFactory (override val context : Context): CheatSheetProviderFactory {
    override val id: String = this.javaClass.name
    override val providerClassName: String = "com.github.yard01.sandbox.map_cheatsheet.MapCheatsheetProvider"
    override val descriptionString: String = context.getString(R.string.map_cheatsheet_app_name)

    override fun createProvider(): CheatSheetProvider {
        return Class.forName(providerClassName).getConstructor(Context::class.java).newInstance(context) as CheatSheetProvider
        //UICheatSheetProvider(context) //UICheatSheetProviderFactory::class.java.javaClass.classLoader?.loadClass(providerClassName)?.getConstructor(Context::class.java)?.newInstance(context) as CheatSheetProvider
    }

    init {

    }

}