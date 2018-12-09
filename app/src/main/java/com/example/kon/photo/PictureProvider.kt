package com.example.kon.photo

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri


class PictureProvider: ContentProvider() {

    companion object {
        const val AUTHORITY = "com.example.photo.provider"
    }

    private val myUriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI(AUTHORITY, "table1", 1)
        addURI(AUTHORITY, "table1/#", 2)
    }

    override fun onCreate(): Boolean {

    }

    override fun getType(uri: Uri?): String {

    }

    override fun query(uri: Uri?, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor? {
        var localOrder: String = sortOrder ?: "ID"
        var localSelection: String = selection ?: "ID"

    }

    override fun insert(uri: Uri?, values: ContentValues?): Uri {

    }

    override fun update(uri: Uri?, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {

    }

    override fun delete(uri: Uri?, selection: String?, selectionArgs: Array<out String>?): Int {

    }
}
