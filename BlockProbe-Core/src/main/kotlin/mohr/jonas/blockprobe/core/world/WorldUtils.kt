package mohr.jonas.blockprobe.core.world

import com.hypixel.hytale.math.vector.Vector3i
import com.hypixel.hytale.server.core.universe.world.World

object WorldUtils {
    fun fillPlane(world: World, origin: Vector3i, width: Int, depth: Int, blockId: String) {
        for (x in 0 until width) {
            for (z in 0 until depth) {
                world.setBlock(origin.x + x, origin.y, origin.z + z, blockId)
            }
        }
    }
}