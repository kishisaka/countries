package com.ttyl.countries

interface CountryContract {
    interface View {
        fun showProgress()
        fun hideProgress()
        fun showNothingInListOrError()
        fun hideNothingInListOrError()
        fun updateCountries(countries: Array<Country>)
    }

    interface Presenter {
        fun publishCountries(countries: Array<Country>)
    }
}