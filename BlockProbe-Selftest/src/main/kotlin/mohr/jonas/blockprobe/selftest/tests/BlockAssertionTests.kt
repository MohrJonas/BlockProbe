package mohr.jonas.blockprobe.selftest.tests

import com.hypixel.hytale.math.vector.Vector3i
import com.hypixel.hytale.server.core.inventory.ItemStack
import mohr.jonas.blockprobe.core.assertions.BlockAssertions.assertInventoryContainsItems
import mohr.jonas.blockprobe.core.assertions.BlockAssertions.assertIsInventoryEmpty
import mohr.jonas.blockprobe.core.world.WorldInteractionContext
import mohr.jonas.blockprobe.junit6.BlockProbeTest
import org.junit.jupiter.api.Test

@Suppress("FunctionName")
@BlockProbeTest
class BlockAssertionTests {
    @Test
    fun placeChest_containsNoItems_assertIsEmpty_returnsTrue(context: WorldInteractionContext) {
        context.setBlock(Vector3i.ZERO, "Furniture_Crude_Chest_Small")
        assertIsInventoryEmpty(Vector3i.ZERO)
    }

    @Test
    fun placeChest_containsNoItems_assertContainsItems_emptyList_returnsTrue(context: WorldInteractionContext) {
        context.setBlock(Vector3i.ZERO, "Furniture_Crude_Chest_Small")
        assertInventoryContainsItems(Vector3i.ZERO, false)
    }

    @Test
    fun placeChest_containsRedWool_assertContainsRedWool_returnsTrue(context: WorldInteractionContext) {
        context.setBlock(Vector3i.ZERO, "Furniture_Crude_Chest_Small")
        context.addItemToBlockInventory(Vector3i.ZERO, ItemStack("Cloth_Block_Wool_Red"))
        assertInventoryContainsItems(Vector3i.ZERO, false, ItemStack("Cloth_Block_Wool_Red"))
    }

    @Test
    fun placeChest_containsRedWoolAndWhiteWool_assertContainsRedWool_allowAdditional_returnsTrue(context: WorldInteractionContext) {
        context.setBlock(Vector3i.ZERO, "Furniture_Crude_Chest_Small")
        context.addItemToBlockInventory(Vector3i.ZERO, ItemStack("Cloth_Block_Wool_Red"))
        context.addItemToBlockInventory(Vector3i.ZERO, ItemStack("Cloth_Block_Wool_White"))
        assertInventoryContainsItems(Vector3i.ZERO, true, ItemStack("Cloth_Block_Wool_Red"))
    }
}