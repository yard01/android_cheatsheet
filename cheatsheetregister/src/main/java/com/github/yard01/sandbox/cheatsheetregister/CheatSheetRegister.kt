package com.github.yard01.sandbox.cheatsheetregister

import com.github.yard01.sandbox.cheatsheet.CheatSheetProviderFactory

class CheatSheetRegister {
    companion object {
        public fun getProviderFactory(factoryName : String) : CheatSheetProviderFactory {
            return CheatSheetRegister.javaClass.classLoader?.loadClass(factoryName)?.newInstance() as CheatSheetProviderFactory
        }
    }
}