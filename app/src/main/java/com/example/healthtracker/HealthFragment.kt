package com.example.healthtracker

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.healthtracker.databinding.FragmentHealthBinding
import com.google.firebase.Timestamp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.Query
import java.util.*

class HealthFragment : Fragment() {
    private lateinit var binding: FragmentHealthBinding
    private val healthAdapter = HealthAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHealthBinding.inflate(inflater)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = Firebase.firestore
        db.collection(Firebase.auth.uid ?: "")
                .orderBy("date", Query.Direction.DESCENDING)
                .addSnapshotListener { value, e ->
                    if (e != null) {
                        Log.w(javaClass.simpleName, "Listen failed.", e)
                        return@addSnapshotListener
                    }
                    val data = mutableListOf<Health>()
                    for (doc in value!!) { //we have verified that there is no error so value will not be null
                        data.add(Health(
                                doc.getDouble("temperature") ?: 0.0,
                                doc.getDouble("oxygen") ?: 0.0,
                                doc.getTimestamp("date") ?: Timestamp(Date()),
                                doc.getBoolean("shortnessOfBreath") ?: false,
                                doc.getBoolean("cough") ?: false,
                                doc.getBoolean("bodyAches") ?: false
                        ))
                    }
                    healthAdapter.data = data
                }

        binding.healthRecycler.adapter = healthAdapter

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_healthFragment_to_addItemFragment)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!,
                requireView()!!.findNavController())
                || super.onOptionsItemSelected(item)
    }
}