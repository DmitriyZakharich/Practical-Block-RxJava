package com.example.rxjavapracticalblock.task_1

import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/** Задание 1
 * Сделайте сетевой запрос и отобразите результат на экране? (база)*/

private const val BASE_URL = "https://catfact.ninja/"

class NetworkLoader {
    fun getCatFact(): Single<CatFact> {
        val apiService = RetrofitHelper.getRetrofit().create(ApiService::class.java)
        return apiService.request()
    }
}

interface ApiService {
    @GET("fact")
    fun request(): Single<CatFact>
}

object RetrofitHelper {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
}
