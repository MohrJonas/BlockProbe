package mohr.jonas.blockprobe.core.assertions

import mohr.jonas.blockprobe.core.fixture.TestBed

object AssertionContext {
    val testBed: ThreadLocal<TestBed> = ThreadLocal()
}