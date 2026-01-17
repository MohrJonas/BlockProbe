package mohr.jonas.blockprobe.core.data

import com.hypixel.hytale.math.vector.Vector3i

enum class Direction {
    Above,
    Below,
    LeftOf,
    RightOf,
    Behind,
    InFrontOf;

    companion object {
        fun Direction.getVectorForDirection(): Vector3i = when (this) {
            Above -> Vector3i.UP
            Below -> Vector3i.DOWN
            LeftOf -> Vector3i.WEST
            RightOf -> Vector3i.EAST
            Behind -> Vector3i.NORTH
            InFrontOf -> Vector3i.SOUTH
        }
    }
}