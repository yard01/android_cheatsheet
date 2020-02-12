package com.github.yard01.sandbox.ui_cheatsheet

import android.content.Context
import androidx.fragment.app.Fragment

import com.github.yard01.sandbox.cheatsheet.CheatSheetProvider
import com.github.yard01.sandbox.cheatsheet.viewmodel.CheatSheetViewModel
import com.github.yard01.sandbox.ui_cheatsheet.ui.UICheatSheetFragment

class UICheatSheetProvider(override val context: Context) : CheatSheetProvider {

    override val fragment: Fragment = UICheatSheetFragment()

    override fun provide() {
        CheatSheetViewModel.setContentStorage(UICheatSheetStorage(context))

//        val pagedList: PagedList<CheatSheetExampleRow> =
//            androidx.paging.PagedList.Builder(dataSource, config)
//                .setFetchExecutor(Executors.newSingleThreadExecutor())
//                .setNotifyExecutor(MainThreadExecutor()) //.setFetchExecutor(Executors.newSingleThreadExecutor())
//.setNotifyExecutor(new MainThreadExecutor())
//.setMainThreadExecutor(new MainThreadExecutor())
//.setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
//                .build()
        //var cell = CheatSheetExampleCell()
        //var a: LivePagedListBuilder? = null
    }

    init {
        //fragment = UICheatSheetFragment()
    }

}