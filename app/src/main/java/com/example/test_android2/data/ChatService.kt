package com.example.test_android2.data

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ChatService {
    @POST("/chatAnalysis/justVariable")
    fun uploadChat(@Body chatInfo: ChatData): Call<ResponseChat>
}