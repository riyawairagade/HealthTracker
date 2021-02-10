package com.example.healthtracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.healthtracker.databinding.ItemRowHealthBinding
import java.text.SimpleDateFormat

class HealthAdapter : RecyclerView.Adapter<HealthAdapter.ViewHolder>() {

    lateinit var deleteListener: (Health) -> Unit

    var data = listOf<Health>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                ItemRowHealthBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val health = data[position]

        with(holder.binding) {
            temperatureText.text = "Temperature: " + health.temperature.toString()
            oxygenText.text = "Oxygen: " + health.oxygen.toString()
            dateText.text = health.date.toDate().toString()
            shortnessOfBreathText.text = "Shortness Of Breath: " + health.shortnessOfBreath.toString()
            coughText.text = "Cough: " + health.cough.toString()
            bodyAchesText.text = "Body Aches: " + health.bodyAches.toString()
        }
    }

    override fun getItemCount() = data.size

    inner class ViewHolder(val binding: ItemRowHealthBinding) : RecyclerView.ViewHolder(binding.root)

}