package com.example.spacex

import junit.framework.Assert.assertTrue
import org.junit.Test
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

class RetrofitTest {
       /* @Test
        fun login_Success() {
            val apiEndpoints: APIEndpoints =
                RetrofitHelper.getTesterInstance().create(APIEndpoints::class.java)
            val call: Call<AuthResponse> = apiEndpoints.postLogin()
            try {
                //Magic is here at .execute() instead of .enqueue()
                val response: Response<AuthResponse> = call.execute()
                val authResponse: AuthResponse = response.body()
                assertTrue(
                    response.isSuccessful() && authResponse.getBearer().startsWith("TestBearer")
                )
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }*/
    }

