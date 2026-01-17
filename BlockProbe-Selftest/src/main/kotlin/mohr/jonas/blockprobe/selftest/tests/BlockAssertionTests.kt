package mohr.jonas.blockprobe.selftest.tests

import com.hypixel.hytale.server.core.inventory.ItemStack
import mohr.jonas.blockprobe.core.assertions.BlockAssertions.assertInventoryContainsItems
import mohr.jonas.blockprobe.core.assertions.BlockAssertions.assertIsInventoryEmpty
import mohr.jonas.blockprobe.core.data.Position
import mohr.jonas.blockprobe.core.world.WorldInteractionContext
import mohr.jonas.blockprobe.junit6.BlockProbeTest
import org.junit.jupiter.api.Test

@Suppress("FunctionName")
@BlockProbeTest
class BlockAssertionTests {
    @Test
    fun placeChest_containsNoItems_assertIsEmpty_returnsTrue(context: WorldInteractionContext) {
        context.setBlock(Position.Zero, "Furniture_Crude_Chest_Small")
        assertIsInventoryEmpty(Position.Zero)
    }

    @Test
    fun placeChest_containsNoItems_assertContainsItems_emptyList_returnsTrue(context: WorldInteractionContext) {
        context.setBlock(Position.Zero, "Furniture_Crude_Chest_Small")
        assertInventoryContainsItems(Position.Zero, false)
    }

    @Test
    fun placeChest_containsRedWool_assertContainsRedWool_returnsTrue(context: WorldInteractionContext) {
        context.setBlock(Position.Zero, "Furniture_Crude_Chest_Small")
        context.addItemToBlockInventory(Position.Zero, ItemStack("Cloth_Block_Wool_Red"))
        assertInventoryContainsItems(Position.Zero, false, ItemStack("Cloth_Block_Wool_Red"))
    }

    @Test
    fun placeChest_containsRedWoolAndWhiteWool_assertContainsRedWool_allowAdditional_returnsTrue(context: WorldInteractionContext) {
        context.setBlock(Position.Zero, "Furniture_Crude_Chest_Small")
        context.addItemToBlockInventory(Position.Zero, ItemStack("Cloth_Block_Wool_Red"))
        context.addItemToBlockInventory(Position.Zero, ItemStack("Cloth_Block_Wool_White"))
        assertInventoryContainsItems(Position.Zero, true, ItemStack("Cloth_Block_Wool_Red"))
    }
}