package mohr.jonas.blockprobe.core

import com.hypixel.hytale.server.core.inventory.container.ItemContainer
import com.hypixel.hytale.server.core.universe.world.World
import com.hypixel.hytale.server.core.universe.world.meta.state.ItemContainerState
import mohr.jonas.blockprobe.core.data.Position

object BlockUtils {
    fun getBlockInventory(world: World, position: Position): ItemContainer {
        return (world
            .getState(
                position.x,
                position.y,
                position.z,
                false
            ) as ItemContainerState)
        .itemContainer
    }
}