package com.example.healthtracker

import com.google.firebase.Timestamp


data class Health(
        val temperature: Double,
        val oxygen: Double,
        val date: Timestamp,
        val shortnessOfBreath: Boolean,
        val cough: Boolean,
        val bodyAches: Boolean)