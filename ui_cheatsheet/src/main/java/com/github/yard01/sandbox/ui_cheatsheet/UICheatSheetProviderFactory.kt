package com.github.yard01.sandbox.ui_cheatsheet

import android.content.Context
import com.github.yard01.sandbox.cheatsheet.CheatSheetProviderFactory

class UICheatSheetProviderFactory (override val context : Context) : CheatSheetProviderFactory {
    override val id: String = this.javaClass.name
    override val providerClassName: String = ""
    override val descriptionString: String = context.getString(R.string.ui_cheatsheet_app_name)
    init {

    }
    //com.github.yard01.sandbox.ui_cheatsheet.UICheatSheetProviderFactory"
}