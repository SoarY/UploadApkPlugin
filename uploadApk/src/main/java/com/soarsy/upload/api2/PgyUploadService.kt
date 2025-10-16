package com.soarsy.upload.api2

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface PgyUploadService {
    @Multipart
    @POST("app/upload")
    fun uploadFile(
        @Part("_api_key") key: RequestBody?,
        @Part file: MultipartBody.Part?
    ): Call<ResponseBody>
}