package com.example.okhttp

import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

data class Authorization(
    val email:String,
    val password:String
)

data class Token(
    val access_token:String
)

fun main(){
    val gson = Gson()

    val client = OkHttpClient.Builder()
        .build()

    val authorization = Authorization(
        "string@.",
        "string"
    )

//    val applicationJson = "Content-Type: application/json".toMediaType()

    val body = gson.toJson(authorization).toRequestBody()
    val request = Request.Builder()
        .post(body)
        .url("https://api.cfif31.ru/spotify/Authorization")
        .build()

    val call = client.newCall(request)

    val response = call.execute()

    if (response.isSuccessful){
        val bodyToken = response.body!!.string()
        val result = gson.fromJson(
            bodyToken,
            Token::class.java
        )
        print(result.access_token)
    }else{
        print(response.code)
    }
}