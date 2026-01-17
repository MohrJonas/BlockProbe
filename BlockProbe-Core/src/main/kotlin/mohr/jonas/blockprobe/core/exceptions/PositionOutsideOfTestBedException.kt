package mohr.jonas.blockprobe.core.exceptions

import mohr.jonas.blockprobe.core.data.Position
import mohr.jonas.blockprobe.core.fixture.TestBed

class PositionOutsideOfTestBedException(testBed: TestBed, position: Position) : Exception(
    "Position $position is out of the bed's bounds, ranging from ${testBed.editableBoundingBox.origin} to ${getTestBedUpperPosition(testBed)})"
) {
    companion object {

        private fun getTestBedUpperPosition(testBed: TestBed): Position {
            return testBed.editableBoundingBox.origin.add(
                testBed.editableBoundingBox.width, testBed.editableBoundingBox.height, testBed.editableBoundingBox.depth
            )
        }
    }
}