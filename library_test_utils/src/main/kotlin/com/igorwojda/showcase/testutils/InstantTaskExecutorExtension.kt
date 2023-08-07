package com.igorwojda.showcase.testutils

import android.annotation.SuppressLint
import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

/**
 * A JUnit Test Extension that swaps the background executor used by the Architecture Components with a
 * different one which executes each task synchronously.
 * <p>
 * Extension can be used for your host side tests that use Architecture Components.
 */
// Android lint complains about exceeding access rights to ArchTaskExecutor
// See https://issuetracker.google.com/u/0/issues/79189568
@SuppressLint("RestrictedApi")
class InstantTaskExecutorExtension : BeforeEachCallback, AfterEachCallback {

    override fun beforeEach(context: ExtensionContext) {
        ArchTaskExecutor.getInstance().setDelegate(
            object : TaskExecutor() {
                override fun executeOnDiskIO(runnable: Runnable) {
                    runnable.run()
                }

                override fun postToMainThread(runnable: Runnable) {
                    runnable.run()
                }

                override fun isMainThread() = true
            },
        )
    }

    override fun afterEach(context: ExtensionContext?) {
        ArchTaskExecutor.getInstance().setDelegate(null)
    }
}
