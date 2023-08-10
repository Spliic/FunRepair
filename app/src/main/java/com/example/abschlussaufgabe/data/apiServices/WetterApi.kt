package com.example.abschlussaufgabe.data.apiServices

import com.example.abschlussaufgabe.data.datamodel.CurrentWeather
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL =
    "http://api.weatherstack.com/"

const val API_KEY = "007565fe37139854dccd3238d06ef89d"

private val httpClient: OkHttpClient = OkHttpClient.Builder().addInterceptor { client ->
    val request: Request = client.request().newBuilder()
        //.addHeader("Authorization","access_key $API_KEY")
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
    @GET("current?access_key=007565fe37139854dccd3238d06ef89d&query=athen")
    suspend fun getWetter(): CurrentWeather
}

object WetterApi{
    val retrofitService: WetterApiService by lazy { retrofit.create(WetterApiService::class.java) }
}