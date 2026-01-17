package mohr.jonas.blockprobe.core.world

import com.hypixel.hytale.server.core.universe.world.World
import mohr.jonas.blockprobe.core.data.Position

object WorldUtils {
    fun fillPlane(world: World, origin: Position, width: Int, depth: Int, blockId: String) {
        for (x in 0 until width) {
            for (z in 0 until depth) {
                world.setBlock(origin.x + x, origin.y, origin.z + z, blockId)
            }
        }
    }

    fun fillLine(world: World, origin: Position, length: Int, blockId: String, positionProvider: (Position) -> Position) {
        var currentPosition = origin
        repeat(length) {
            world.setBlock(currentPosition.x, currentPosition.y, currentPosition.z, blockId)
            currentPosition = positionProvider(currentPosition)
        }
    }
}