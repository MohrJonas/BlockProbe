package mohr.jonas.blockprobe.core.fixture

import com.hypixel.hytale.math.vector.Vector3i
import com.hypixel.hytale.server.core.universe.world.World
import mohr.jonas.blockprobe.core.data.BoundingBox

data class TestBed(val world: World, val boundingBox: BoundingBox) {
    val statusLightPosition: Vector3i = boundingBox.origin.clone().add(0, 1, 0)

    val centerBlockPlacementPosition: Vector3i = boundingBox.origin.clone().add(
        (boundingBox.width - 1) / 2,
        1,
        (boundingBox.depth - 1) / 2,
    )
}