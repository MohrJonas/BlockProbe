package mohr.jonas.blockprobe.selftest.tests

import mohr.jonas.blockprobe.core.assertions.BlockAssertions.assertIsBlock
import mohr.jonas.blockprobe.core.data.Direction
import mohr.jonas.blockprobe.core.data.Position
import mohr.jonas.blockprobe.core.world.WorldInteractionContext
import mohr.jonas.blockprobe.junit6.BlockProbeTest
import org.junit.jupiter.api.Test

@Suppress("FunctionName")
@BlockProbeTest
class RelativePositioningTests {
    @Test
    fun relativePositioning_above_placesBlockAbove(context: WorldInteractionContext) {
        context
            .setBlock(Position.Zero, "Cloth_Block_Wool_Red")
            .setBlock(Direction.Above, "Cloth_Block_Wool_White")
        assertIsBlock(Position(0, 1, 0), "Cloth_Block_Wool_White")
    }

    @Test
    fun relativePositioning_below_placesBlockAbove(context: WorldInteractionContext) {
        context
            .setBlock(Position(0, 1, 0), "Cloth_Block_Wool_Red")
            .setBlock(Direction.Below, "Cloth_Block_Wool_White")
        assertIsBlock(Position.Zero, "Cloth_Block_Wool_White")
    }

    @Test
    fun relativePositioning_inFront_placesBlockInFront(context: WorldInteractionContext) {
        context
            .setBlock(Position.Zero, "Cloth_Block_Wool_Red")
            .setBlock(Direction.InFront, "Cloth_Block_Wool_White")
        assertIsBlock(Position(-1, 0, 0), "Cloth_Block_Wool_White")
    }

    @Test
    fun relativePositioning_behind_placesBlockBehind(context: WorldInteractionContext) {
        context
            .setBlock(Position.Zero, "Cloth_Block_Wool_Red")
            .setBlock(Direction.Behind, "Cloth_Block_Wool_White")
        assertIsBlock(Position(1, 0, 0), "Cloth_Block_Wool_White")
    }

    @Test
    fun relativePositioning_left_placesBlockLeft(context: WorldInteractionContext) {
        context
            .setBlock(Position.Zero, "Cloth_Block_Wool_Red")
            .setBlock(Direction.Left, "Cloth_Block_Wool_White")
        assertIsBlock(Position(0, 0, -1), "Cloth_Block_Wool_White")
    }

    @Test
    fun relativePositioning_right_placesBlockRight(context: WorldInteractionContext) {
        context
            .setBlock(Position.Zero, "Cloth_Block_Wool_Red")
            .setBlock(Direction.Right, "Cloth_Block_Wool_White")
        assertIsBlock(Position(0, 0, 1), "Cloth_Block_Wool_White")
    }
}