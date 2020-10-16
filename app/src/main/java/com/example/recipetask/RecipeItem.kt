package com.example.recipetask

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RecipeItem(
    val calories: String,
    val carbos: String,
    val country: String?,
    val description: String,
    val difficulty: Int,
    val fats: String,
    val headline: String,
    val id: String,
    @Json(name = "image") val imgSrcUrl: String,
    val name: String,
    val proteins: String,
    val thumb: String,
    val time: String,

    ) : Parcelable




