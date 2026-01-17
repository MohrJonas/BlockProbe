package mohr.jonas.blockprobe.core.exceptions

import com.hypixel.hytale.math.vector.Vector3i
import mohr.jonas.blockprobe.core.fixture.TestBed

class PositionOutsideOfTestBedException(testBed: TestBed, position: Vector3i) : Exception(
    "Position ${stringifyVector(position)} is out of the bed's bounds, ranging from ${stringifyVector(testBed.boundingBox.origin)} to ${
        stringifyVector(getTestBedUpperPosition(testBed))
    })"
) {
    companion object {
        private fun stringifyVector(position: Vector3i): String = "(${position.x}, ${position.y}, ${position.z})"

        private fun getTestBedUpperPosition(testBed: TestBed): Vector3i {
            return testBed.boundingBox.origin.clone().add(
                testBed.boundingBox.width, testBed.boundingBox.height, testBed.boundingBox.depth
            )
        }
    }
}