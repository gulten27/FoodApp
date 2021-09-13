package com.gultendogan.foodapp.home

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.gultendogan.foodapp.main.MainActivity
import com.gultendogan.foodapp.R
import com.gultendogan.foodapp.databinding.ActivityHomeBinding
import com.gultendogan.foodapp.group.GroupActivity
import com.gultendogan.foodapp.recipe.RecipeActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.signout_dialog.view.*

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth

        exit.setOnClickListener {
            val view = View.inflate(this@HomeActivity, R.layout.signout_dialog,null)

            val builder = AlertDialog.Builder(this@HomeActivity)
            builder.setView(view)

            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

            view.signout_dialog_ok_btn.setOnClickListener {
                auth.signOut()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            view.signout_dialog_cancel_btn.setOnClickListener {
                dialog.cancel()
            }
        }
    }


    fun recipe(view:View){
        val intent = Intent(this@HomeActivity,RecipeActivity::class.java)
        startActivity(intent)
    }

    fun group(view:View){
        val intent = Intent(this@HomeActivity,GroupActivity::class.java)
        startActivity(intent)
    }

}