package com.igorwojda.showcase.library.testutils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

/**
 * Add this JUnit 5 extension to your test class using
 * @JvmField
 * @RegisterExtension
 * val coroutinesTestExtension = CoroutinesTestExtension()
 */

/*
* A JUnit Test Extension that swaps the coroutine dispatcher one which executes each task synchronously.
* You can use this rule for your host side tests that use Kotlin Coroutines.
*/
@ExperimentalCoroutinesApi
class CoroutinesTestExtension(
    private val standardTestDispatcher: TestDispatcher = StandardTestDispatcher(),
) : BeforeEachCallback, AfterEachCallback {
    override fun beforeEach(context: ExtensionContext?) {
        Dispatchers.setMain(standardTestDispatcher)
    }

    override fun afterEach(context: ExtensionContext?) {
        Dispatchers.resetMain()
    }
}
