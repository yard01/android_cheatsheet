package com.github.yard01.sandbox.cheatsheet.viewmodel

import androidx.paging.PagedList
import com.github.yard01.sandbox.cheatsheet.ExampleBridge
import io.reactivex.Observable

class CheatSheetExampleRow {
    var title = ""

    private var bridges: List<ExampleBridge> = emptyList()

    fun setBridges(bridges: List<ExampleBridge>) {
        this.bridges = bridges
    }

    fun getCells(position: Int, size: Int): List<CheatSheetExampleCell> {
        var len = size
        if (position + size >= bridges.size ) len = bridges.size - position - 1
        //var result:  Array<CheatSheetExampleCell?> = arrayOfNulls<CheatSheetExampleCell>(len)
        var result: List<CheatSheetExampleCell> = emptyList() //liArray<CheatSheetExampleCell?> = arrayOfNulls<CheatSheetExampleCell>(len)

        //for (i in position..position + len) {
        //    result
        //}
        return result
    }
}