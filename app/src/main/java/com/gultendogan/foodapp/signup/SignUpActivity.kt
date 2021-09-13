package com.gultendogan.foodapp.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.gultendogan.foodapp.home.HomeActivity
import com.gultendogan.foodapp.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth
    }

    fun signUpNow(view:View){
        val email = binding.signUpEmail.text.toString()
        val password = binding.signUpPassword.text.toString()
        val passwordAgain = binding.signUpPasswordAgain.text.toString()

        if(email.equals("") || password.equals("") || passwordAgain.equals("")){
            Toast.makeText(this,"Enter email and password!", Toast.LENGTH_LONG).show()
        }else{
            auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
                val intent = Intent(this@SignUpActivity, HomeActivity::class.java)
                startActivity(intent)
            }.addOnFailureListener {
                Toast.makeText(this@SignUpActivity,it.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
    }


}