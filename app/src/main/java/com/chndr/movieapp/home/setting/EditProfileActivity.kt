package com.chndr.movieapp.home.setting

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chndr.movieapp.R
import com.chndr.movieapp.home.HomeActivity
import com.chndr.movieapp.sign.signin.User
import com.chndr.movieapp.utils.Preferences
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_edit_profile.iv_back
import kotlinx.android.synthetic.main.activity_edit_profile.iv_profile
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up_photoscreen.*
import java.util.*

class EditProfileActivity : AppCompatActivity() {
    private var statusAdd: Boolean = false
    private var statusAddImage: Boolean = false
    private lateinit var filePath: Uri

    lateinit var preferences: Preferences
    private lateinit var storage: FirebaseStorage
    private lateinit var storageReference: StorageReference

    private lateinit var mFirebaseDatabase: DatabaseReference
    private lateinit var mFirebaseInstance: FirebaseDatabase

    private lateinit var sUsername: String
    private lateinit var sNama: String
    private lateinit var sEmail: String
    private lateinit var sUrl: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        iv_back.setOnClickListener {
            finishAffinity()
            val intent = Intent(this@EditProfileActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
        storage = FirebaseStorage.getInstance("gs://movieapp-2b1af.appspot.com")
        storageReference = storage.reference

        mFirebaseInstance = FirebaseDatabase.getInstance("https://movieapp-2b1af-default-rtdb.firebaseio.com/")
        mFirebaseDatabase = mFirebaseInstance.getReference("User")
        preferences = Preferences(this)


        if(preferences.getValues("url") != ""){
            Glide.with(this)
                .load(preferences.getValues("url"))
                .apply(RequestOptions.circleCropTransform())
                .into(iv_profile)

            statusAdd = true
            iv_add2.setImageResource(R.drawable.ic_btn_delete)
        }

        iv_add2.setOnClickListener {
            if (statusAdd) {
                statusAdd = false
                statusAddImage = false
                iv_add2.setImageResource(R.drawable.ic_btn_upload)
                iv_profile.setImageResource(R.drawable.user_pic)

            } else {
                ImagePicker.with(this)
                    .compress(1024)
                    .start()
            }
        }


        tv_username.setText(preferences.getValues("user").toString())
        tv_nama.setText(preferences.getValues("nama").toString())
        tv_email_editprofile.setText(preferences.getValues("email").toString())

        btn_saveprofile.setOnClickListener {
            sUsername = tv_username.text.toString()
            sNama = tv_nama.text.toString()
            sEmail = tv_email_editprofile.text.toString()
            sUrl = preferences.getValues("url").toString()

            if (statusAddImage && statusAdd) {
                val progressDialog = ProgressDialog(this)
                progressDialog.setTitle("Uploading...")
                progressDialog.show()

                val ref = storageReference.child("images/" + UUID.randomUUID().toString())
                ref.putFile(filePath)
                    .addOnSuccessListener {
                        progressDialog.dismiss()
                        Toast.makeText(this, "Uploaded", Toast.LENGTH_SHORT).show()

                        ref.downloadUrl.addOnSuccessListener {
                            saveToFirebase(it.toString(), sUsername, sNama, sEmail)
                            Toast.makeText(this@EditProfileActivity, "Update Success", Toast.LENGTH_SHORT).show()
                        }
                    }
                    .addOnFailureListener {
                        progressDialog.dismiss()
                        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                    }
                    .addOnProgressListener { taskSnapshot ->
                        val progress =
                            100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                        progressDialog.setMessage("Upload " + progress.toInt() + " %")
                    }
            }else if(!statusAddImage && !statusAdd){
                deleteImageFirebase()
                saveToFirebase("", sUsername, sNama, sEmail)
                Toast.makeText(this@EditProfileActivity, "Update & delete image Success", Toast.LENGTH_SHORT).show()
            }else{
                saveToFirebase(sUrl, sUsername, sNama, sEmail)
                Toast.makeText(this@EditProfileActivity, "Update data Success", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteImageFirebase(){
        // Create a reference to the file to delete
        val referenceUrl = storage.getReferenceFromUrl(preferences.getValues("url").toString())
        // Delete the file
        referenceUrl.delete().addOnFailureListener {
            Toast.makeText(this, "Error, can't delete profile picture", Toast.LENGTH_SHORT).show()
        }

    }


    private fun saveToFirebase(url: String, Username: String, nama: String, email: String){
        mFirebaseDatabase.child(Username).child("nama").setValue(nama)
        mFirebaseDatabase.child(Username).child("email").setValue(email)
        mFirebaseDatabase.child(Username).child("url").setValue(url)

        mFirebaseDatabase.child(Username).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                preferences.setValues("nama", nama)
                preferences.setValues("email", email)
                preferences.setValues("url", url)

            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@EditProfileActivity, ""+error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                //Image Uri will not be null for RESULT_OK
                statusAdd = true
                statusAddImage = true
                filePath = data?.data!!
                Glide.with(this)
                    .load(filePath)
                    .apply(RequestOptions.circleCropTransform())
                    .into(iv_profile)
                iv_add2.setImageResource(R.drawable.ic_btn_delete)
            }
            ImagePicker.RESULT_ERROR -> {
                Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }
    }

}