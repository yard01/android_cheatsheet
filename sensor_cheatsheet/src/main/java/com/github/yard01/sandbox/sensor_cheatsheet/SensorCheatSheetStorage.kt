package com.github.yard01.sandbox.sensor_cheatsheet

import android.content.Context
import android.os.Build
import android.text.Html
import androidx.annotation.RequiresApi
import com.github.yard01.sandbox.cheatsheet.ExampleBridge
import com.github.yard01.sandbox.cheatsheet.viewmodel.CheatSheetExampleRow
import com.github.yard01.sandbox.cheatsheet.viewmodel.CheatSheetViewModel
import com.github.yard01.sandbox.cheatsheet.viewmodel.ContentStorage
import com.github.yard01.sensor_cheatsheet.R
import org.json.JSONObject

class SensorCheatSheetStorage (var context: Context): ContentStorage {
    var headers: Array<String> = emptyArray();
    val jsonContent: JSONObject
    companion object {
        private val JSON_ID = "id"
        private val JSON_ROWS = "rows"
        private val JSON_BRIDGES = "bridges"
    }
    init {
        headers = context.resources.getStringArray(R.array.sensor_examples)
        jsonContent = JSONObject(context.getString(R.string.sensor_content))
        //Log.d("rows", " " + jsonContent.getJSONArray("rows").length())
        //context.resources
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun getData(position: Int, size: Int): List<CheatSheetExampleRow> {
        var len = size
        var result: ArrayList<CheatSheetExampleRow> = ArrayList()

        if (position + size >= headers.size) len =  headers.size - position - 1

        //var fr: FabfilterExampleBridge? = null

        for (i in position..len + position) {
            var row = CheatSheetExampleRow()
            row.title = headers[i]

            val bridges = ArrayList<ExampleBridge>()

            val jsonRows = jsonContent.getJSONArray(JSON_ROWS)

            for (j in 0..jsonRows.length() - 1) {
                val row: JSONObject = jsonRows.getJSONObject(j)
                if (row.getInt(JSON_ID) == i) {
                    val jsonBridges = row.getJSONArray(JSON_BRIDGES)
                    for ( bridge in 0..jsonBridges.length() - 1) {
                        val bridgeName = jsonBridges.getString(bridge)
                        val exampleBridge = Class.forName(bridgeName).getConstructor(Context::class.java).newInstance(context) as ExampleBridge
                        var description = ""
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                            description = Html.fromHtml(exampleBridge.getDescription(), Html.FROM_HTML_MODE_COMPACT).toString()
                        else
                            description = Html.fromHtml(exampleBridge.getDescription()).toString()


                        if (description.indexOf(CheatSheetViewModel.filter) >=0)
                            bridges.add(exampleBridge)
                    }
                }
            }
            row.setBridges(bridges)
            result.add(row)
        }
        return result
    }

}