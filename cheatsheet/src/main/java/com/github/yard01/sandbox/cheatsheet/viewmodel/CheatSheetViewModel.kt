package com.github.yard01.sandbox.cheatsheet.viewmodel

import androidx.paging.PagedList
import com.github.yard01.sandbox.cheatsheet.CheetSheetPage
import io.reactivex.Observable

class CheatSheetViewModel {

    companion object {
        var filter = ""; // string
        var search = "";
        var exampleRows: Array<CheatSheetExampleRow> = emptyArray() //the array of example rows
        //each row is a set of examples

        var page: CheetSheetPage = CheetSheetPage()
        var rows: Observable<PagedList<CheatSheetExampleRow>>? = null
        var contentDataSource: ContentDataSource? = null

        fun setContentStorage(storage: ContentStorage) {
            contentDataSource = ContentDataSource(storage)
        }
//        var exampleStorage
    }


}