package com.soarsy.upload.api2

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import kotlin.concurrent.Volatile

object ApiFactory {

    fun <T> create(baseUrl: String, clazz: Class<T>): T {
        return Retrofit.Builder().baseUrl(baseUrl).client(newClient())
            .addConverterFactory(GsonConverterFactory.create()).build().create(clazz)
    }

    private fun newClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
        return builder.build()
    }

    fun getFilePart(mediaType: String, file: File): MultipartBody.Part {
        return MultipartBody.Part.createFormData(
            "file", file.absoluteFile.name,
            RequestBody.create(MediaType.parse(mediaType), file)
        )
    }

    fun getTextBody(text: String?): RequestBody {
        return RequestBody.create(MediaType.parse("text/plain"), text)
    }

}