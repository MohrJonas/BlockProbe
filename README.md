<p align="center">
  <img src="assets/logo.png" width="512" height="512" />
</p>

# BlockProbe - Tests that run inside your Hytale world

BlockProbe lets you write familiar unit tests while interacting with real blocks, entities, inventories, and game logic — no mocks, no abstractions, just true in‑world behavior.

### Disclaimer
Logo created with AI. **Absolutely zero** code was written by or with the help of AI. I'm a crappy artist, sorry.

### Preamble

Traditional unit tests force you to mock or abstract away the world. That works for isolated logic, but it can never validate the full behavior.
With BlockProbe, your tests run in‑game, inside an actual Hytale world. You can place blocks, interact with inventories, spawn entities, wait for conditions, and assert the outcome — all using well-known testing syntax.
This enables true end‑to‑end testing of your mod’s gameplay logic.

### Example

Imagine you’re building a pipe mod to transfer items between chests.
Instead of mocking inventories and simulating ticks, you can:

- Place two chests and a pipe in the world
- Insert items into the first chest
- Wait for the chest to clear of items
- Assert that the second chest contains the transferred items

All inside a regular test method:

```kotlin
@Test
@Timeout(10)
fun testPipeTransfersItems(context: WorldInteractionContext) {
    // Arrange
    context
        .setBlock(Position.Zero, "Furniture_Crude_Chest_Small")
        .setBlock(Direction.Right, "Pipe_Item")
        .setBlock(Direction.Right, "Furniture_Crude_Chest_Small")

    // Act
    context.addItemToBlockInventory(Position.Zero, ItemStack("Ore_Adamantite", 10))
    context.waitUntil(InventoryEmptyWaitCondition(Position.Zero))

    // Assert
    assertInventoryContainsItems(Position(2, 0, 0), false, ItemStack("Ore_Adamantite", 10))
}
```

Or, using Java if you prefer:

```java
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
```

### Why BlockProbe

- Realistic testing — no mocks, no stubs, no fake worlds
- End‑to‑end validation of your mod’s actual behavior
- JUnit used under the hood — use the tools you already know
- Deterministic scenarios — set up worlds exactly as needed
- Faster iteration — catch gameplay bugs before players do

### Contributing

Contributions, ideas, and feedback are welcome. Feel free to open issues or submit PRs.