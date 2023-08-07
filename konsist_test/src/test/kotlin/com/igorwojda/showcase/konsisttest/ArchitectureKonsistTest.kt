package com.igorwojda.showcase.konsisttest

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.verify.assert
import org.junit.jupiter.api.Test

// Check architecture coding rules.
class ArchitectureKonsistTest {

    // TODO: Add architecture test
    @Test
    fun `every file in the feature module reside in feature specific package`() {
        Konsist.scopeFromProject()
            .files
            .assert {
                // com.igorwojda.showcase.feature.album.presentation.screen.albumdetail
                val packageName = it.moduleName.lowercase().replace("_", "")
                val fullyQualifiedPackageName = "com.igorwojda.showcase.feature.${packageName}"
                it.packagee?.fullyQualifiedName?.startsWith(fullyQualifiedPackageName)
            }
    }
}
