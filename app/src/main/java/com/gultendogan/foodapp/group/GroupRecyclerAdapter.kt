package com.gultendogan.foodapp.group

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gultendogan.foodapp.databinding.GroupRecyclerRowBinding
import com.squareup.picasso.Picasso

class GroupRecyclerAdapter(private val postList : ArrayList<Post>) : RecyclerView.Adapter<GroupRecyclerAdapter.PostHolder>() {

    class PostHolder(val binding: GroupRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val binding =GroupRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostHolder(binding)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.binding.recyclerEmailText.text=postList.get(position).email
        holder.binding.recyclerRecipeNameText.text=postList.get(position).recipeName
        holder.binding.recyclerRecipeText.text=postList.get(position).recipe
        Picasso.get().load(postList.get(position).downloadUrl).into(holder.binding.recyclerImageView)
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}