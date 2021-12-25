package com.chndr.movieapp.sign.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.chndr.movieapp.R
import com.chndr.movieapp.sign.signin.User
import com.chndr.movieapp.utils.Preferences
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var sUsername: String
    private lateinit var sPassword: String
    private lateinit var sNama: String
    private lateinit var sEmail: String
    private lateinit var sSaldo: String

    lateinit var mFirebaseDatabase: DatabaseReference
    private lateinit var mFirebaseInstance: FirebaseDatabase

    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mFirebaseInstance = FirebaseDatabase.getInstance("https://movieapp-2b1af-default-rtdb.firebaseio.com/")
        mFirebaseDatabase = mFirebaseInstance.getReference("User")

        preferences = Preferences(this)

        btn_lanjutkan.setOnClickListener {
            sUsername = et_username.text.toString()
            sPassword = et_password.text.toString()
            sNama = et_nama.text.toString()
            sEmail = et_email.text.toString()
            sSaldo = "0"

            if (sUsername.equals("")) {
                et_username.error = "Silahkan Isi Username Anda"
                et_username.requestFocus()
            } else if (sPassword.equals("")) {
                et_password.error = "Silahkan Isi Password Anda"
                et_password.requestFocus()
            } else if (sNama.equals("")) {
                et_nama.error = "Silahkan Isi Nama Anda"
                et_nama.requestFocus()
            } else if (sEmail.equals("")) {
                et_email.error = "Silahkan Isi Email Anda"
                et_email.requestFocus()
            } else {
                val statusUsername = sUsername.indexOf(".")
                if (statusUsername >= 0) {
                    et_username.error = "Silahkan tulis Username Anda tanpa ."
                    et_username.requestFocus()
                } else {
                    saveUser(sUsername, sPassword, sNama, sEmail, sSaldo)
                }
            }

        }
        iv_back.setOnClickListener{
            finish()
        }

    }


    private fun saveUser(sUsername: String, sPassword: String, sNama: String, sEmail: String, sSaldo: String) {
        val user = User()
        user.email = sEmail
        user.username = sUsername
        user.nama = sNama
        user.password = sPassword
        user.saldo = sSaldo


        if (sUsername != null) {
            checkingUsername(sUsername, user)
        }
    }

    private fun checkingUsername(iUsername: String, data: User) {
        mFirebaseDatabase.child(iUsername)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    val user = dataSnapshot.getValue(User::class.java)
                    if (user == null) {
                        mFirebaseDatabase.child(iUsername).setValue(data)

                        preferences.setValues("nama", data.nama.toString())
                        preferences.setValues("user", data.username.toString())
                        preferences.setValues("saldo", data.saldo.toString())
                        preferences.setValues("url", "")
                        preferences.setValues("email", data.email.toString())
                        preferences.setValues("status", "1")

                        val intent = Intent(
                            this@SignUpActivity,
                            SignUpPhotoscreenActivity::class.java
                        ).putExtra("data", data)
                        startActivity(intent)

                    } else {
                        Toast.makeText(
                            this@SignUpActivity,
                            "User Sudah Digunakan",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onCancelled(Error: DatabaseError) {
                    Toast.makeText(this@SignUpActivity, "" + Error.message, Toast.LENGTH_LONG)
                        .show()
                }

            })
    }
}