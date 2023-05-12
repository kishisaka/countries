package com.ttyl.countries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ttyl.countries.databinding.ActivityMainBinding

class CountryActivity : AppCompatActivity(), CountryContract.View {

    private val countryAdapter = CountryAdapter()

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var emptyList: TextView
    private lateinit var countryPresenter: CountryPresenter

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        progressBar = binding.progressBar
        recyclerView = binding.countries
        emptyList = binding.emptyList
        recyclerView.adapter = countryAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // get countries, present it!
        val viewModel = ViewModelProvider(this)[(CountryViewModel::class.java)]
        countryPresenter = CountryPresenter(this)
        viewModel.countryDoneState.observe(this) {
            countryPresenter.publishCountries(it)
        }
        viewModel.getCountries()
    }

    override fun updateCountries(countries: Array<Country>) {
        countryAdapter.setItem(countries)
    }

    override fun showNothingInListOrError() {
        emptyList.visibility = View.VISIBLE
    }

    override fun hideNothingInListOrError() {
        emptyList.visibility = View.GONE
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }
}