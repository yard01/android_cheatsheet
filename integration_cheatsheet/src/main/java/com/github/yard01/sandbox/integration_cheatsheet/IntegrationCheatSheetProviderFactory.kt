package com.github.yard01.sandbox.integration_cheatsheet

import android.content.Context
import com.github.yard01.integration_cheatsheet.R
import com.github.yard01.sandbox.cheatsheet.CheatSheetProvider
import com.github.yard01.sandbox.cheatsheet.CheatSheetProviderFactory

class IntegrationCheatSheetProviderFactory(override val context : Context): CheatSheetProviderFactory {
    override val id: String = this.javaClass.name
    override val providerClassName: String =
        "com.github.yard01.sandbox.integration_cheatsheet.IntegrationCheatSheetProvider"
    override val descriptionString: String = context.getString(R.string.integration_cheatsheet_app_name)

    override fun createProvider(): CheatSheetProvider {
        return Class.forName(providerClassName).getConstructor(Context::class.java)
            .newInstance(context) as CheatSheetProvider
        //UICheatSheetProvider(context) //UICheatSheetProviderFactory::class.java.javaClass.classLoader?.loadClass(providerClassName)?.getConstructor(Context::class.java)?.newInstance(context) as CheatSheetProvider
    }

}