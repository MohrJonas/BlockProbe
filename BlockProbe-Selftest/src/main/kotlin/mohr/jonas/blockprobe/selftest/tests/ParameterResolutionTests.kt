package mohr.jonas.blockprobe.selftest.tests

import mohr.jonas.blockprobe.core.world.WorldInteractionContext
import mohr.jonas.blockprobe.junit6.BlockProbeTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

@Suppress("FunctionName")
@BlockProbeTest
class ParameterResolutionTests {
    @Test
    fun testMethod_noWorldInteractionContext_resolvesCorrectly() {
    }

    @Test
    fun testMethod_worldInteractionContext_acquiresNonNullContext(context: WorldInteractionContext) {
        Assertions.assertNotNull(context)
    }
}