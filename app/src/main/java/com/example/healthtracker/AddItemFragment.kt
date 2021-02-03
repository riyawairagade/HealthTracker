package com.example.healthtracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.healthtracker.databinding.FragmentAddItemBinding
import com.example.healthtracker.databinding.FragmentHealthBinding

class AddItemFragment : Fragment() {

    private lateinit var binding: FragmentAddItemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddItemBinding.inflate(inflater)
        return binding.root
    }


}