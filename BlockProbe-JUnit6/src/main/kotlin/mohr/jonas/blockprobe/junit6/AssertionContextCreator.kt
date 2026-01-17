package mohr.jonas.blockprobe.junit6

import mohr.jonas.blockprobe.core.assertions.AssertionContext
import mohr.jonas.blockprobe.core.world.TestBedFetcher
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.Extension
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ExtensionContext.Namespace

class AssertionContextCreator : Extension, BeforeEachCallback {
    override fun beforeEach(extensionContext: ExtensionContext) {
        val fetcher = extensionContext.getStore(Namespace.GLOBAL).get(TestBedFetcher::class) as TestBedFetcher
        AssertionContext.testBed.set(fetcher.getInteractionContextForTest(extensionContext.uniqueId))
    }
}