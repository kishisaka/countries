package com.ttyl.countries

class CountryPresenter(var callback: CountryCallback,
                       private var mainView: CountryContract.View?,
                       private val model: CountryContract.Model): CountryContract.Presenter {

    override fun publishCountries() {
        mainView?.showProgress()
        model.loadCountries(callback)
    }

    override fun onDestroy() {
        mainView = null
    }
}