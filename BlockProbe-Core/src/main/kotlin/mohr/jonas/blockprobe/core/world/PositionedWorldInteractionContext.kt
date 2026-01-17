package mohr.jonas.blockprobe.core.world

import mohr.jonas.blockprobe.core.data.Direction
import mohr.jonas.blockprobe.core.data.Direction.Companion.getVectorForDirection
import mohr.jonas.blockprobe.core.data.Position

class PositionedWorldInteractionContext(private val context: WorldInteractionContext, private val position: Position) {

    fun setBlock(direction: Direction, blockId: String): PositionedWorldInteractionContext {
        return context.setBlock(position.add(direction.getVectorForDirection()), blockId)
    }
}