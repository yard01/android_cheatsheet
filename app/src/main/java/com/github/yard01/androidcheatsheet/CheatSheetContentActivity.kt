package com.github.yard01.androidcheatsheet

import android.content.Intent
import android.os.Bundle
import android.util.Log
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

        provider = MainActivity.currentFactory?.createProvider()

        var fragment = this.supportFragmentManager.findFragmentByTag(FRAGMENT_TAG)

        if (fragment == null) {
            fragment = CheatSheetFragment()
            supportFragmentManager.beginTransaction().replace(
                R.id.cheatsheet_container,
                fragment, FRAGMENT_TAG
            ).commit()
        }
    }
}
