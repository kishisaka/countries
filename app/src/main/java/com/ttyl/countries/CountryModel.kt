package com.ttyl.countries

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Callback

class CountryModel(private val countryDao: CountryDao,
                   private val dispatcher: CoroutineDispatcher): CountryContract.Model {

    override fun loadCountries(callback: Callback<Array<Country>>) {
        CoroutineScope(dispatcher).launch {
            countryDao.getCountry(callback)
        }
    }
}