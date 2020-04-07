package com.github.yard01.androidcheatsheet

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.github.yard01.androidcheatsheet.ui.CheatSheetFragment
import com.github.yard01.sandbox.cheatsheet.CheatSheetProvider
import kotlinx.android.synthetic.main.activity_cheatsheet_content.*
import kotlinx.android.synthetic.main.cheatsheet_fragment.*

class CheatSheetContentActivity : AppCompatActivity() {
    companion object {
        val PROVIDER_ID_PARAMETER = "PROVIDER_ID"
        val FRAGMENT_TAG = "FRAGMENT_TAG"
    }

    private var provider : CheatSheetProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheatsheet_content)
        savedInstanceState?.getString(PROVIDER_ID_PARAMETER)
        provider = MainActivity.currentFactory?.createProvider()
        var fragment = supportFragmentManager.findFragmentByTag(FRAGMENT_TAG)
        if (fragment == null) fragment = CheatSheetFragment()
        supportFragmentManager.beginTransaction().replace(
            R.id.cheatsheet_container,
            fragment
        ).commit()

    }

}
