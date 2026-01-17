package mohr.jonas.blockprobe.samplemod

import com.hypixel.hytale.server.core.inventory.ItemStack
import mohr.jonas.blockprobe.core.assertions.BlockAssertions.assertInventoryContainsItems
import mohr.jonas.blockprobe.core.assertions.BlockAssertions.assertIsInventoryEmpty
import mohr.jonas.blockprobe.core.data.Direction
import mohr.jonas.blockprobe.core.data.Position
import mohr.jonas.blockprobe.core.world.WorldInteractionContext
import mohr.jonas.blockprobe.core.world.wait.InventoryEmptyWaitCondition
import mohr.jonas.blockprobe.junit6.BlockProbeTest
import org.junit.jupiter.api.Test

@Suppress("FunctionName")
@BlockProbeTest
class BasicTestsKt {
    @Test
    fun placeWoolInCenter_checkCenterBlock_isWool(context: WorldInteractionContext) {
        // Arrange
        context
            .setBlock(Position.Zero, "Furniture_Crude_Chest_Small")
            .setBlock(Direction.Right, "Pipe_Item")
            .setBlock(Direction.Right, "Furniture_Crude_Chest_Small")

        // Act
        context.addItemToBlockInventory(Position.Zero, ItemStack("Ore_Adamantite", 10))
        context.waitUntil(InventoryEmptyWaitCondition(Position.Zero))

        // Assert
        assertIsInventoryEmpty(Position.Zero)
        assertInventoryContainsItems(Position(2, 0, 0), false, ItemStack("Ore_Adamantite", 10))
    }

    @Test
    fun alwaysSucceedingTest(context: WorldInteractionContext) {
    }

    @Test
    fun alwaysFailingTest(context: WorldInteractionContext) {
        throw Exception()
    }
}