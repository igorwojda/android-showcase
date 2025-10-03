package com.igorwojda.showcase.konsisttest

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.jupiter.api.Test

// Check architecture coding rules.
class ModuleKonsistTest {
    @Test
    fun `every file in module reside in module specific package`() {
        Konsist
            .scopeFromProject()
            .files
            .assertTrue {
                val modulePackageName =
                    it.moduleName
                        .lowercase()
                        .replace("/", ".")
                        .replace("-", "")

                val fullyQualifiedPackageName = "com.igorwojda.showcase.$modulePackageName"

                it.packagee?.name?.startsWith(fullyQualifiedPackageName)
            }
    }
}
