# UploadApkPlugin
上传蒲公英插件

## 使用步骤
#### Step 1.依赖UploadApkPlugin
Gradle
```groovy
repositories {
    maven("https://jitpack.io")
}
dependencies{
    classpath("com.github.SoarY:UploadApkPlugin:1.0.0")
}
```

```groovy
plugins {
    id("com.soarsy.uploadapkplugin")
}
UploadApk {
    /**
     * (必填)值为debug、release等buildTypes
     */
    uploadBuildType="debug"
    /**
     * (必填)蒲公英后台apiKey
     */
    apiKey = ""
}
```

#### Step 2.执行
![示例](https://github.com/SoarY/UploadApkPlugin/blob/main/file/log.png?raw=true)
