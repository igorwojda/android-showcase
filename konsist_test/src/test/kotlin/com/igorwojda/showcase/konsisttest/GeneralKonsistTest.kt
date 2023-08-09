package com.igorwojda.showcase.konsisttest

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.properties
import com.lemonappdev.konsist.api.verify.assert
import com.lemonappdev.konsist.api.verify.assertNot
import org.junit.jupiter.api.Test

// Check General coding rules.
class GeneralKonsistTest {
    @Test
    fun `package name must match file path`() {
        Konsist.scopeFromProject()
            .packages
            .assert { it.hasMatchingPath }
    }

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
    fun `no class should use Android util logging`() {
        Konsist.scopeFromProject()
            .files
            .assertNot { it.hasImports("android.util.Log") }
    }

    fun `no empty files allowed`() {
        Konsist.scopeFromProject()
            .files
            .assertNot { it.text.isEmpty() }
    }
}
