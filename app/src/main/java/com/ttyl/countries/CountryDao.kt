package com.ttyl.countries

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class CountryDao {
    private var countryService: CountryService? = null

    companion object {
        const val COUNTRY_ROOT_URL = "https://gist.githubusercontent.com/"
        const val HTTP_OK = 200
    }

    init {
        val client = OkHttpClient.Builder()
            .readTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(object: Interceptor{
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request = chain.request()
                    val response = chain.proceed(request)
                    // log this to whatever observability platform you have
                    if (response.code() != HTTP_OK) {
                        Log.e("CountryDao request", "${request.method()} " +
                                "${request.url()}, ${response.code()}, ${response.body()}")
                    }
                    return response
                }
            })
            .build()
        countryService = Retrofit.Builder()
            .baseUrl(COUNTRY_ROOT_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(CountryService::class.java)
    }

    fun getCountry(callBack: Callback<Array<Country>>) {
        val call = countryService?.getCountries()
        call?.enqueue(callBack)
    }
}