package com.github.yard01.sandbox.cheatsheet.viewmodel

import androidx.paging.PagedList
import io.reactivex.Observable

class CheatSheetExampleRow {
    var titleId = -1
    var examples: Array<CheatSheetExampleCell> = emptyArray()
    var cells: Observable<PagedList<CheatSheetExampleCell>>? = null
}