package mohr.jonas.blockprobe.core.fixture

import mohr.jonas.blockprobe.core.world.WorldUtils

object TestBedConstructor {
    fun constructBed(
        bed: TestBed,
        blockId: String = "Cloth_Block_Wool_White",
        borderBlockId: String = "Cloth_Block_Wool_Black"
    ) {
        clearArea(bed)
        WorldUtils.fillPlane(
            bed.world,
            bed.editableBoundingBox.origin,
            bed.editableBoundingBox.width,
            bed.editableBoundingBox.depth,
            blockId
        )
        setBedBorder(bed, borderBlockId)
    }

    fun teardownBed(bed: TestBed) = clearArea(bed)

    fun setBedBorder(bed: TestBed, blockId: String) {
        val world = bed.world
        WorldUtils.fillLine(world, bed.completeBoundingBox.origin, bed.completeBoundingBox.width + 1, blockId) {
            it.addX(1)
        }
        WorldUtils.fillLine(world, bed.completeBoundingBox.origin, bed.completeBoundingBox.depth + 1, blockId) {
            it.addZ(1)
        }
        WorldUtils.fillLine(
            world, bed.completeBoundingBox.origin.add(
                bed.completeBoundingBox.width, 0, bed.completeBoundingBox.depth
            ), bed.completeBoundingBox.width, blockId
        ) {
            it.addX(-1)
        }
        WorldUtils.fillLine(
            world, bed.completeBoundingBox.origin.add(
                bed.completeBoundingBox.width, 0, bed.completeBoundingBox.depth
            ), bed.completeBoundingBox.depth, blockId
        ) {
            it.addZ(-1)
        }
    }


    private fun clearArea(bed: TestBed) {
        for (y in 0 until bed.completeBoundingBox.height) {
            WorldUtils.fillPlane(
                bed.world,
                bed.completeBoundingBox.origin.addY(y),
                bed.completeBoundingBox.width + 1,
                bed.completeBoundingBox.depth + 1,
                "Empty"
            )
        }
    }
}