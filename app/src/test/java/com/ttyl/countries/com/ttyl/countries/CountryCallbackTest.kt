package com.ttyl.countries.com.ttyl.countries

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ttyl.countries.Country
import com.ttyl.countries.CountryCallback
import com.ttyl.countries.CountryDao
import io.mockk.*
import org.junit.Assert.assertArrayEquals
import org.junit.Test
import retrofit2.Call
import retrofit2.Response

class CountryCallbackTest {

    val response: Response<Array<Country>> = mockk()
    var countryDoneState: MutableLiveData<Array<Country>> = mockk()
    val call: Call<Array<Country>> = mockk()
    val throwable: Throwable = mockk()

    @Test
    fun `callback success`() {
        every { response.code() } returns CountryDao.HTTP_OK
        every { response.body() } returns arrayOf(Country("test1", "test2", "test3", "test4"))
        every {countryDoneState.setValue(arrayOf(Country("test1", "test2", "test3", "test4")))} just Runs
        every {countryDoneState.value} returns arrayOf(Country("test1", "test2", "test3", "test4"))
        val callBack = CountryCallback(countryDoneState)
        callBack.onResponse(call, response)
        assertArrayEquals(countryDoneState.value, arrayOf(Country("test1", "test2", "test3", "test4")))
    }

    @Test
    fun `callback success 400`() {
        every { response.code() } returns 400
        every { response.body() } returns arrayOf()
        every {countryDoneState.setValue(arrayOf())} just Runs
        every {countryDoneState.value} returns arrayOf()
        val callBack = CountryCallback(countryDoneState)
        callBack.onResponse(call, response)
        assertArrayEquals(countryDoneState.value, arrayOf())
    }

    @Test
    fun `callback fail`() {
        mockkStatic(Log::class)
        every { response.code() } returns 400
        every { response.body() } returns arrayOf()
        every {countryDoneState.setValue(arrayOf())} just Runs
        every {countryDoneState.value} returns arrayOf()
        every {throwable.localizedMessage} returns "failure"
        every { Log.e(any(), any()) } returns 0
        val callBack = CountryCallback(countryDoneState)
        callBack.onFailure(call, throwable)
        assertArrayEquals(countryDoneState.value, arrayOf())
        verify { Log.e("CountryCallback", "Failed to get countries, msg: failure") }
        unmockkStatic(Log::class)
    }

}