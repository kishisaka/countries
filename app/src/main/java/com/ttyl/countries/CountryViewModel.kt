package com.ttyl.countries

import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CountryViewModel: ViewModel() {

    val countryDoneState: LiveData<Array<Country>> get() = _countryDoneState

    // the dao as well as the callBack should be injected so we can easily make a
    // unit test for the CountryViewModel
    private val dao = CountryDao()

    private val _countryDoneState: MutableLiveData<Array<Country>> by lazy {
        MutableLiveData<Array<Country>>()
    }

    fun getCountries() {
        if (_countryDoneState.value == null) {
            CoroutineScope(Dispatchers.IO).launch {
                val callBack = CountryCallback(_countryDoneState)
                dao.getCountries(callBack)
            }
        }
    }
}