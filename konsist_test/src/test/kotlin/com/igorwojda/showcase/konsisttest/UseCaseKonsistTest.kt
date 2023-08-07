package com.igorwojda.showcase.konsisttest

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.verify.assert
import org.junit.jupiter.api.Test

// Check test coding rules.
class UseCaseKonsistTest {
    @Test
    fun `every UseCase class has test`() {
        Konsist.scopeFromProduction()
            .classes()
            .withNameEndingWith("UseCase")
            .assert { it.hasTest() }
    }

    @Test
    fun `classes with 'UseCase' suffix should reside in 'domain' and 'usecase' packages`() {
        Konsist.scopeFromProject()
            .classes()
            .withNameEndingWith("UseCase")
            .assert { it.resideInPackage("..domain..usecase..") }
    }

    @Test
    fun `classes with 'UseCase' suffix should have single public method named 'invoke'`() {
        Konsist.scopeFromProject()
            .classes()
            .withNameEndingWith("UseCase")
            .assert {
                val function = it.functions().first()
                it.numDeclarations() == 1 && function.name == "invoke" && function.isPublicOrDefault
            }
    }
}
