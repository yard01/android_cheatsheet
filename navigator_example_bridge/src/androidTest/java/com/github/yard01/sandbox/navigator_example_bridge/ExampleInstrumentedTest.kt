package com.github.yard01.sandbox.navigator_example_bridge

import android.graphics.drawable.Drawable
import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

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
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        Log.d("drawable", ""+appContext.getDrawable(R.drawable.ic_launcher_foreground))

        assertEquals(
            "com.github.yard01.sandbox.navigator_example_bridge.test",
            appContext.packageName
        )
    }
}
