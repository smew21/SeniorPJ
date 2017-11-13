package com.egco428.camera

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.example.project.seniorpj.R
import com.example.project.seniorpj.Result.ResultActivity

class CameraOneStepActivity : AppCompatActivity() {

    val REQUEST_IMAGE_CAPTURE = 1
    lateinit var photoImageView: ImageView
    lateinit var btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cameraonestep)

        photoImageView = findViewById(R.id.imgView_cameraonestep) as ImageView
        btn = findViewById(R.id.btn_cameraonestep) as Button

        launchCamera(photoImageView)

        btn.setOnClickListener {
            val intent2 = Intent(this,ResultActivity::class.java)
            startActivity(intent2)
        }

    }

    private fun hascamera(): Boolean {
        return packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)
    }

    fun launchCamera(view: View) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val extra = data!!.extras
            val photo = extra.get("data") as Bitmap
            photoImageView.setImageBitmap(photo)
        }
    }
}
