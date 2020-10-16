package com.example.recipetask.Api

import com.example.recipetask.RecipeItem
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://hf-android-app.s3-eu-west-1.amazonaws.com/android-test/"
enum class RecipeApiFilter(val value: String) { SortByCalories("cal"), SortByFats("fats") }




private  val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface RecipeApiService {

    @GET("recipes.json")
    suspend fun getProperties(): List<RecipeItem>
}


object RecipeApi {
    val retrofitService : RecipeApiService by lazy { retrofit.create(RecipeApiService::class.java) }
}




