package mohr.jonas.blockprobe.core.world

import mohr.jonas.blockprobe.core.fixture.TestBed

fun interface TestBedFetcher {
    fun getInteractionContextForTest(testIdentifier: String): TestBed
}