package mohr.jonas.blockprobe.core.fixture

import com.hypixel.hytale.server.core.universe.world.World
import mohr.jonas.blockprobe.core.data.BoundingBox
import mohr.jonas.blockprobe.core.data.Position

data class TestBed(val world: World, val editableBoundingBox: BoundingBox, val completeBoundingBox: BoundingBox) {
    val centerBlockPlacementPosition: Position = editableBoundingBox.origin.add(
        (editableBoundingBox.width - 1) / 2,
        1,
        (editableBoundingBox.depth - 1) / 2,
    )
}