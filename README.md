<center>
    <img alt="Logo" src="assets/logo.png" width="512" height="512" style="margin: 0; padding: 0"/>
    <p style="font-size: 50px; font-weight: 800; margin: 0; padding: 0">BlockProbe - Tests that run inside your Hytale world</p>
    <p style="font-size: xx-large; margin: 0; padding: 0; padding-bottom: 20px">Unit‑style tests executed directly inside a live Hytale world.</p>
</center>

BlockProbe lets you write familiar unit tests while interacting with real blocks, entities, inventories, and game logic — no mocks, no abstractions, just true in‑world behavior.

### Disclaimer
Logo created with AI. **Absolutely zero** code was written by or with the help of AI. I'm a crappy artist, sorry.

### About

Traditional unit tests force you to mock or abstract away the world. That works for isolated logic, but it can never validate the full behavior.

With BlockProbe, your tests run in‑game, inside an actual Hytale world. You can place blocks, interact with inventories, spawn entities, wait for conditions, and assert the real outcomes — all using well-known testing syntax.

This enables true end‑to‑end testing of your mod’s gameplay logic.

### Example

Imagine you’re building a pipe plugin to transfer items between chests.
Instead of mocking inventories and simulating ticks, you can:

- Place two chests and a pipe in the world
- Insert items into the first chest
- Wait for the chest to clear of items
- Assert that the second chest contains the transferred items

All inside a normal unit-test method:

```java
@Test
public void testPipeTransfersItems() {
// Arrange
world.placeBlock(posA, CHEST);
world.placeBlock(posB, PIPE);
world.placeBlock(posC, CHEST);

// Act
world.getInventory(posA).add(itemStack);
world.waitTicks(40);

// Assert
assertTrue(world.getInventory(posA).isEmpty());
assertEquals(itemStack, world.getInventory(posC).getFirst());
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