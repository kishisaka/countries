package com.ttyl.countries

import retrofit2.Callback

interface CountryContract {
    interface View {
        fun showProgress()
        fun hideProgress()
        fun showNothingInListOrError()
        fun hideNothingInListOrError()
        fun updateCountries(countries: Array<Country>)
    }

    interface Model {
        fun loadCountries(callBack: Callback<Array<Country>>)
    }

    interface Presenter {
        fun publishCountries()
        fun onDestroy()
    }
}