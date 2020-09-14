package com.github.yard01.sandbox.map_cheatsheet

import android.content.Context
import com.github.yard01.map_cheatsheet.R
import com.github.yard01.sandbox.cheatsheet.CheatSheetProvider
import com.github.yard01.sandbox.cheatsheet.viewmodel.CheatSheetViewModel
import com.github.yard01.sandbox.cheatsheet.viewmodel.JSONContentStorage
import org.json.JSONObject

class MapCheatsheetProvider (override val context: Context) : CheatSheetProvider {

    override fun provide() {
        CheatSheetViewModel.setContentStorage(
            JSONContentStorage(
                context,
                context.resources.getStringArray(R.array.map_examples),
                JSONObject(context.getString(R.string.map_content))
            )
        )
    }
}