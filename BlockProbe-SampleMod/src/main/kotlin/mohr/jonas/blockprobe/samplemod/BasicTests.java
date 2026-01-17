package mohr.jonas.blockprobe.samplemod;

import com.hypixel.hytale.server.core.inventory.ItemStack;
import mohr.jonas.blockprobe.core.data.Direction;
import mohr.jonas.blockprobe.core.data.Position;
import mohr.jonas.blockprobe.core.world.WorldInteractionContext;
import mohr.jonas.blockprobe.core.world.wait.InventoryEmptyWaitCondition;
import mohr.jonas.blockprobe.junit6.BlockProbeTest;
import org.junit.jupiter.api.Test;

import static mohr.jonas.blockprobe.core.assertions.BlockAssertions.assertInventoryContainsItems;

@BlockProbeTest
public class BasicTests {
    @Test
    public void testPipeTransfersItems(WorldInteractionContext context) {
        // Arrange
        context
                .setBlock(Position.Zero, "Furniture_Crude_Chest_Small")
                .setBlock(Direction.Right, "Pipe_Item")
                .setBlock(Direction.Right, "Furniture_Crude_Chest_Small");

        // Act
        context.addItemToBlockInventory(Position.Zero, new ItemStack("Ore_Adamantite", 10));
        context.waitUntil(new InventoryEmptyWaitCondition(Position.Zero));

        // Assert
        assertInventoryContainsItems(new Position(2, 0, 0), false, new ItemStack("Ore_Adamantite", 10));
    }
}
