package com.example.healthtracker

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.healthtracker.databinding.FragmentAddItemBinding
import com.example.healthtracker.databinding.FragmentHealthBinding
import com.google.firebase.Timestamp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class AddItemFragment : Fragment() {

    private lateinit var binding: FragmentAddItemBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAddItemBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = Firebase.firestore
        binding.addButton.setOnClickListener {

            val data = Health(binding.temperatureInputEditText.text.toString().toDouble(),binding.oxygenEditText.text.toString().toDouble(),Timestamp(Date()),binding.coughCheckBox.isChecked,binding.shortnessOfBreathCheckBox.isChecked,binding.bodyAchesCheckBox.isChecked,)

            db.collection(Firebase.auth.uid?:"")
                    .add(data)
                    .addOnSuccessListener { documentReference ->
                        Log.d(javaClass.simpleName, "DocumentSnapshot added with ID: ${documentReference.id}")
                        findNavController().navigate(R.id.action_addItemFragment_to_healthFragment)
                    }
                    .addOnFailureListener { e ->
                        Log.w(javaClass.simpleName, "Error adding document", e)
                        Toast.makeText(activity,"Inserting data failed",Toast.LENGTH_LONG).show();
                    }
        }

    }
}