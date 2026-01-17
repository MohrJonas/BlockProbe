package mohr.jonas.blockprobe.selftest.tests

import com.hypixel.hytale.math.vector.Vector3i
import mohr.jonas.blockprobe.core.data.Direction
import mohr.jonas.blockprobe.core.world.WorldInteractionContext
import mohr.jonas.blockprobe.junit6.BlockProbeTest
import org.junit.jupiter.api.Test

@Suppress("FunctionName")
@BlockProbeTest
class RelativePositioningTests {
    @Test
    fun relativePositioning_works(context: WorldInteractionContext) {
        context
            .setBlock(Vector3i.ZERO, "Cloth_Block_Wool_Red")
            .setBlock(Direction.Above, "Cloth_Block_Wool_White")
            .setBlock(Direction.Behind, "Cloth_Block_Wool_Cyan")
            .setBlock(Direction.LeftOf, "Cloth_Block_Wool_Blue")
    }
}