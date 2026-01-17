package mohr.jonas.blockprobe.junit6

import mohr.jonas.blockprobe.core.world.TestBedFetcher
import mohr.jonas.blockprobe.core.world.WorldInteractionContext
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ExtensionContext.Namespace
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.support.TypeBasedParameterResolver

class WorldInteractionContextResolver : TypeBasedParameterResolver<WorldInteractionContext>() {
    override fun resolveParameter(
        parameterContext: ParameterContext, extensionContext: ExtensionContext
    ): WorldInteractionContext {
        val fetcher = extensionContext.getStore(Namespace.GLOBAL).get(TestBedFetcher::class) as TestBedFetcher
        return WorldInteractionContext(fetcher.getInteractionContextForTest(extensionContext.uniqueId))
    }
}