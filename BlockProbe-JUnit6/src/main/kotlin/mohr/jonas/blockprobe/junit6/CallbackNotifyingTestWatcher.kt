package mohr.jonas.blockprobe.junit6

import mohr.jonas.blockprobe.core.data.TestResult
import mohr.jonas.blockprobe.core.data.TestStatus
import mohr.jonas.blockprobe.core.testing.TestCompletionCallback
import org.junit.jupiter.api.extension.Extension
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ExtensionContext.Namespace
import org.junit.jupiter.api.extension.TestWatcher
import org.opentest4j.TestAbortedException
import java.util.*

class CallbackNotifyingTestWatcher : Extension, TestWatcher {

    override fun testAborted(context: ExtensionContext, cause: Throwable?) {
        notifyCallback(
            context, context.uniqueId, TestResult(
                TestStatus.Failed,
                TestAbortedException("Test was aborted", cause)
            )
        )
    }

    override fun testDisabled(context: ExtensionContext, reason: Optional<String>) {
        notifyCallback(
            context, context.uniqueId, TestResult(
                TestStatus.Skipped,
                null
            )
        )
    }

    override fun testFailed(context: ExtensionContext, cause: Throwable?) {
        notifyCallback(
            context, context.uniqueId, TestResult(
                TestStatus.Failed,
                cause
            )
        )
    }

    override fun testSuccessful(context: ExtensionContext) {
        notifyCallback(
            context, context.uniqueId, TestResult(
                TestStatus.Succeeded,
                null
            )
        )
    }

    private fun notifyCallback(extensionContext: ExtensionContext, testIdentifier: String, result: TestResult) {
        val callback = extensionContext
            .getStore(Namespace.GLOBAL)
            .get(TestCompletionCallback::class) as TestCompletionCallback
        callback.onTestCompletion(testIdentifier, result)
    }
}