package mohr.jonas.blockprobe.core.world

import com.hypixel.hytale.math.vector.Vector3i
import mohr.jonas.blockprobe.core.data.Direction
import mohr.jonas.blockprobe.core.data.Direction.Companion.getVectorForDirection

class PositionedWorldInteractionContext(private val context: WorldInteractionContext, private val position: Vector3i) {

    fun setBlock(direction: Direction, blockId: String): PositionedWorldInteractionContext {
        return context.setBlock(position.add(direction.getVectorForDirection()), blockId)
    }
}