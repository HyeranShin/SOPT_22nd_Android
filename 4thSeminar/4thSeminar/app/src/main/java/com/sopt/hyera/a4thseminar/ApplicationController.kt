package com.sopt.hyera.a4thseminar

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApplicationController : Application(){
    lateinit var networkService: NetworkService
    private val baseUrl = "http://13.125.118.111:3009/"
    companion object {
        lateinit var instance: ApplicationController
        //일종의 스태틱
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        buildNetwork()
    }
    fun buildNetwork(){
        var builder = Retrofit.Builder()
        val retrofit = builder.baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()

        networkService = retrofit.create(NetworkService::class.java)
    }
}