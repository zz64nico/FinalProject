package com.example.note_kotlin.activity

import android.os.Bundle
import android.os.Process
import android.text.TextUtils
import android.view.Menu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.example.note_kotlin.App
import com.example.note_kotlin.R
import com.example.note_kotlin.databinding.ActivityMainBinding
import com.example.note_kotlin.utils.SPUtil
import com.example.note_kotlin.widget.CircleImageView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)
        auth = FirebaseAuth.getInstance()
        binding.appBarMain.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
        }
        //搜索按钮
        binding.appBarMain.imgSearch.setOnClickListener {

        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        val headerView: CircleImageView = navView.getHeaderView(0).findViewById(R.id.imageView)
        val tvName: TextView = navView.getHeaderView(0).findViewById(R.id.tv_name)
        val textView: TextView = navView.getHeaderView(0).findViewById(R.id.textView)
        val logOut: TextView = drawerLayout.findViewById(R.id.tv_logOut)
        logOut.setOnClickListener {
            var spUtil = SPUtil(applicationContext,"data")
            spUtil.putString("email","")
            spUtil.putString("name","")
            spUtil.putString("img","")
            spUtil.putString("password","")
            try {
                //正常退出
                Process.killProcess(Process.myPid())
                System.exit(0)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        if(App.user!=null){
            tvName.text = App.name
            textView.text  =  App.user!!.email
        }
        if (!TextUtils.isEmpty(App.headUrl)){
            Glide.with(this@MainActivity).load(App.headUrl).into(headerView)
        }
        var spUtil = SPUtil(applicationContext,"data")
        var name = spUtil.getString("name","")
        var img = spUtil.getString("img","")
        var password = spUtil.getString("password","")
        var email = spUtil.getString("email","")
        if (!TextUtils.isEmpty(name)){
            Glide.with(this@MainActivity).load(img).into(headerView)
            tvName.text = name
            textView.text  = email
            login(email!!,password!!)
        }
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun login(email:String,password:String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser;
                    App.user = user;
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