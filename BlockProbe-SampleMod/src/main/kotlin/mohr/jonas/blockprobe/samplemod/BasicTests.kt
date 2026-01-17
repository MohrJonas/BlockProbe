package mohr.jonas.blockprobe.samplemod

import com.hypixel.hytale.math.vector.Vector3i
import mohr.jonas.blockprobe.core.assertions.BlockAssertions.assertIsBlock
import mohr.jonas.blockprobe.core.world.WorldInteractionContext
import mohr.jonas.blockprobe.junit6.BlockProbeTest
import org.junit.jupiter.api.Test

@Suppress("FunctionName")
@BlockProbeTest
class BasicTests {
    @Test
    fun placeWoolInCenter_checkCenterBlock_isWool(context: WorldInteractionContext) {
        context.setBlock(Vector3i.ZERO, "Cloth_Block_Wool_Red")
        assertIsBlock(Vector3i.ZERO, "Cloth_Block_Wool_Red")
    }

    @Test
    fun alwaysSucceedingTest(context: WorldInteractionContext) {
    }

    @Test
    fun alwaysFailingTest(context: WorldInteractionContext) {
        throw Exception()
    }
}