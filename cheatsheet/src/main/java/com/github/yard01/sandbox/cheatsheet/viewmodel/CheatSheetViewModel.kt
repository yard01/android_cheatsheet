package com.github.yard01.sandbox.cheatsheet.viewmodel

import com.github.yard01.sandbox.cheatsheet.CheetSheetPage

class CheatSheetViewModel {
    companion object {
        var exampleRows: Array<CheatSheetExampleRow> = emptyArray()
        var page: CheetSheetPage = CheetSheetPage()
    }
}