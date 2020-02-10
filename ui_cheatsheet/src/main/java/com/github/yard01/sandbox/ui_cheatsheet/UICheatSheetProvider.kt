package com.github.yard01.sandbox.ui_cheatsheet
import android.content.Context
import androidx.fragment.app.Fragment

import com.github.yard01.sandbox.cheatsheet.CheatSheetProvider
import com.github.yard01.sandbox.cheatsheet.viewmodel.CheatSheetExampleCell
import com.github.yard01.sandbox.cheatsheet.viewmodel.CheatSheetViewModel
import com.github.yard01.sandbox.ui_cheatsheet.ui.UICheatSheetFragment

class UICheatSheetProvider(override val context: Context) : CheatSheetProvider {

    override val fragment: Fragment = UICheatSheetFragment()

    override fun provide() {
        var cell = CheatSheetExampleCell()
        //var a: LivePagedListBuilder? = null
    }

    init {
        //fragment = UICheatSheetFragment()
    }

}