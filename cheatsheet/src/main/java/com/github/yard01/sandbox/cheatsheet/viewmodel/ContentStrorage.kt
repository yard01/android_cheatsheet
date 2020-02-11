package com.github.yard01.sandbox.cheatsheet.viewmodel

interface ContentStrorage {
    fun getData(position: Int, size: Int): List<CheatSheetExampleRow>
}