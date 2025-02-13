package com.example.firestoreapp2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.firestoreapp2.databinding.RvItemBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class Rv_adaptir(var context:Context, var list:ArrayList<User>):
    RecyclerView.Adapter<Rv_adaptir.ViewHolder>() {

    class ViewHolder(var binding:RvItemBinding ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       var binding= RvItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.binding.name.text = list.get(position).name
        holder.binding.pass.text = list.get(position).pass

        holder.binding.delete.setOnClickListener {
            val db = Firebase.firestore
            db.collection("users").document(list.get(position).id!!).delete().addOnSuccessListener {
               Toast.makeText(context,"Deleted",Toast.LENGTH_LONG).show()
                list.removeAt(position)
                notifyDataSetChanged()
            }.addOnFailureListener{
                Toast.makeText(context,"Failure",Toast.LENGTH_LONG).show()
            }
        }
    }
}