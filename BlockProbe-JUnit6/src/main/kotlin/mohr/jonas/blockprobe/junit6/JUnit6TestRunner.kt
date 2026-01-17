package mohr.jonas.blockprobe.junit6

import mohr.jonas.blockprobe.core.testing.TestCompletionCallback
import mohr.jonas.blockprobe.core.testing.TestDiscoverer
import mohr.jonas.blockprobe.core.testing.TestRunner
import mohr.jonas.blockprobe.core.world.TestBedFetcher
import org.junit.platform.engine.discovery.DiscoverySelectors
import org.junit.platform.launcher.LauncherDiscoveryRequest
import org.junit.platform.launcher.core.LauncherConfig
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder
import org.junit.platform.launcher.core.LauncherFactory

class JUnit6TestRunner : TestRunner {

    private val testClasses = mutableListOf<Class<*>>()

    override fun runTests(
        callback: TestCompletionCallback,
        fetcher: TestBedFetcher
    ) {
        val testDiscoveryRequest = buildDiscoveryRequestBuilder()
        val config = LauncherConfig.builder()
            .enableLauncherSessionListenerAutoRegistration(false)
            .enableLauncherDiscoveryListenerAutoRegistration(false)
            .enablePostDiscoveryFilterAutoRegistration(false)
            .enableTestExecutionListenerAutoRegistration(false)
            .enableTestEngineAutoRegistration(false)
            .addTestEngines(
                TestEngine()
                    .addStoreEntry(TestCompletionCallback::class, callback)
                    .addStoreEntry(TestBedFetcher::class, fetcher)
            )
            .build()
        LauncherFactory
            .openSession(config)
            .use { it.launcher.execute(testDiscoveryRequest) }
    }

    override fun teardownTests() {}

    override fun registerTestClass(testClass: Class<*>) {
        if (!testClass.isAnnotationPresent(BlockProbeTest::class.java))
            throw IllegalArgumentException("Test classes registered with the ${this::class.simpleName} have to annotated with @BlockProbeTest")
        testClasses.add(testClass)
    }

    override fun registerAutoDiscoveredTests(discoverer: TestDiscoverer) {
        testClasses.addAll(discoverer.discoverTests())
    }

    private fun buildDiscoveryRequestBuilder(): LauncherDiscoveryRequest {
        val discoveryRequestBuilder = LauncherDiscoveryRequestBuilder.request()
        testClasses.forEach { discoveryRequestBuilder.selectors(DiscoverySelectors.selectClass(it)) }
        return discoveryRequestBuilder.build()
    }
}