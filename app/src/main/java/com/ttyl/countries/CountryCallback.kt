package com.ttyl.countries

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountryCallback(private var countryDoneState: MutableLiveData<Array<Country>>): Callback<Array<Country>> {
    override fun onResponse(call: Call<Array<Country>>, response: Response<Array<Country>>) {
        if (response.code() != CountryDao.HTTP_OK) {
            // we log the http error in CountryDAO interceptor, fill empty array here
            countryDoneState.value = arrayOf()
        } else {
            response.body()?.let {
               countryDoneState.value = response.body()
            }
        }
    }

    override fun onFailure(call: Call<Array<Country>>, t: Throwable) {
        // fill empty array due to error
        countryDoneState.value = arrayOf()
        Log.e("CountryCallback", "Failed to get countries," +
                " msg: ${t.localizedMessage}")
    }
}