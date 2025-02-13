package com.example.firestoreapp1

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firestoreapp1.databinding.MainActivityBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class MainActivity : AppCompatActivity() {
    private val binding : MainActivityBinding by lazy {
        MainActivityBinding.inflate(layoutInflater)
    }
    override fun onCreate (savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
       setContentView(binding.root)
        val db= Firebase.firestore

        binding.login.setOnClickListener {
            // Create a new user with a first and last name
            val user = hashMapOf(
                "name" to binding.name.text.toString(),
                "pass" to binding.pass.text.toString(),
            )

            // Add a new document with a generated ID
            db.collection("users")
                .add(user)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(this, "DocumentSnapshot added with ID: ${documentReference.id}",Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error adding document ${e.localizedMessage}", Toast.LENGTH_LONG).show()
                }
        }


    }
}