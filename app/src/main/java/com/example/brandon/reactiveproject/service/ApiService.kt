package com.example.brandon.reactiveproject.service

import com.example.brandon.reactiveproject.models.User
import com.example.brandon.reactiveproject.Config
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    companion object {
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(
                            GsonConverterFactory.create())
                    .baseUrl(Config.APISERVICE_BASE_URL)
                    .build()

            return retrofit.create(ApiService::class.java)
        }
    }

    @GET("/user/{id}")
    fun getUser(@Path("id") id: Int): Call<User>

    @GET("/users")
    fun getUsers(): Call<List<User>>
}