package com.github.yard01.androidcheatsheet

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.yard01.sandbox.cheatsheet.CheatSheetProviderFactory
import com.github.yard01.sandbox.cheatsheetregister.CheatSheetRegister

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.

        val appContext : Context = InstrumentationRegistry.getInstrumentation().targetContext
        val arr  = appContext.resources.getStringArray(R.array.cheatsheet_list)

        val factory : CheatSheetProviderFactory = CheatSheetRegister.getProviderFactory(appContext, arr[0])
        System.out.println("" + factory)
        assertEquals("com.github.yard01.androidcheatsheet", appContext.packageName)
    }
}
