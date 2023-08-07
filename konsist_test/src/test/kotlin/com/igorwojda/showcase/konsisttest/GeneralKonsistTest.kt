package com.igorwojda.showcase.konsisttest

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.properties
import com.lemonappdev.konsist.api.verify.assert
import com.lemonappdev.konsist.api.verify.assertNot
import org.junit.jupiter.api.Test

import java.util.Locale

// Check General coding rules.
class GeneralKonsistTest {
    @Test
    fun `no field should have 'm' prefix`() {
        Konsist.scopeFromProject()
            .classes()
            .properties()
            .assertNot {
                val secondCharacterIsUppercase = it.name.getOrNull(1)?.isUpperCase() ?: false
                it.name.startsWith('m') && secondCharacterIsUppercase
            }
    }

    @Test
    fun `every constructor parameter has name derived from parameter type`() {
        Konsist.scopeFromProject()
            .classes()
            .flatMap { it.constructors }
            .flatMap { it.parameters }
            .assert {
                val nameTitleCase = it.name.replaceFirstChar { char -> char.titlecase(Locale.getDefault()) }
                nameTitleCase == it.type.sourceType
            }
    }

    @Test
    fun `no class should use Android util logging`() {
        Konsist.scopeFromProject()
            .files
            .assertNot { it.hasImports("android.util.Log") }
    }
}
