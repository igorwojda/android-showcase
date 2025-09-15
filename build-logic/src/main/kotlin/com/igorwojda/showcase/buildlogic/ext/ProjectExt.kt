package com.igorwojda.showcase.buildlogic.ext

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.the

/**
 * Returns "libs" from version catalog.
 */
val Project.libs: LibrariesForLibs
    get() = the<LibrariesForLibs>()

/**
 * Returns "versions" from version catalog.
 */
val Project.versions: LibrariesForLibs.VersionAccessors
    get() = the<LibrariesForLibs>().versions
