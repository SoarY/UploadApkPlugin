plugins {
    id("java-gradle-plugin")
    id("org.jetbrains.kotlin.jvm")

//    id("maven-publish")
}

gradlePlugin {
    plugins {
        create("UploadApkPlugin") {
            id = "com.soarsy.uploadapkplugin"
            implementationClass = "com.soarsy.upload.UploadApkPlugin"
        }
    }
}

dependencies {
    // 版本跟AGP版本对应
    implementation("com.android.tools.build:gradle:8.8.0")
    //retrofit2
    api(libs.retrofit2)
    api(libs.retrofit2.gson)
}


//group = "com.soarsy.plugin"
//version = "1.0.0"
//
//publishing {
//    publications {
//        create<MavenPublication>("maven") {
//            groupId = project.group.toString()
//            artifactId = "uploadapkplugin"
//            version = project.version.toString()
//
//            from(components["java"])
//        }
//    }
//    repositories {
//        maven {
//            url = uri(layout.buildDirectory.dir("maven-repo"))
//        }
//    }
//}

