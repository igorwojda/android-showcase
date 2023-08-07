package com.igorwojda.showcase.testutils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

/**
 * A JUnit Test Extension that swaps the coroutine dispatcher for the TestDispatcher.
 *
 * Add this JUnit 5 extension to your test class using
 * @JvmField
 * @RegisterExtension
 * val coroutinesTestExtension = CoroutinesTestExtension()
 */
@ExperimentalCoroutinesApi
class CoroutinesTestDispatcherExtension : BeforeEachCallback, AfterEachCallback {

    override fun beforeEach(context: ExtensionContext?) {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    override fun afterEach(context: ExtensionContext?) {
        Dispatchers.resetMain()
    }
}
