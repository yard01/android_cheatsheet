package com.github.yard01.sandbox.sensor_cheatsheet

import android.content.Context
import com.github.yard01.sandbox.cheatsheet.CheatSheetProvider
import com.github.yard01.sandbox.cheatsheet.viewmodel.CheatSheetViewModel
import com.github.yard01.sandbox.cheatsheet.viewmodel.JSONContentStorage
import com.github.yard01.sensor_cheatsheet.R
import org.json.JSONObject

class SensorCheatSheetProvider(override val context: Context) : CheatSheetProvider {
    override fun provide() {
        CheatSheetViewModel.setContentStorage(JSONContentStorage(context,
            context.resources.getStringArray(R.array.sensor_examples),
            JSONObject(context.getString(R.string.sensor_content))
        ))
    }
}