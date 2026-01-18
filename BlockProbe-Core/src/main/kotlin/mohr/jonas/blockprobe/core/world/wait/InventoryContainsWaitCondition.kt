package mohr.jonas.blockprobe.core.world.wait

import com.hypixel.hytale.server.core.inventory.ItemStack
import mohr.jonas.blockprobe.core.BlockUtils
import mohr.jonas.blockprobe.core.assertions.AssertionContext
import mohr.jonas.blockprobe.core.data.Position
import mohr.jonas.blockprobe.core.fixture.TestBedUtils

class InventoryContainsWaitCondition(
    private val position: Position,
    private val allowAdditional: Boolean,
    private vararg val items: ItemStack
) : WaitCondition {
    override fun isComplete(): Boolean {
        val bed = AssertionContext.testBed.get()
        val unprojectedPosition = TestBedUtils.unproject(bed, position)
        val inventory = BlockUtils.getBlockInventory(bed.world, unprojectedPosition)
        val allStacks = (0 until inventory.capacity).mapNotNull { inventory.getItemStack(it.toShort()) }.toList()
        return (allowAdditional || allStacks.size == items.size) && allStacks.containsAll(items.toList())
    }
}