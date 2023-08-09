package com.example.abschlussaufgabe.data.apiServices

import com.example.abschlussaufgabe.data.datamodel.CurrentWeather
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL =
    "https://api.open-meteo.com/v1/"

private val httpClient: OkHttpClient = OkHttpClient.Builder().addInterceptor { client ->
    val request: Request = client.request().newBuilder()
        .build()
    client.proceed(request)
}.build()

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .client(httpClient)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface WetterApiService{
    @GET("forecast?latitude=34.0522&longitude=-118.2437&hourly=apparent_temperature&timezone=GMT&forecast_days=1")
    suspend fun getWetter(): CurrentWeather
}

object WetterApi{
    val retrofitService: WetterApiService by lazy { retrofit.create(WetterApiService::class.java) }
}