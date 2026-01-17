package mohr.jonas.blockprobe.core.data

import com.hypixel.hytale.math.vector.Vector3i

data class Position(val x: Int, val y: Int, val z: Int) {
    fun add(other: Position) = Position(x + other.x, y + other.y, z + other.z)

    fun add(x: Int, y: Int, z: Int) = Position(this.x + x, this.y + y, this.z + z)

    fun addX(x: Int) = Position(this.x + x, y, z)

    fun addY(y: Int) = Position(x, this.y + y, z)

    fun addZ(z: Int) = Position(x, y, this.z + z)

    fun asVector3i() = Vector3i(x, y, z)

    override fun toString() = "($x, $y, $z)"

    companion object {
        @JvmField
        val Zero = Position(0, 0, 0)
    }
}
