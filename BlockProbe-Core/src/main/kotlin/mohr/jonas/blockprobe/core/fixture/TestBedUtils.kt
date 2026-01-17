package mohr.jonas.blockprobe.core.fixture

import mohr.jonas.blockprobe.core.data.BoundingBox
import mohr.jonas.blockprobe.core.data.Position
import mohr.jonas.blockprobe.core.exceptions.PositionOutsideOfTestBedException

object TestBedUtils {
    fun unproject(bed: TestBed, position: Position): Position {
        val unprojectedPosition = bed.centerBlockPlacementPosition.add(position)
        if (!isInBoundingBox(bed.editableBoundingBox, unprojectedPosition))
            throw PositionOutsideOfTestBedException(bed, unprojectedPosition)
        return unprojectedPosition
    }

    private fun isInBoundingBox(boundingBox: BoundingBox, position: Position): Boolean {
        val origin = boundingBox.origin
        val x = position.x
        val y = position.y
        val z = position.z
        if (x < origin.x || y < origin.y || z < origin.z)
            return false
        if (x > origin.x + boundingBox.width || y > origin.y + boundingBox.height || z > origin.z + boundingBox.depth)
            return false
        return true
    }
}