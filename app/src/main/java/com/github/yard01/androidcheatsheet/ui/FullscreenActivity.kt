package com.github.yard01.androidcheatsheet.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import com.github.yard01.androidcheatsheet.R
import kotlinx.android.synthetic.main.activity_fullscreen.*

val DRAWABLE_ID = "DRAWABLE_ID"

class FullscreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val screenshot_id = this.intent.getIntExtra(DRAWABLE_ID, -1)
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_fullscreen)
        if (screenshot_id != null)
            this.imageView.setImageResource(screenshot_id)
    }
}
