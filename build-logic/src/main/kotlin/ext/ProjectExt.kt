package ext

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.the

/**
 * Returns "libs" version catalog.
 */
val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

/**
 * Returns version accessor for easier access to versions from the catalog.
 */
val Project.versions: VersionCatalog
    get() = libs


/**
 * Returns "libs" from version catalog.
 */
val Project.libs2: LibrariesForLibs
    get() = the<LibrariesForLibs>()

/**
 * Returns "versions" from version catalog.
 */
val Project.versions2: LibrariesForLibs.VersionAccessors
    get() = the<LibrariesForLibs>().versions
