package com.example.kon.photo

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.Bitmap
import android.media.VolumeShaper
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.util.Config
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.IOException
import java.net.URI
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val EXTRAFILENAME = "extra_filename"
        const val EXTRAUSERNAME = "extra_username"
    }

    val REQUEST_IMAGE_CAPTURE: Int = 1;
    var currentPhotoPath: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        val locale = Locale("ru")
        Locale.setDefault(locale)
        var cfg: Configuration = baseContext.resources.configuration
        cfg.setLocale(locale)
        baseContext.resources.updateConfiguration(cfg, baseContext.resources.displayMetrics)
        */

        setContentView(R.layout.activity_main)
    }

    fun startCamera(v: View) {
        if(editText.text.length < 1)
            Toast.makeText(this, getString(R.string.empty_name), Toast.LENGTH_SHORT).show()
        else {
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                takePictureIntent.resolveActivity(packageManager)?.also {
                    val file: File? = try {
                        createImageFile()
                    } catch (e: Exception) {
                        Log.d("exception", "")
                        e.printStackTrace()
                        null
                    }
                    file?.also {
                        val photoUri: Uri = FileProvider.getUriForFile(this, "com.exemple.android.flleprovider", it)
                            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                    }
                }

            }
        }
    }



    @Throws(IOException::class)
    fun createImageFile(): File {
        val name: String = SimpleDateFormat("yyyyMMDD_HHmmss").format(Date())
        val dir: File = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${name}_", ".jpg", dir).apply{
            currentPhotoPath = absolutePath
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {

            val file = File(currentPhotoPath)
            if(file.length() < 1) {
                Toast.makeText(this, R.string.make_photo_btn, Toast.LENGTH_SHORT).show()
                return
            }


            val intent = Intent(this, PhotoResult::class.java)
            intent.putExtra(EXTRAFILENAME, currentPhotoPath)
            intent.putExtra(EXTRAUSERNAME, editText.text.toString())
            startActivity(intent)
        }
    }
}
