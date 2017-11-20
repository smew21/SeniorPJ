package com.example.project.seniorpj.Cam

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Environment.getExternalStoragePublicDirectory
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.project.seniorpj.R
import com.example.project.seniorpj.Result.ResultActivity
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class CameraOneStepActivity : AppCompatActivity() {

    val REQUEST_IMAGE_CAPTURE = 123
    lateinit var photoImageView: ImageView
    lateinit var btn: Button

    private var fpath:String? = null
    private var filePath: Uri? = null
    private var filename:String? = null
    internal var storage: FirebaseStorage? = null
    internal var storageReference: StorageReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_one_step)

        storage = FirebaseStorage.getInstance()
        storageReference = storage!!.reference

        photoImageView = findViewById(R.id.imgView_cameraonestep) as ImageView
        btn = findViewById(R.id.btn_cameraonestep) as Button

        val date = Date()
        val dateformat = SimpleDateFormat("yyyyMMdd_HHmmss")
        filename = "food_"+dateformat.format(date)

        launchCamera(photoImageView)

        btn.setOnClickListener {
            val intent2 = Intent(this, ResultActivity::class.java)
            uploadImage()
            startActivity(intent2)
        }
    }

    private fun hascamera(): Boolean {
        return packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)
    }

    fun launchCamera(view: View) {
        dispatchTakePictureIntent()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val bitmap = BitmapFactory.decodeFile(fpath)
            createImageFile()
            galleryAddPic()
            photoImageView.setImageBitmap(bitmap)
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        //val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val storageDir = getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(
                filename, /* prefix */
                ".jpg", /* suffix */
                storageDir      /* directory */
        )

        fpath = image.absolutePath
        println("saved path "+fpath)
        return image
    }

    private fun dispatchTakePictureIntent() {
        val pictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (pictureIntent.resolveActivity(getPackageManager()) != null) {
            var photoFile:File? = null
            try {
                photoFile = createImageFile()
            } catch (e: IOException) {
            }
            if (photoFile != null) {
                filePath = FileProvider.getUriForFile(this,
                        applicationContext.packageName+".provider",
                        photoFile)
                pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, filePath)
                startActivityForResult(pictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    private fun galleryAddPic() {
        val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        val f = File(filePath.toString())
        val contentUri = Uri.fromFile(f)
        println("saved image--"+contentUri)
        mediaScanIntent.data = contentUri
        this.sendBroadcast(mediaScanIntent)
    }

    private fun uploadImage(){
        if (fpath!=null){
            val imageRef = storageReference!!.child("foodimages/"+ UUID.randomUUID().toString())//send to firebase folder
            imageRef.putFile(filePath!!)
                    .addOnSuccessListener {
                        Toast.makeText(this,"Success", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
                    }
                    .addOnProgressListener {
                        Toast.makeText(this,"Uploading", Toast.LENGTH_SHORT).show()
                    }
        }
    }
}
