package com.example.healthtracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.healthtracker.databinding.FragmentHealthBinding
import com.example.healthtracker.databinding.FragmentWeeklyReportBinding

class WeeklyReportFragment : Fragment() {

    private lateinit var binding: FragmentWeeklyReportBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeeklyReportBinding.inflate(inflater)
        return binding.root
    }

}