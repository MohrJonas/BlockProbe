package mohr.jonas.blockprobe.core.assertions

import com.hypixel.hytale.math.vector.Vector3i
import com.hypixel.hytale.server.core.inventory.ItemStack
import com.hypixel.hytale.server.core.universe.world.meta.state.ItemContainerState
import mohr.jonas.blockprobe.core.fixture.TestBedUtils
import org.assertj.core.api.Assertions.assertThat

object BlockAssertions {
    @JvmStatic
    fun assertIsBlock(position: Vector3i, blockId: String) {
        val bed = AssertionContext.testBed.get()
        val unprojectedPosition = TestBedUtils.unproject(bed, position)
        val actualBlockId = bed.world.getBlockType(unprojectedPosition)?.id
        assertThat(blockId)
            .`as`("Provided and actual block-ids should match")
            .isEqualTo(actualBlockId)
    }

    @JvmStatic
    fun assertIsInventoryEmpty(position: Vector3i) {
        val bed = AssertionContext.testBed.get()
        val unprojectedPosition = TestBedUtils.unproject(bed, position)
        val state = bed.world.getState(unprojectedPosition.x, unprojectedPosition.y, unprojectedPosition.z, false)
        assertThat(state)
            .`as`("State of block should be instance of ItemContainerState")
            .isInstanceOf(ItemContainerState::class.java)
        state as ItemContainerState
        assertThat(state.itemContainer.isEmpty)
            .`as`("Item container should be empty")
            .isTrue
    }

    @JvmStatic
    fun assertInventoryContainsItems(position: Vector3i, allowAdditional: Boolean, vararg items: ItemStack) {
        val bed = AssertionContext.testBed.get()
        val unprojectedPosition = TestBedUtils.unproject(bed, position)
        val state = bed.world.getState(unprojectedPosition.x, unprojectedPosition.y, unprojectedPosition.z, false)
        assertThat(state)
            .`as`("State of block should be instance of ItemContainerState")
            .isInstanceOf(ItemContainerState::class.java)
        state as ItemContainerState
        assertThat(state.itemContainer)
            .`as`("Item container should contain provided items")
            .matches { container ->
                val allStacks = (0 until container.capacity)
                    .mapNotNull { container.getItemStack(it.toShort()) }
                    .toList()
                (allowAdditional || allStacks.size == items.size) && allStacks.containsAll(items.toList())
            }
    }
}