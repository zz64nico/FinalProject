package com.example.note_kotlin.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.note_kotlin.App
import com.example.note_kotlin.R
import com.example.note_kotlin.databinding.ActivityLogPwdBinding
import com.example.note_kotlin.utils.SPUtil
import com.google.firebase.auth.FirebaseAuth

class LoginWithPasswordActivity : AppCompatActivity(){
    private lateinit var auth: FirebaseAuth
    lateinit var binding: ActivityLogPwdBinding
    lateinit var name:String
    lateinit var email:String
    lateinit var img:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_log_pwd)
        email = intent.getStringExtra("email") as String
        name = intent.getStringExtra("name") as String
        img = intent.getStringExtra("img") as String
        auth = FirebaseAuth.getInstance()
        binding.tvContinue.setOnClickListener {
            val password = binding.password.text.toString();
            if (TextUtils.isEmpty(password)){
                Toast.makeText(applicationContext,"input password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(this) { task ->
                                if (task.isSuccessful) {
                                    // Sign in success, update UI with the signed-in user's information
                                    val user = auth.currentUser;
                                    App.user = user;
                                    var intent = Intent()
                                    intent.putExtra("name",user)
                                    setResult(102,intent)
                                    finish()
                                } else {
                                    Toast.makeText(
                                        baseContext,
                                        "Authentication failed.",
                                        Toast.LENGTH_SHORT,
                                    ).show()
                                }
                            }
                    } else {
                        // If sign in fails, display a message to the user.
                        auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(this) { task ->
                                if (task.isSuccessful) {
                                    // Sign in success, update UI with the signed-in user's information
                                    val user = auth.currentUser;
                                    App.user = user;
                                    var intent = Intent()
                                    intent.putExtra("name",user)
                                    setResult(102,intent)
                                    var spUtil = SPUtil(applicationContext,"data")
                                    spUtil.putString("email",email)
                                    spUtil.putString("name",name)
                                    spUtil.putString("img",img)
                                    spUtil.putString("password",password)
                                    finish()
                                } else {
                                    Toast.makeText(
                                        baseContext,
                                        "Authentication failed.",
                                        Toast.LENGTH_SHORT,
                                    ).show()
                                }
                            }

                    }
                }
        }
    }
}