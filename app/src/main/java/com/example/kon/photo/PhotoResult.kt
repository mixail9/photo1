package com.example.kon.photo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat.getExtras
import android.support.v4.content.FileProvider
import android.util.Log
import kotlinx.android.synthetic.main.activity_photo_result.*
import java.io.File

class PhotoResult : AppCompatActivity() {

    lateinit var img: File
    lateinit var userName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_result)

        if(savedInstanceState == null) {
            val bundle: Bundle? = intent.extras
            val keys: Set<String>? = bundle?.keySet()
            if (keys != null) {
                for (key: String in keys) {
                    Log.d("extras r k ", key)
                    Log.d("extras r v ", bundle.get(key).toString())
                }
            }
            img = File(bundle?.get(MainActivity.EXTRAFILENAME) as String)
            userName = bundle.get(MainActivity.EXTRAUSERNAME) as String
        }

        textView.text = userName
        imageView.setImageURI(FileProvider.getUriForFile(this, "com.exemple.android.flleprovider", img))
    }
}
