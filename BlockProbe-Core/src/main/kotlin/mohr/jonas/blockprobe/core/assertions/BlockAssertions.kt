package mohr.jonas.blockprobe.core.assertions

import com.hypixel.hytale.server.core.inventory.ItemStack
import mohr.jonas.blockprobe.core.BlockUtils
import mohr.jonas.blockprobe.core.data.Position
import mohr.jonas.blockprobe.core.fixture.TestBedUtils
import org.assertj.core.api.Assertions.assertThat

object BlockAssertions {
    @JvmStatic
    fun assertIsBlock(position: Position, blockId: String) {
        val bed = AssertionContext.testBed.get()
        val unprojectedPosition = TestBedUtils.unproject(bed, position)
        val actualBlockId = bed.world.getBlockType(unprojectedPosition.asVector3i())?.id
        assertThat(blockId)
            .`as`("Provided and actual block-ids should match")
            .isEqualTo(actualBlockId)
    }

    @JvmStatic
    fun assertIsInventoryEmpty(position: Position) {
        val bed = AssertionContext.testBed.get()
        val unprojectedPosition = TestBedUtils.unproject(bed, position)
        val inventory = BlockUtils.getBlockInventory(bed.world, unprojectedPosition)
        assertThat(inventory.isEmpty)
            .`as`("Item container should be empty")
            .isTrue
    }

    @JvmStatic
    fun assertInventoryContainsItems(position: Position, allowAdditional: Boolean, vararg items: ItemStack) {
        val bed = AssertionContext.testBed.get()
        val unprojectedPosition = TestBedUtils.unproject(bed, position)
        val inventory = BlockUtils.getBlockInventory(bed.world, unprojectedPosition)
        assertThat(inventory)
            .`as`("Item container should contain provided items")
            .matches { container ->
                val allStacks = (0 until container.capacity)
                    .mapNotNull { container.getItemStack(it.toShort()) }
                    .toList()
                (allowAdditional || allStacks.size == items.size) && allStacks.containsAll(items.toList())
            }
    }
}