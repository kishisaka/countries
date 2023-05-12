package com.ttyl.countries

class CountryPresenter(private var mainView: CountryContract.View): CountryContract.Presenter {

    override fun publishCountries(countries: Array<Country>) {
        mainView.updateCountries(countries)
        if (countries.isEmpty()) {
            mainView.showNothingInListOrError()
        } else {
            mainView.hideNothingInListOrError()
        }
        mainView.hideProgress()
    }
}