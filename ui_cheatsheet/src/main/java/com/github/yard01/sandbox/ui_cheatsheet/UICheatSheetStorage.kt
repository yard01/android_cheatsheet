package com.github.yard01.sandbox.ui_cheatsheet

import android.content.Context
import com.github.yard01.sandbox.cheatsheet.viewmodel.CheatSheetExampleRow
import com.github.yard01.sandbox.cheatsheet.viewmodel.ContentStrorage
import org.json.JSONObject

class UICheatSheetStorage(var context: Context): ContentStrorage {
    var headers: Array<String> = emptyArray();

    init {
        headers = context.resources.getStringArray(R.array.ui_examples)
//        val jsonContent = JSONObject(context.getString(R.string.ui_content))
        //context.resources
    }


    override fun getData(position: Int, size: Int): List<CheatSheetExampleRow> {
        var len = size
        var result: ArrayList<CheatSheetExampleRow> = ArrayList()
        //if position
        if (position + size >= headers.size) len =  headers.size - position - 1

//            result[1] = CheatSheetExampleRow();
            var list: List<String>

            for (i in position..len + position) {
                var row = CheatSheetExampleRow()

                result.add(row)
            }


        return result
    }
}