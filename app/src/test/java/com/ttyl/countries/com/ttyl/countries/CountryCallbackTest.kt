package com.ttyl.countries.com.ttyl.countries

import android.util.Log
import com.ttyl.countries.*
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.Test
import retrofit2.Call
import retrofit2.Response

class CountryCallbackTest {

    @MockK
    var countryView: CountryActivity = mockk()

    @MockK
    var call: Call<Array<Country>> = mockk()

    @MockK
    var response: Response<Array<Country>> = mockk()

    @Test
    fun `test callback onResponse success` () {
        every { response.body() } returns arrayOf(
            Country("test1", "region1", "code1", "cap1"),
            Country("test2", "region2", "code2", "cap2"))
        every { response.code() } returns 200
        every { countryView.hideProgress() } just Runs
        every { countryView.hideNothingInListOrError() } just Runs
        every { countryView.updateCountries(arrayOf(
            Country("test1", "region1", "code1", "cap1"),
            Country("test2", "region2", "code2", "cap2")))} just runs
        val countryCallBack = CountryCallback(countryView)
        countryCallBack.onResponse(call, response)
        verify { countryView.hideProgress() }
        verify { countryView.hideNothingInListOrError() }
        verify { countryView.updateCountries(arrayOf(
            Country("test1", "region1", "code1", "cap1"),
            Country("test2", "region2", "code2", "cap2")))}
        verify(exactly = 0) { countryView.showNothingInListOrError() }
    }

    @Test
    fun `test callback onResponse success on http error 500` () {
        every { response.body() } returns arrayOf()
        every { response.code() } returns 500
        every { countryView.hideProgress() } just Runs
        every { countryView.hideNothingInListOrError() } just Runs
        every { countryView.updateCountries(arrayOf()) } just runs
        every { countryView.showNothingInListOrError() } just runs
        val countryCallBack = CountryCallback(countryView)
        countryCallBack.onResponse(call, response)
        verify { countryView.hideProgress() }
        verify { countryView.showNothingInListOrError() }
        verify(exactly = 0) { countryView.hideNothingInListOrError() }
        verify(exactly = 0) { countryView.updateCountries(arrayOf())}
    }

    @Test
    fun `test callback onFailure` () {
        mockkStatic(Log::class)
        every { response.body() } returns arrayOf()
        every { response.code() } returns 500
        every { countryView.hideProgress() } just Runs
        every { countryView.updateCountries(arrayOf()) } just runs
        every { countryView.showNothingInListOrError() } just runs
        every { Log.e(any(), any()) } returns 0
        val countryCallBack = CountryCallback(countryView)
        countryCallBack.onFailure(call, Exception("failure"))
        verify { countryView.hideProgress() }
        verify { countryView.showNothingInListOrError() }
        verify { countryView.updateCountries(arrayOf()) }
        verify { Log.e("CountryPresenter", "Failed to get countries, msg: failure") }
        unmockkStatic(Log::class)
    }
}