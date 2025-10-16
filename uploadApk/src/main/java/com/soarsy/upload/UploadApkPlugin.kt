package com.soarsy.upload

import com.android.build.gradle.AppExtension
import com.android.build.gradle.internal.tasks.factory.dependsOn
import com.soarsy.upload.tasks.PgyUploadTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.Logging

/**
 * NAME：YONG_
 * Created at: 2025/9/3 17
 * Describe:
 */
class UploadApkPlugin: Plugin<Project> {

    private val TAG=UploadApkPlugin::class.java.simpleName

    private val logger = Logging.getLogger(TAG)

    override fun apply(project: Project) {
        // 创建 uploadApk 扩展
        val uploadApkExtension =
            project.extensions.create(UploadApkExtension.NAME_SPACE, UploadApkExtension::class.java)

        // 当在build.gradle中配置好UploadApkExtension的属性后，如果直接通过uploadApkExtension.getXxx()是无法获取得到值的
        // 所以需要调用project.afterEvaluate，该闭包会在gradle配置完成后回调，即解析完build.gradle文件后回调
        project.afterEvaluate {
            // AppExtension是Android插件创建的扩展
            val appExtension = project.extensions.getByType(AppExtension::class.java)

            // 获取apk包的变体，applicationVariants默认有debug跟release两种变体
            appExtension.applicationVariants.all {applicationVariant->
                logger.lifecycle(TAG + "applicationVariant.getName() = " + applicationVariant.getName())

                if (applicationVariant.name==uploadApkExtension.uploadBuildType) {
                    val pgyUploadTask = project.tasks.create("pgyUploadRelease", PgyUploadTask::class.java)
                    pgyUploadTask.init(applicationVariant)

                    //依赖关系
                    applicationVariant.assembleProvider.dependsOn(project.tasks.findByName("clean"))
                    pgyUploadTask.dependsOn(applicationVariant.assembleProvider)
                }
            }
        }

    }

}