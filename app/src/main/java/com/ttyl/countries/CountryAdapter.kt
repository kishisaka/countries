package com.ttyl.countries

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ttyl.countries.databinding.CountryCellBinding

class CountryAdapter: RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
    var countries: Array<Country>? = null

    class CountryViewHolder(binding: CountryCellBinding): RecyclerView.ViewHolder(binding.root) {
        val code: TextView = binding.code
        val capital: TextView = binding.capital
        val name: TextView = binding.name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = CountryCellBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        countries?.let {
            holder.code.text = it[position].code
            holder.name.text = "${it[position].name}, ${it[position].region}"
            holder.capital.text = it[position].capital
        }
    }

    override fun getItemCount(): Int {
        this.countries?.let {
            return it.size
        }
        return 0
    }

    fun setItem(countries: Array<Country>) {
        this.countries = countries
        notifyDataSetChanged()
    }
}