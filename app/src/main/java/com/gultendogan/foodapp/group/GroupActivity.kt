package com.gultendogan.foodapp.group

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.gultendogan.foodapp.R
import com.gultendogan.foodapp.databinding.ActivityGroupBinding

class GroupActivity : AppCompatActivity() {

    private lateinit var binding : ActivityGroupBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db : FirebaseFirestore
    private lateinit var postArrayList : ArrayList<Post>
    private lateinit var groupAdapter : GroupRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGroupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth
        db = Firebase.firestore

        postArrayList = ArrayList<Post>()

        getData()

        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        groupAdapter= GroupRecyclerAdapter(postArrayList)
        binding.recyclerView.adapter=groupAdapter
    }

    private fun getData(){
        db.collection("Posts").orderBy("date",Query.Direction.DESCENDING).addSnapshotListener { value, error ->
            if(error != null){
                Toast.makeText(this,error.localizedMessage,Toast.LENGTH_LONG).show()
            }else{
                if(value != null){
                    if (!value.isEmpty){
                        val documents = value.documents
                        postArrayList.clear()
                        for (document in documents){
                            val recipeName = document.get("recipeName") as String
                            val recipe = document.get("recipe") as String
                            val userEmail = document.get("userEmail") as String
                            val downloadUrl = document.get("downloadUrl") as String

                            //println(recipe)
                            //println(downloadUrl)

                            val post = Post(userEmail,recipeName,recipe,downloadUrl)
                            postArrayList.add(post)
                        }

                        groupAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.upload_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this,UploadActivity::class.java)
        startActivity(intent)
        return super.onOptionsItemSelected(item)
    }
}