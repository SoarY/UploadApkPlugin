package com.soarsy.upload.tasks

import com.android.build.gradle.AppExtension
import com.android.build.gradle.api.ApplicationVariant
import com.google.gson.Gson
import com.soarsy.upload.UploadApkExtension
import com.soarsy.upload.api2.ApiConstants
import com.soarsy.upload.api2.ApiFactory
import com.soarsy.upload.api2.PgyResponse
import com.soarsy.upload.api2.PgyUploadService
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import java.io.File

/**
 * NAME：YONG_
 * Created at: 2025/10/15 19
 * Describe:
 */
open class PgyUploadTask : DefaultTask() {

    @Internal
    lateinit var variant:ApplicationVariant

    fun init(variant: ApplicationVariant) {
        this.variant = variant
        description = "Reinforce Release Apk"
        group = "uploadApk"
    }

    @TaskAction
    fun action() {
        val uploadApkExtension = project.extensions.getByType(UploadApkExtension::class.java)

        println("############################上传蒲公英#############################")
        println("# apiKey       : " + uploadApkExtension.apiKey)
        println("##################################################################")

        var apkFilePath: File?=null
        variant.outputs.all {output ->
            apkFilePath = output.outputFile
        }

        val apiFactory = ApiFactory.create(ApiConstants.PGY_BASE_URL, PgyUploadService::class.java)
        val appResponse = apiFactory.uploadFile(
            ApiFactory.getTextBody(uploadApkExtension.apiKey),
            ApiFactory.getFilePart("application/vnd.android.package-archive", apkFilePath!!)
        ).execute()
        val result = appResponse.body()!!.string()
        val pgyResponse = Gson().fromJson(result, PgyResponse::class.java)
        println("pgyResponse:"+pgyResponse)
    }
}