import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        google()
        jcenter()

        //Ktlint Gradle
        maven("https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath(GradleDependency.androidGradle)
        classpath(GradleDependency.kotlin)
        classpath(GradleDependency.ktlintGradle)

    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

plugins {
    id(GradlePluginId.detekt) version GradlePluginVersion.detekt
    id(GradlePluginId.ktlintGradle) version GradlePluginVersion.ktlintGradle
    id(GradlePluginId.gradleVersionPlugin) version GradlePluginVersion.gradleVersionPlugin
}

//All kotlin modules
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

task("staticCheck") {
    afterEvaluate {
        val lintDependencies = subprojects.mapNotNull { "${it.name}:lint" }
        dependsOn(lintDependencies + listOf("ktlintCheck", "detekt"))
    }
}
