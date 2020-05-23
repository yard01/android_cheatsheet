package com.github.yard01.integration_example

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_send_simple_file.view.*
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.nio.channels.Channels
import java.util.*
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

class SendSimpleFileFragment: Fragment() {
    companion object {
        val AUTHORITY = "integration_example_share_asset_file"
        val ASSET_PROVIDER = Uri.parse("content://"+AUTHORITY)
    }

    private fun compressStream(inp: InputStream?, zos: ZipOutputStream) {
        if (inp != null) {
            val buffer: ByteArray = ByteArray(1024 * 1024)
            do {
                val bytesRead = inp?.read(buffer)
                if (bytesRead > 0) zos.write(buffer, 0, bytesRead) else break;
            } while (bytesRead != -1)
            inp.close()
        }

    }

    private fun compressAsset(context: Context): String {
        if (context == null) return ""
        val zipFileName = "cheatsheet_share_file.zip"
        val outputFile = File( context.cacheDir, zipFileName)
        val fos = FileOutputStream(outputFile)
        val zos = ZipOutputStream(fos)
        zos.putNextEntry(ZipEntry("portrait.jpeg"))
        compressStream(context.assets?.open("jpeg/portrait.jpeg"), zos)
        zos.putNextEntry(ZipEntry("file.pdf"))
        compressStream(context.assets?.open("pdf/send_pdf.pdf"), zos)
        zos.closeEntry()
        zos.close()
        fos.close()
        return zipFileName
    }

    fun createContent(view: View) {
        view.sendTXT_Button.setOnClickListener{button -> run {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, view.textToSend_EditText.text)
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }}

        view.sendJPEG_Button.setOnClickListener{button -> run {

            //Log.d( "cheatfile", "avail: " + view.context.assets?.open("jpeg/portrait.jpeg")?.available())
            val inp = Channels.newChannel(view.context.assets?.open("jpeg/portrait.jpeg"))

            //-- получается файл в 5 Мб!!!
            //val inp = view.context.assets?.openFd("jpeg/portrait.jpeg")?.createInputStream()?.channel
            //////////////////////////////
            if (inp != null) {
                //Log.d( "cheatfile", "size: " + inp.size())

                val outputDir: File = view.context.cacheDir // context being the Activity pointer

                val outputFile: File =   File( outputDir ,"cheatsheet_share_file.jpeg")//File.createTempFile("image_cachecheatsheet_integration", "tmp", outputDir)
                val fileUri = FileProvider.getUriForFile(view.context,  "com.github.yard01.integration_example.fileprovider" , outputFile)

                val out = FileOutputStream(outputFile).channel
                var offset: Long = 0
                val quantum = 1024 * 1024.toLong()
                var count: Long
                count = out.transferFrom(inp, offset, quantum)
                while(count > 0) {
                    offset += count
                    count = out.transferFrom(inp, offset, quantum)
                }
                inp.close()
                out.close()

                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_STREAM, fileUri)
                    type = "image/jpeg"
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)

            }
        }}

        view.sendPDF_Button.setOnClickListener {button -> run {
            //FileProvider.
                //FileProvider.getUriForFile(view.context,  activity!!.packageName, file)
            val uriFile = ASSET_PROVIDER.buildUpon().appendPath("pdf/send_pdf.pdf").build() //"pdf/send_pdf.pdf").build()
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_STREAM, uriFile)
                    putExtra(Intent.EXTRA_TEXT, "PDF File")
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    type = "application/pdf"
            }
            val shareIntent = Intent.createChooser(sendIntent, "Share PDF")
            startActivity(shareIntent)
        }}

        view.sendZIP_Button.setOnClickListener{button -> run {
            val zipFileName = compressAsset(view.context)
            val outputFile = File(view.context.cacheDir, zipFileName)//File.createTempFile("image_cachecheatsheet_integration", "tmp", outputDir)
            val fileUri = FileProvider.getUriForFile(view.context,  "com.github.yard01.integration_example.fileprovider" , outputFile)
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_STREAM, fileUri)
                type = "*/*"
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)

        }}

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_send_simple_file, container,false)
        //view.sensorList_TextView.text = this.context?.getString(R.string.sensorlist_example_name)
        createContent(view)
        return view
    }
}