package com.github.yard01.sandbox.cheatsheet.viewmodel

import androidx.paging.PositionalDataSource


class ContentDataSource(val storage: ContentStrorage): PositionalDataSource<CheatSheetExampleRow>() {

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<CheatSheetExampleRow>
    ) {
        val result: List<CheatSheetExampleRow> = storage.getData(params.requestedStartPosition, params.requestedLoadSize)
        callback.onResult(result, params.requestedStartPosition)
    }

    override fun loadRange(
        params: LoadRangeParams,
        callback: LoadRangeCallback<CheatSheetExampleRow>
    ) {
        val result: List<CheatSheetExampleRow> = storage.getData(params.startPosition, params.loadSize)
        callback.onResult(result)
    }

}