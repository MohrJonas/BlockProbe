package mohr.jonas.blockprobe.core.data

enum class Direction {
    Above,
    Below,
    Left,
    Right,
    Behind,
    InFront;

    companion object {
        fun Direction.getVectorForDirection() = when (this) {
            Above -> Position(0, 1, 0)
            Below -> Position(0, -1, 0)
            Left -> Position(0, 0, -1)
            Right -> Position(0, 0, 1)
            Behind -> Position(1, 0, 0)
            InFront -> Position(-1, 0, 0)
        }
    }
}