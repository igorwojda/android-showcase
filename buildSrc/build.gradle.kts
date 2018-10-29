plugins {
    `kotlin-dsl`
}

/**
 * Fix for "Gradle 4.10-rc1 can't resolve dependency kotlin-scripting-compiler-embeddable"
 * With Kotlin 1.2.60, the Kotlin Gradle Plugin driving the kotlin compiler. Now
 * it requires extra dependencies that aren't required by Gradle Kotlin DSL scripts alone and aren't embedded into Gradle.
 */
repositories {
    jcenter()
}