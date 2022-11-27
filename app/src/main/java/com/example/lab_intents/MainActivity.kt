package com.example.lab_intents

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    private lateinit var goToExplicitActivity: Button
    private lateinit var goToImplicitActivity: Button
    private lateinit var shareData: Button
    private lateinit var chooseImage: Button
    private lateinit var displayText: TextView
    private lateinit var imageView: ImageView
    private var imageUri: Uri? = null
    private val pickImage = 100

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            displayText.text = data?.getStringExtra("editText")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //define the inputs
        goToExplicitActivity = findViewById(R.id.goToExplicitActivity)
        goToImplicitActivity = findViewById(R.id.goToImplicitActivity)
        shareData = findViewById(R.id.shareData)
        chooseImage = findViewById(R.id.chooseImage)
        displayText = findViewById(R.id.displayText)
        imageView = findViewById(R.id.imageView)


        val bundle: Bundle? = intent.extras;
        if (bundle != null) {
            val text = bundle.get("textInput");
            displayText.text = text.toString();
        }

        //start ExplicitActivity
        goToExplicitActivity.setOnClickListener{
            val intent = Intent(this, ExplicitActivity::class.java)
            startActivity(intent)
        }


        val mainIntent = Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        //start ImplicitActivity
        goToImplicitActivity.setOnClickListener {
            Intent(this, ImplicitActivity::class.java).apply {
                action = "mk.ukim.finki.mpip.IMPLICIT_ACTION"
                type = "text/plain"
            }.let { intent ->
                //define the extra
                intent.putExtra("displayList", ArrayList(packageManager.queryIntentActivities(mainIntent, 0)))
                resultLauncher.launch(intent)
            }
        }

        //send data
        shareData.setOnClickListener {
            Intent(Intent.ACTION_SEND).let { data ->
                data.type = "text/plain"
                data.putExtra(Intent.EXTRA_SUBJECT, "MPiP Send Title")
                data.putExtra(Intent.EXTRA_TEXT, "Content send from MainActivity")
                startActivity(data)
            }
        }

        //choose photo
        chooseImage.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            startActivity(Intent(Intent.ACTION_VIEW, imageUri))
        }
    }
}