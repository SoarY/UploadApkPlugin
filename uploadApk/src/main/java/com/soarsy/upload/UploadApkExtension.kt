package com.soarsy.upload



open class UploadApkExtension {

    // ------------ 上传蒲公英相关的扩展 ------------
    /**
     * (必填)值为debug、release等buildTypes
     */
    var uploadBuildType: String? = null
    /**
     * (必填)蒲公英后台apiKey
     */
    var apiKey: String? = null

    companion object {
        var NAME_SPACE: String = "UploadApk"
    }
}