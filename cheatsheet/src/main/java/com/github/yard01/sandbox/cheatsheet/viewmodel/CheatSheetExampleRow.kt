package com.github.yard01.sandbox.cheatsheet.viewmodel

import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.github.yard01.sandbox.cheatsheet.ExampleBridge
import io.reactivex.Observable
import java.util.*

class CheatSheetExampleRow {
    var title = ""

    private var bridges: List<ExampleBridge> = emptyList()

    fun setBridges(bridges: List<ExampleBridge>) {
        this.bridges = bridges

    }

    val cellSource = object: PositionalDataSource<CheatSheetExampleCell>(){
        override fun loadInitial(
            params: LoadInitialParams,
            callback: LoadInitialCallback<CheatSheetExampleCell>
        ) {
            val result: List<CheatSheetExampleCell> = getCells(params.requestedStartPosition, params.requestedLoadSize)
            callback.onResult(result, params.requestedStartPosition)
        }

        override fun loadRange(
            params: LoadRangeParams,
            callback: LoadRangeCallback<CheatSheetExampleCell>
        ) {
            val result: List<CheatSheetExampleCell> = getCells(params.startPosition, params.loadSize)
            callback.onResult(result)
        }
    }


    fun getCells(position: Int, size: Int): List<CheatSheetExampleCell> {
        var len = size
        if (position + size >= bridges.size ) len = bridges.size - position - 1
        var result = ArrayList<CheatSheetExampleCell>() // = arrayOfNulls<CheatSheetExampleCell>(len)

        //var result: List<CheatSheetExampleCell> = List(2) {}//liArray<CheatSheetExampleCell?> = arrayOfNulls<CheatSheetExampleCell>(len)

        for (i in position..position + len) {
            val cell = CheatSheetExampleCell(bridges[i])
            cell.bridge.getIcon() // .iconProvider.call()
            result.add(cell)
        }

        return result.toList()
    }

}