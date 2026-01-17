package mohr.jonas.blockprobe.core.testing

import mohr.jonas.blockprobe.core.world.TestBedFetcher

interface TestRunner {
    fun runTests(callback: TestCompletionCallback, fetcher: TestBedFetcher)
    fun teardownTests()
    fun registerTestClass(testClass: Class<*>)
    fun registerAutoDiscoveredTests(discoverer: TestDiscoverer)
}