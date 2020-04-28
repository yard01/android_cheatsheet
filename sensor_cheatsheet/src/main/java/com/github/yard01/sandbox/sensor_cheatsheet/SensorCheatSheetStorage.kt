package com.github.yard01.sandbox.sensor_cheatsheet

import android.content.Context
import com.github.yard01.sandbox.cheatsheet.viewmodel.CheatSheetExampleRow
import com.github.yard01.sandbox.cheatsheet.viewmodel.ContentStorage

class SensorCheatSheetStorage (var context: Context): ContentStorage {
    override fun getData(position: Int, size: Int): List<CheatSheetExampleRow> {
        TODO("Not yet implemented")
    }

}