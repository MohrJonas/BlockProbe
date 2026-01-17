package mohr.jonas.blockprobe.core.testing

import mohr.jonas.blockprobe.core.data.TestResult

fun interface TestCompletionCallback {
    fun onTestCompletion(testIdentifier: String, testResult: TestResult)
}