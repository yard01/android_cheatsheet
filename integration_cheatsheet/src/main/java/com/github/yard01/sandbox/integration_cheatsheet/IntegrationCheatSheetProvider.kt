package com.github.yard01.sandbox.integration_cheatsheet

import android.content.Context
import com.github.yard01.integration_cheatsheet.R
import com.github.yard01.sandbox.cheatsheet.CheatSheetProvider
import com.github.yard01.sandbox.cheatsheet.viewmodel.CheatSheetViewModel
import com.github.yard01.sandbox.cheatsheet.viewmodel.JSONContentStorage
import org.json.JSONObject

class IntegrationCheatSheetProvider(override val context: Context) : CheatSheetProvider {
    override fun provide() {
        CheatSheetViewModel.setContentStorage(
            JSONContentStorage(
                context,
                context.resources.getStringArray(R.array.integration_examples),
                JSONObject(context.getString(R.string.integration_content))
            )
        )
    }
}