package com.github.yard01.sandbox.cheatsheet.viewmodel

import androidx.paging.PagedList
import com.github.yard01.sandbox.cheatsheet.CheetSheetPage
import io.reactivex.Observable

class CheatSheetViewModel {
    companion object {
        //debug! must delete
        var exampleRows: Array<CheatSheetExampleRow> = emptyArray()

        var page: CheetSheetPage = CheetSheetPage()
        var rows: Observable<PagedList<CheatSheetExampleRow>>? = null
        var contentDataSource: ContentDataSource? = null

        fun setContentStorage(storage: ContentStorage) {
            contentDataSource = ContentDataSource(storage)
        }
//        var exampleStorage
    }
}