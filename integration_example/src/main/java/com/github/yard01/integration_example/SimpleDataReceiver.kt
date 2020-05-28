package com.github.yard01.integration_example

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_simple_data_receiver.*
import java.net.URI

class SimpleDataReceiver : AppCompatActivity() {

    var adapter: ArrayAdapter<URI>? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_data_receiver)
        when {
            intent?.action == Intent.ACTION_SEND -> {
                if ("text/plain" == intent.type) {
                    handleSendText(intent) // Handle text being sent
                } else if (intent.type?.startsWith("image/") == true) {
                    handleSendImage(intent) // Handle single image being sent
                }
            }
            intent?.action == Intent.ACTION_SEND_MULTIPLE
                    && intent.type?.startsWith("image/") == true -> {
                handleSendMultipleImages(intent) // Handle multiple images being sent
            }
            else -> {
                // Handle other intents, such as being started from the home screen
            }
        }

    }

    private fun handleSendText(intent: Intent) {
        //intent.getStringExtra(Intent.EXTRA_TEXT)?.let { //НЕ РАБОТАЕТ!!! возвращает null
        intent.extras?.get(Intent.EXTRA_TEXT)?.let {
            // Update UI to reflect text being shared
            val contextView: View = findViewById(android.R.id.content)
            Snackbar.make(contextView, "Received: " + it, Snackbar.LENGTH_LONG)
                .show();
        }
    }

    private fun handleSendImage(intent: Intent) {
        (intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as? Uri)?.let {
            // Update UI to reflect image being shared
            val stream = contentResolver.openInputStream(it)
            val bitmap: Bitmap = BitmapFactory.decodeStream(stream)
            receivedImage_ImageView.setImageBitmap(bitmap)
        }
    }

    private fun handleSendMultipleImages(intent: Intent) {
        intent.getParcelableArrayListExtra<Parcelable>(Intent.EXTRA_STREAM)?.let {
            // Update UI to reflect multiple images being shared
            //adapter = ArrayAdapter(it.toArray())
            for (uri in it) {

            }
        }
    }

}
