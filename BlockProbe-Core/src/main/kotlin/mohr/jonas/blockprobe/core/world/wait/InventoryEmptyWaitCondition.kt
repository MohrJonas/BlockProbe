package mohr.jonas.blockprobe.core.world.wait

import mohr.jonas.blockprobe.core.BlockUtils
import mohr.jonas.blockprobe.core.assertions.AssertionContext
import mohr.jonas.blockprobe.core.data.Position
import mohr.jonas.blockprobe.core.fixture.TestBedUtils

class InventoryEmptyWaitCondition(private val position: Position) : WaitCondition {
    override fun isComplete(): Boolean {
        val bed = AssertionContext.testBed.get()
        val unprojectedPosition = TestBedUtils.unproject(bed, position)
        val inventory = BlockUtils.getBlockInventory(bed.world, unprojectedPosition)
        return inventory.isEmpty
    }
}