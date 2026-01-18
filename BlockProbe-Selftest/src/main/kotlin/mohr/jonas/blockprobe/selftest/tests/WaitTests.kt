package mohr.jonas.blockprobe.selftest.tests

import com.hypixel.hytale.server.core.inventory.ItemStack
import mohr.jonas.blockprobe.core.data.Position
import mohr.jonas.blockprobe.core.world.WorldInteractionContext
import mohr.jonas.blockprobe.core.world.wait.InventoryContainsWaitCondition
import mohr.jonas.blockprobe.core.world.wait.InventoryEmptyWaitCondition
import mohr.jonas.blockprobe.junit6.BlockProbeTest
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Percentage
import org.junit.jupiter.api.Test
import java.time.Duration
import java.util.concurrent.ForkJoinPool

@Suppress("FunctionName")
@BlockProbeTest
class WaitTests {
    @Test
    fun wait_waitsForDuration(context: WorldInteractionContext) {
        val old = System.currentTimeMillis()
        context.wait(Duration.ofSeconds(5))
        val new = System.currentTimeMillis()
        assertThat(new - old).isCloseTo(5000, Percentage.withPercentage(1.0))
    }

    @Test
    fun wait_inventoryEmptyWaitCondition_waitsCorrectly(context: WorldInteractionContext) {
        context.setBlock(Position.Zero, "Furniture_Crude_Chest_Small")
        context.addItemToBlockInventory(Position.Zero, ItemStack("Furniture_Crude_Chest_Small"))
        ForkJoinPool.commonPool().execute {
            Thread.sleep(5000)
            context.removeItemFromBlockInventory(Position.Zero, ItemStack("Furniture_Crude_Chest_Small"))
        }
        val old = System.currentTimeMillis()
        context.waitUntil(InventoryEmptyWaitCondition(Position.Zero))
        val new = System.currentTimeMillis()
        assertThat(new - old).isCloseTo(5000, Percentage.withPercentage(2.0))
    }

    @Test
    fun wait_inventoryContainsWaitCondition_waitsCorrectly(context: WorldInteractionContext) {
        context.setBlock(Position.Zero, "Furniture_Crude_Chest_Small")
        ForkJoinPool.commonPool().execute {
            Thread.sleep(5000)
            context.addItemToBlockInventory(Position.Zero, ItemStack("Furniture_Crude_Chest_Small"))
        }
        val old = System.currentTimeMillis()
        context.waitUntil(InventoryContainsWaitCondition(Position.Zero, false, ItemStack("Furniture_Crude_Chest_Small")))
        val new = System.currentTimeMillis()
        assertThat(new - old).isCloseTo(5000, Percentage.withPercentage(2.0))
    }
}