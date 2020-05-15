package com.github.yard01.integration_example.providers

import android.content.ContentProvider
import android.content.ContentValues
import android.content.res.AssetFileDescriptor
import android.database.Cursor
import android.net.Uri
import com.github.yard01.integration_example.IntegrationActivity
import java.io.FileNotFoundException
import java.io.IOException

class AssetFileProvider : ContentProvider() {

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
       return 0
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun onCreate(): Boolean {
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        return null
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return 0
    }

    override fun openAssetFile(uri: Uri, mode: String): AssetFileDescriptor? {
        //return super.openAssetFile(uri, mode)
        val assetManager = this.context?.assets
        val fileName = uri.lastPathSegment
        if (fileName == null) throw FileNotFoundException()
        try {
            return assetManager?.openFd(fileName);
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }
}
