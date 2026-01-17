package mohr.jonas.blockprobe.core.world

import com.hypixel.hytale.server.core.inventory.ItemStack
import com.hypixel.hytale.server.core.inventory.transaction.ItemStackTransaction
import com.hypixel.hytale.server.core.universe.world.meta.state.ItemContainerState
import mohr.jonas.blockprobe.core.data.Position
import mohr.jonas.blockprobe.core.data.TestBedSize
import mohr.jonas.blockprobe.core.fixture.TestBed
import mohr.jonas.blockprobe.core.fixture.TestBedUtils
import mohr.jonas.blockprobe.core.world.wait.DurationWaitCondition
import mohr.jonas.blockprobe.core.world.wait.WaitCondition
import java.time.Duration

class WorldInteractionContext(private val testBed: TestBed) {

    fun setBlock(position: Position, blockId: String): PositionedWorldInteractionContext {
        val unprojectedPosition = TestBedUtils.unproject(testBed, position)
        testBed.world.setBlock(
            unprojectedPosition.x,
            unprojectedPosition.y,
            unprojectedPosition.z,
            blockId
        )
        return PositionedWorldInteractionContext(this, position)
    }

    fun addItemToBlockInventory(position: Position, item: ItemStack): ItemStackTransaction {
        val unprojectedPosition = TestBedUtils.unproject(testBed, position)
        return (testBed
            .world
            .getState(
                unprojectedPosition.x,
                unprojectedPosition.y,
                unprojectedPosition.z,
                false
            ) as ItemContainerState)
            .itemContainer
            .addItemStack(item)
    }

    fun wait(duration: Duration) {
        waitUntil(DurationWaitCondition(duration))
    }

    fun waitUntil(condition: WaitCondition) {
        while (!condition.isComplete())
            Thread.sleep(25)
    }

    val bedSize = TestBedSize(testBed.editableBoundingBox.width, testBed.editableBoundingBox.height, testBed.editableBoundingBox.depth)
}