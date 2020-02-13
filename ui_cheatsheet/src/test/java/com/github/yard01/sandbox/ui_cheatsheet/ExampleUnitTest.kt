package com.github.yard01.sandbox.ui_cheatsheet


import org.json.JSONObject
import org.junit.Test

import org.junit.Assert.*
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val jsonContent = JSONObject("{rows:[\n" +
                "            {id:0,\n" +
                "                bridges:[{com.github.yard01.sandbox.navigator_example_bridge.NavigatorExampleBridge}]\n" +
                "            },\n" +
                "            {id:1,\n" +
                "                bridges:[{com.github.yard01.sandbox.navigator_example_bridge.NavigatorExampleBridge}]\n" +
                "            }\n" +
                "            ]\n" +
                "        }")
        //println(jsonContent["rows"])

        //val js = JSONObject.quote ("{\"rows\":5}")

        val a = JSONObject()
        a.put("aaaa", "dddd")

       // Gson().fromJson<>()

        //a.has("sss")
        //js. .getString("rows")
        //println(jsonContent.getJSONArray("rows").length())

        assertEquals(4, 2 + 2)
    }
}
