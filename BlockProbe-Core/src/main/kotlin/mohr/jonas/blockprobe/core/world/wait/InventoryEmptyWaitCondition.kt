package mohr.jonas.blockprobe.core.world.wait

import com.hypixel.hytale.server.core.universe.world.meta.state.ItemContainerState
import mohr.jonas.blockprobe.core.assertions.AssertionContext
import mohr.jonas.blockprobe.core.data.Position
import mohr.jonas.blockprobe.core.fixture.TestBedUtils

class InventoryEmptyWaitCondition(private val position: Position) : WaitCondition {
    override fun isComplete(): Boolean {
        val bed = AssertionContext.testBed.get()
        val unprojectedPosition = TestBedUtils.unproject(bed, position)
        val items = (bed
            .world
            .getState(
                unprojectedPosition.x,
                unprojectedPosition.y,
                unprojectedPosition.z,
                false
            ) as ItemContainerState)
            .itemContainer
        return items.isEmpty
    }
}