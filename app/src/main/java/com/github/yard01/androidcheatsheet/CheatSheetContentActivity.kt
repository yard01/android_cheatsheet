package com.github.yard01.androidcheatsheet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.github.yard01.sandbox.cheatsheet.CheatSheetProvider

class CheatSheetContentActivity : AppCompatActivity() {
    companion object {
        val PROVIDER_ID_PARAMETER = "PROVIDER_ID"
    }

    private var provider : CheatSheetProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheatsheet_content)
        savedInstanceState?.getString(PROVIDER_ID_PARAMETER)
        provider = MainActivity.currentFactory?.createProvider()
        supportFragmentManager.beginTransaction().replace(
            R.id.cheatsheet_container,
            provider!!.fragment
        ).commit()
        //FragmentManager

        /*
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/
    }
}
