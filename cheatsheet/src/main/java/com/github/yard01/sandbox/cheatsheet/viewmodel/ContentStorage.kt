package com.github.yard01.sandbox.cheatsheet.viewmodel

interface ContentStorage {
    fun getData(position: Int, size: Int): List<CheatSheetExampleRow>
}