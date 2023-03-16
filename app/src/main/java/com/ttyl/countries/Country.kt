package com.ttyl.countries

import com.squareup.moshi.Json

data class Country(@field:Json(name="name")val name: String,
                   @field:Json(name="region")val region: String,
                   @field:Json(name="code")val code: String,
                   @field:Json(name="capital")val capital: String)