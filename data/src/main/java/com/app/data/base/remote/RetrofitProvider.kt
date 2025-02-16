package com.app.data.base.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider {

    val provideRetrofit: Retrofit by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor("eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5YzQ1ZWVlYWU4Y2JjNDQ2Njg5NzE4MjYzOTFmYjk2ZiIsIm5iZiI6MTcxMzk1ODU0MC4wNjIsInN1YiI6IjY2MjhlZThjZTI5NWI0MDE2NDliYjI3YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.aZhnIk25UTJyIFfMl8it6CTzUS9aLRyD1kaff6cNzhE")) // For API token AUTH since this is static I will use a different way to save this
            .build()

        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/") // For URL I will receive it sa parameter since we can have different for prod, stage or dev
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}