package com.igorwojda.showcase.konsisttest

import com.igorwojda.showcase.base.presentation.viewmodel.BaseViewModel
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withParentClassOf
import com.lemonappdev.konsist.api.verify.assert
import org.junit.jupiter.api.Test

// Check test coding rules.
class ViewModelKonsistTest {
    @Test
    fun `every ViewModel class has test`() {
        Konsist.scopeFromProduction()
            .classes()
            .withParentClassOf(BaseViewModel::class)
            .assert { it.hasTest() }
    }
}
