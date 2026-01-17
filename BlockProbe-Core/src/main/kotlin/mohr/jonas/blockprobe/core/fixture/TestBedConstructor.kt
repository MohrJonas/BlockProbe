package mohr.jonas.blockprobe.core.fixture

import mohr.jonas.blockprobe.core.world.WorldUtils

object TestBedConstructor {
    fun constructBed(bed: TestBed, blockId: String = "Cloth_Block_Wool_White") {
        clearArea(bed)
        WorldUtils.fillPlane(
            bed.world,
            bed.boundingBox.origin,
            bed.boundingBox.width,
            bed.boundingBox.depth,
            blockId
        )
    }

    fun teardownBed(bed: TestBed) = clearArea(bed)

    fun setBedStatusBlock(bed: TestBed, blockId: String) {
        val lightPosition = bed.statusLightPosition;
        val x = lightPosition.x
        val y = lightPosition.y
        val z = lightPosition.z
        bed.world.setBlock(x, y, z, blockId)
    }

    private fun clearArea(bed: TestBed) {
        for (y in 0 until bed.boundingBox.height) {
            WorldUtils.fillPlane(
                bed.world,
                bed.boundingBox.origin
                    .clone()
                    .add(0, y, 0),
                bed.boundingBox.width,
                bed.boundingBox.depth,
                "Empty"
            )
        }
    }
}