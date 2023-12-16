package com.example.note_kotlin.activity

import android.Manifest
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.note_kotlin.App
import com.example.note_kotlin.R
import com.example.note_kotlin.databinding.ActivityLoginBinding
import com.example.note_kotlin.dialog.ChooseDialog
import com.example.note_kotlin.dialog.IChooseListener
import com.example.note_kotlin.utils.ImageEngine
import com.example.note_kotlin.utils.SPUtil
import com.google.firebase.auth.FirebaseAuth
import com.huantansheng.easyphotos.EasyPhotos
import com.huantansheng.easyphotos.callback.SelectCallback
import com.huantansheng.easyphotos.models.album.entity.Photo
import com.huantansheng.easyphotos.utils.permission.PermissionUtil
import com.huantansheng.easyphotos.utils.permission.PermissionUtil.PermissionCallBack


class LoginActivity:AppCompatActivity() {
    lateinit var binding:ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    lateinit var img:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        var spUtil = SPUtil(applicationContext,"data")
        var name = spUtil.getString("name","")
        var img = spUtil.getString("img","")
        var password = spUtil.getString("password","")
        var email = spUtil.getString("email","")
        if (!TextUtils.isEmpty(name)){
            startActivity(Intent(this@LoginActivity,MainActivity::class.java))
            finish()
            return
        }
        binding.imageHead.setOnClickListener{
            ChooseDialog(this, R.style.FullScreenDialog, object : IChooseListener {
                override fun onCamera() {

                }
                override fun onFile() {
                    EasyPhotos
                        .createAlbum(
                            this@LoginActivity,
                            true,
                            true,
                            ImageEngine.instance!!
                        )
                        .setFileProviderAuthority("com.example.note_kotlin.fileprovider")
                        .setCount(1)
                        .start(object : SelectCallback() {
                            override fun onResult(photos: ArrayList<Photo>, isOriginal: Boolean) {
                                //获取file，进行对应操作
                                if (photos.size > 0) {
                                    img = photos[0].path
                                    Glide.with(this@LoginActivity).load(img).into(binding.imageHead)
                                }
                            }

                            override fun onCancel() {}
                        })
                }
            }).show()
        }

        binding.tvContinue.setOnClickListener {
            val name = binding.account.text.toString()
            val email = binding.email.text.toString()
            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email)){
                Toast.makeText(applicationContext,"please input",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(img)){
                Toast.makeText(applicationContext,"please choose image",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            App.headUrl = img;
            App.name = name;
            var intent = Intent(this@LoginActivity,LoginWithPasswordActivity::class.java)
            intent.putExtra("email",email)
            intent.putExtra("name",name)
            intent.putExtra("img",img)
            startActivityForResult(intent,101)
        }

        if (PermissionUtil.checkAndRequestPermissionsInActivity(this,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE)) {
            preLoadAlbums();
        }
    }


    private fun preLoadAlbums() {
        EasyPhotos.preLoad(this)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PermissionUtil.onPermissionResult(this, permissions, grantResults,
            object : PermissionCallBack {
                override fun onSuccess() {
                    preLoadAlbums()
                }

                override fun onShouldShow() {}
                override fun onFailed() {}
            })
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 101 && resultCode == 102){
            startActivity(Intent(this@LoginActivity,MainActivity::class.java))
            finish()
        }
    }
}