package com.igorwojda.showcase.konsisttest

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import org.junit.jupiter.api.Test

// Check architecture coding rules.
class CleanArchitectureKonsistTest {

    @Test
    fun `clean architecture layers have correct dependencies`() {
        Konsist
            .scopeFromProduction()
            .assertArchitecture {
                // Define layers
                val packagePrefix = "com.igorwojda.showcase"
                val domain = Layer("Domain", "$packagePrefix..domain..")
                val presentation = Layer("Presentation", "$packagePrefix..presentation..")
                val data = Layer("Data", "$packagePrefix..data..")

                // Define architecture assertions
                domain.dependsOnNothing()
                presentation.dependsOn(domain)
                data.dependsOn(domain)
            }
    }
}
