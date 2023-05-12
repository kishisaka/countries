package com.ttyl.countries

import com.ttyl.countries.*
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.Test

class CountryPresenterTest {

    @MockK
    val countryView: CountryContract.View = mockk()

    @Test
    fun `country presenter test success` () {
        val presenter = CountryPresenter(countryView)
        every { countryView.hideProgress() } just Runs
        every { countryView.hideNothingInListOrError() } just Runs
        every { countryView.updateCountries(arrayOf(
            Country("test1", "region1", "code1", "cap1"),
            Country("test2", "region2", "code2", "cap2")))} just runs
        every { countryView.hideProgress() } just Runs
        presenter.publishCountries(arrayOf(
            Country("test1", "region1", "code1", "cap1"),
            Country("test2", "region2", "code2", "cap2")))
        verify { countryView.hideNothingInListOrError() }
        verify { countryView.hideProgress() }
        verify { countryView.updateCountries(arrayOf(
            Country("test1", "region1", "code1", "cap1"),
            Country("test2", "region2", "code2", "cap2"))) }
    }

    @Test
    fun `country presenter test fail or empty` () {
        val presenter = CountryPresenter(countryView)
        every { countryView.hideProgress() } just Runs
        every { countryView.showNothingInListOrError() } just Runs
        every { countryView.updateCountries(arrayOf())} just runs
        presenter.publishCountries(arrayOf())
        verify { countryView.showNothingInListOrError() }
        verify { countryView.hideProgress() }
        verify { countryView.updateCountries(arrayOf()) }
    }
}