package com.igorwojda.showcase.konsisttest

import com.igorwojda.showcase.base.presentation.viewmodel.BaseViewModel
import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.modifierprovider.withoutAllModifiers
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.ext.list.withParentClassOf
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.jupiter.api.Test
import java.util.Locale

// Check test coding rules.
class ViewModelKonsistTest {
    @Test
    fun `every view model has test`() {
        Konsist.scopeFromProduction()
            .classes()
            .withParentClassOf(BaseViewModel::class)
            .assertTrue { it.hasTestClasses(testPropertyName = "cut") }
    }

    @Test
    fun `every view model constructor parameter has name derived from parameter type`() {
        Konsist.scopeFromProject()
            .classes()
            .withNameEndingWith("ViewModel")
            .withoutAllModifiers(KoModifier.ABSTRACT)
            .flatMap { it.constructors }
            .flatMap { it.parameters }
            .assertTrue {
                val nameTitleCase = it.name.replaceFirstChar { char -> char.titlecase(Locale.getDefault()) }
                nameTitleCase == it.type.sourceType
            }
    }
}
