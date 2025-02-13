package com.example.firestoreapp2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firestoreapp2.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        var list = arrayListOf <User>()
        var rvAdaptir = Rv_adaptir(this,list)
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = rvAdaptir

        val db = Firebase.firestore
        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                list.clear()
                for (document in result) {
//                    Log.d(TAG, "${document.id} => ${document.data}")
                    val user = document.toObject(User::class.java)
                    user.id=document.id
                    list.add(user)
                }
                rvAdaptir.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
//                Log.w(TAG, "Error getting documents.", exception)
            }
    }
}