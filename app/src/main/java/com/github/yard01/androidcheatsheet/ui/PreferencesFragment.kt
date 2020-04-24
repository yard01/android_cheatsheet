package com.github.yard01.androidcheatsheet.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.github.yard01.androidcheatsheet.R
import com.github.yard01.sandbox.cheatsheet.viewmodel.CheatSheetViewModel
import kotlinx.android.synthetic.main.cheatsheet_fragment.*

class PreferencesFragment: PreferenceFragmentCompat() {

    private var preferences: SharedPreferences? = null
    private var changeListener : SharedPreferences.OnSharedPreferenceChangeListener? = null

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
        preferences = PreferenceManager.getDefaultSharedPreferences(this.activity)
    }

    private fun setSummaryValues() {
        this.findPreference(this.getString(R.string.cheatsheet_text_filter_Key)).summary = preferences?.getString(this.getString(R.string.cheatsheet_text_filter_Key),"")
    }

    override fun onStart() {
        super.onStart()
        setSummaryValues()
        changeListener =  SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->

            when (key) {
                this.getString(R.string.cheatsheet_text_filter_Key) -> {
                        val filter = sharedPreferences.getString(key, "")
                        this.findPreference(this.getString(R.string.cheatsheet_text_filter_Key)).summary = filter
                        CheatSheetViewModel.filter = ""+filter
                        CheatSheetViewModel.search = CheatSheetViewModel.filter
                    }

            }

        }
        preferences?.registerOnSharedPreferenceChangeListener(changeListener)

    }

    override fun onResume() {
        super.onResume()
        setSummaryValues()
    }

    override fun onPause() {
        super.onPause()
        preferences?.unregisterOnSharedPreferenceChangeListener(changeListener)
    }
}