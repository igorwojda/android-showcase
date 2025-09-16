plugins {
    id("com.igorwojda.showcase.convention.test.library")
}

android {
    namespace = "com.igorwojda.showcase.konsistTest"
}

/*
Exclude konsistTest from general test tasks (e.g. ./gradlew testDebugUnitTest)
Konsist tests verify architectural rules and should run separately from unit tests
Unit tests are executed explicitly via: ./gradlew konsistTest:test
*/
tasks.matching { it.name.startsWith("test") }.configureEach {
    onlyIf {
        gradle.startParameter.taskRequests.any { request ->
            request.args.any { arg ->
                arg.contains("konsistTest") || (arg.contains(":test") && arg.startsWith(":konsistTest"))
            }
        }
    }
}

dependencies {
    implementation(projects.feature.base)

    testImplementation(projects.library.testUtils)
    testImplementation(libs.bundles.test)
    testImplementation(libs.viewmodel.ktx)
}
