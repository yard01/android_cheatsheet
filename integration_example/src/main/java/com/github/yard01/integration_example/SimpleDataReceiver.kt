package com.github.yard01.integration_example

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_data_receiver.*
import kotlinx.android.synthetic.main.recived_picture.view.*
import java.io.InputStream


class SimpleDataReceiver : AppCompatActivity() {

    var adapter: PictureAdapter? = null

    var pictureRecyclerView: RecyclerView? = null

    companion object {
//        val REQUEST_CODE_EXTERNAL_STORAGE_READ = 1
    }

    inner class PictureAdapter(val picturesList: ArrayList<Uri>): RecyclerView.Adapter<PictureAdapter.PictureHolder>() {

        inner class PictureHolder(view: View) : RecyclerView.ViewHolder(view)  {
            val image = view.received_picture_ImageView
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recived_picture, parent, false)
            return PictureHolder(view)
        }

        override fun getItemCount(): Int {
            return picturesList.size
        }

        override fun onBindViewHolder(holder: PictureHolder, position: Int) {
            //checkPermission()
            val uri = picturesList[position]
            var stream: InputStream? = null
            stream = contentResolver.openInputStream(uri)
            val bitmap: Bitmap = BitmapFactory.decodeStream(stream)
            holder.image.setImageBitmap(bitmap)
        }
    }

//    private fun checkPermission() {
//        if (checkCallingOrSelfPermission( android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
//            else
//            requestPermission()
//        ;
//    }
//
//    private fun requestPermission() {
//           // No explanation needed; request the permission
//        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
////                // Show an explanation to the user *asynchronously* -- don't block
////                // this thread waiting for the user's response! After the user
////                // sees the explanation, try again to request the permission.
//        } else {
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf<String>(android.Manifest.permission.READ_EXTERNAL_STORAGE),
//                REQUEST_CODE_EXTERNAL_STORAGE_READ
//            )
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_receiver)//R.layout.activity_simple_data_receiver)

        pictureRecyclerView =  this.picture_recycler_view//findViewById(R.id.picture_recycler_view)

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
            adapter = PictureAdapter(arrayListOf<Uri>(it))
            pictureRecyclerView?.adapter = adapter
            //adapter?.notifyDataSetChanged()
            //val stream = contentResolver.openInputStream(it)
            //val bitmap: Bitmap = BitmapFactory.decodeStream(stream)
            //receivedImage_ImageView.setImageBitmap(bitmap)
        }
    }

    private fun handleSendMultipleImages(intent: Intent) {
        intent.getParcelableArrayListExtra<Parcelable>(Intent.EXTRA_STREAM)?.let {
            // Update UI to reflect multiple images being shared
            //adapter = ArrayAdapter(it.toArray())
            //Log.d("cheatfie_len", " " +it?.size)

            adapter = PictureAdapter(it as ArrayList<Uri>)
            pictureRecyclerView?.adapter = adapter
            //adapter?.notifyDataSetChanged()

            //for (uri in it) {

            //}
        }
    }

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        //super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//
//        when (requestCode) {
//            REQUEST_CODE_EXTERNAL_STORAGE_READ -> {
//
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.size > 0
//                    && grantResults[0] === PackageManager.PERMISSION_GRANTED
//                ) {
//                    // permission was granted, yay! Do the
//                    // contacts-related task you need to do.
//                    //isForceLocationRequest = false;
//                    //createContent()
//                } else {
//                    // permission denied, boo! Disable the
//                    // functionality that depends on this permission.
//                    //finish();
//                }
//                return
//            }
//        }
//    }
}
