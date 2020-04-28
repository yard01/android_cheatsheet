package com.github.yard01.sandbox.sensor_cheatsheet

import android.content.Context
import androidx.fragment.app.Fragment
import com.github.yard01.sandbox.cheatsheet.CheatSheetProvider
import com.github.yard01.sandbox.cheatsheet.viewmodel.CheatSheetViewModel
import com.github.yard01.sandbox.sensor_cheatsheet.ui.SensorCheatSheetFragment

class SensorCheatSheetProvider(override val context: Context) : CheatSheetProvider {
    override val fragment: Fragment = SensorCheatSheetFragment()

    override fun provide() {
        CheatSheetViewModel.setContentStorage(SensorCheatSheetStorage(context))

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