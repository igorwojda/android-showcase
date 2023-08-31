package com.igorwojda.showcase.konsisttest

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.verify.assert
import org.junit.jupiter.api.Test
import java.util.Locale

// Check test coding rules.
class UseCaseKonsistTest {
    @Test
    fun `every use case class has test`() {
        Konsist.scopeFromProduction()
            .classes()
            .withNameEndingWith("UseCase")
            .assert { it.hasTest() }
    }

    @Test
    fun `every use case constructor has alphabetically ordered parameters`() {
        Konsist.scopeFromProject()
            .classes()
            .withNameEndingWith("UseCase")
            .flatMap { it.constructors }
            .assert {
                val names = it.parameters.map { parameter -> parameter.name }
                val sortedNames = names.sorted()
                names == sortedNames
            }
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
            .assert { it ->
                val containsPublicInvokeFunction = it.containsFunction { function ->
                    function.name == "invoke" && function.hasPublicOrDefaultModifier
                }
                
                containsPublicInvokeFunction && it.numPublicOrDefaultDeclarations() == 1

            }
    }

    @Test
    fun `every use case constructor parameter has name derived from parameter type`() {
        Konsist.scopeFromProject()
            .classes()
            .withNameEndingWith("UseCase")
            .flatMap { it.constructors }
            .flatMap { it.parameters }
            .assert {
                val nameTitleCase = it.name.replaceFirstChar { char -> char.titlecase(Locale.getDefault()) }
                nameTitleCase == it.type.sourceType
            }
    }
}
