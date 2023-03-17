package com.ttyl.countries

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountryCallback(private var mainView: CountryContract.View): Callback<Array<Country>> {

    override fun onResponse(call: Call<Array<Country>>, response: Response<Array<Country>>) {
        mainView.hideProgress()
        if (response.code() != CountryDao.HTTP_OK) {
            // we log the http error in CountryDAO interceptor
            mainView.showNothingInListOrError()
        } else {
            response.body()?.let {
                if (it.isEmpty()) {
                    mainView.showNothingInListOrError()
                } else {
                    mainView.hideNothingInListOrError()
                }
                mainView.updateCountries(it)
            }
        }
    }

    override fun onFailure(call: Call<Array<Country>>, t: Throwable) {
        mainView.hideProgress()
        mainView.updateCountries(emptyArray())
        mainView.showNothingInListOrError()
        // Log this to whatever observability platform you have
        Log.e("CountryPresenter", "Failed to get countries," +
                " msg: ${t.localizedMessage}")
    }
}