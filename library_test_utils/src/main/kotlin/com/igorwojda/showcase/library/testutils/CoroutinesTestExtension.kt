package com.igorwojda.showcase.library.testutils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
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
@ExperimentalCoroutinesApi
class CoroutinesTestExtension(
    private val testCoroutineDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
) : BeforeEachCallback, AfterEachCallback, TestCoroutineScope by TestCoroutineScope(testCoroutineDispatcher) {

    override fun beforeEach(context: ExtensionContext?) {
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    override fun afterEach(context: ExtensionContext?) {
        cleanupTestCoroutines()
        Dispatchers.resetMain()
    }
}
