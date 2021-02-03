package com.example.healthtracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.healthtracker.databinding.FragmentHealthBinding
import com.example.healthtracker.databinding.FragmentPrecautionsBinding

class PrecautionsFragment : Fragment() {

    private lateinit var binding: FragmentPrecautionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPrecautionsBinding.inflate(inflater)
        return binding.root
    }

}