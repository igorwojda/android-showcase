package com.igorwojda.showcase.konsisttest

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.functions
import com.lemonappdev.konsist.api.verify.assertFalse
import org.junit.jupiter.api.Test

// Check test coding rules.
class TestKonsistTest {

    @Test
    fun `don't use JUnit4 Test annotation`() {
        Konsist.scopeFromProject()
            .classes()
            .functions()
            .assertFalse { it.hasAnnotationsWithAllNames("org.junit.Test") } // should be only org.junit.jupiter.api.Test
    }
}
