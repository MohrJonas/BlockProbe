package mohr.jonas.blockprobe.selftest.tests

import com.hypixel.hytale.math.vector.Vector3i
import mohr.jonas.blockprobe.core.assertions.BlockAssertions.assertIsBlock
import mohr.jonas.blockprobe.core.exceptions.PositionOutsideOfTestBedException
import mohr.jonas.blockprobe.core.world.WorldInteractionContext
import mohr.jonas.blockprobe.junit6.BlockProbeTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@Suppress("FunctionName")
@BlockProbeTest
class UnprojectionTests {
    @Test
    fun unproject_outOfBoundsX_throwsException(context: WorldInteractionContext) {
        assertThrows<PositionOutsideOfTestBedException> {
            context.setBlock(Vector3i(context.bedSize.width, 0, 0), "Empty")
        }
    }

    @Test
    fun unproject_outOfBoundsY_throwsException(context: WorldInteractionContext) {
        assertThrows<PositionOutsideOfTestBedException> {
            context.setBlock(Vector3i(0, context.bedSize.height, 0), "Empty")
        }
    }

    @Test
    fun unproject_outOfBoundsZ_throwsException(context: WorldInteractionContext) {
        assertThrows<PositionOutsideOfTestBedException> {
            context.setBlock(Vector3i(0, 0, context.bedSize.depth), "Empty")
        }
    }

    @Test
    fun unproject_multipleTimes_returnsSameValue(context: WorldInteractionContext) {
        context.setBlock(Vector3i(2, 1, 0), "Cloth_Block_Wool_Red")
        assertIsBlock(Vector3i(2, 1, 0), "Cloth_Block_Wool_Red")
    }
}