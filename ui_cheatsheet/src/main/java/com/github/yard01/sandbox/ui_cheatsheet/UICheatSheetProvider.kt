package com.github.yard01.sandbox.ui_cheatsheet

import android.content.Context

import com.github.yard01.sandbox.cheatsheet.CheatSheetProvider
import com.github.yard01.sandbox.cheatsheet.viewmodel.CheatSheetViewModel
import com.github.yard01.sandbox.cheatsheet.viewmodel.JSONContentStorage
import org.json.JSONObject

class UICheatSheetProvider(override val context: Context) : CheatSheetProvider {

    override fun provide() {
        CheatSheetViewModel.setContentStorage(
            JSONContentStorage(
                context,
                context.resources.getStringArray(R.array.ui_examples),
                JSONObject(context.getString(R.string.ui_content))
            )
        )
      }


}