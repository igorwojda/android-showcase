import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        // Android plugin & support libraries
        google()

        // Main open-source repository
        jcenter()

        // Ktlint Gradle
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

subprojects {
    tasks.withType<Test> {
        maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).takeIf { it > 0 } ?: 1
    }

    plugins.apply(GradlePluginId.ktlintGradle)

    ktlint {
        version.set(CoreVersion.ktlint)
        verbose.set(true)
        android.set(true)
    }

    plugins.apply(GradlePluginId.detekt)

    detekt {
        config = files("${project.rootDir}/config/detekt.yml")
        parallel = true
    }
}

// All kotlin modules
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
}

task("staticCheck") {
    afterEvaluate {
        val lintDependencies = subprojects.mapNotNull { "${it.name}:lint" }
        dependsOn(lintDependencies + listOf("ktlintCheck", "detekt"))
    }
}

/*Do not show pre-release version of library in generated dependency report*/
tasks {
    "dependencyUpdates"(DependencyUpdatesTask::class) {
        resolutionStrategy {
            componentSelection {
                all {
                    val rejected = listOf("alpha", "beta", "rc", "cr", "m", "preview")
                        .map { qualifier -> Regex("(?i).*[.-]$qualifier[.\\d-]*") }
                        .any { it.matches(candidate.version) }
                    if (rejected) {
                        reject("Release candidate")
                    }
                }
            }
        }
    }
}
