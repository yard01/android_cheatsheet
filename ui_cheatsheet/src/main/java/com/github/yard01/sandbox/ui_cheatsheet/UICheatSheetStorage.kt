package com.github.yard01.sandbox.ui_cheatsheet

import android.content.Context
import com.github.yard01.sandbox.cheatsheet.viewmodel.CheatSheetExampleRow
import com.github.yard01.sandbox.cheatsheet.viewmodel.ContentStrorage
import org.json.JSONObject

class UICheatSheetStorage(var context: Context): ContentStrorage {

    init {
        val rows = context.resources.getStringArray(R.array.ui_examples)
//        val jsonContent = JSONObject(context.getString(R.string.ui_content))
        //context.resources
    }

    override fun getData(position: Int, size: Int): List<CheatSheetExampleRow> {

        var result:  ArrayList<CheatSheetExampleRow> = ArrayList<CheatSheetExampleRow>()

        for (i in position .. size + position) {
            var row = CheatSheetExampleRow()
            result.add(row)
        }


        return result
    }
}