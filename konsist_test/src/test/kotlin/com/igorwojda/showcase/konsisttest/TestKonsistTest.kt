package com.igorwojda.showcase.konsisttest

import com.igorwojda.showcase.base.presentation.viewmodel.BaseViewModel
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.functions
import com.lemonappdev.konsist.api.ext.list.withParentClassOf
import com.lemonappdev.konsist.api.verify.assert
import com.lemonappdev.konsist.api.verify.assertNot
import org.junit.jupiter.api.Test

// Check test coding rules.
class TestKonsistTest {
    @Test
    fun `every ViewModel class has test`() {
        Konsist.scopeFromProduction()
            .classes()
            .withParentClassOf(BaseViewModel::class)
            .assert { it.hasTest() }
    }

    @Test
    fun `don't use JUnit4 Test annotation`() {
        Konsist.scopeFromProject()
            .classes()
            .functions()
            .assertNot { it.hasAnnotations("org.junit.Test") } // should be only org.junit.jupiter.api.Test
    }
}
