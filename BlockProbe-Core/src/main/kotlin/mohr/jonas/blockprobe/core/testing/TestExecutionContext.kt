package mohr.jonas.blockprobe.core.testing

import com.hypixel.hytale.math.vector.Vector3i
import com.hypixel.hytale.server.core.entity.entities.Player
import com.hypixel.hytale.server.core.universe.world.World
import mohr.jonas.blockprobe.core.data.BoundingBox
import mohr.jonas.blockprobe.core.data.TestStatus
import mohr.jonas.blockprobe.core.fixture.TestBed
import mohr.jonas.blockprobe.core.fixture.TestBedConstructor
import kotlin.math.floor

class TestExecutionContext(val runner: TestRunner, val configuration: TestExecutionConfiguration) {

    private val testBeds = mutableMapOf<String, TestBed>()

    fun execute(executingPlayer: Player) {
        runner.runTests(
            { testIdentifier, testResult ->
                println("$testIdentifier: $testResult")
                TestBedConstructor.setBedStatusBlock(
                    getTestBedForTest(executingPlayer.world!!, testIdentifier),
                    getBlockIdForTestStatus(testResult.status)
                )
            },
            { getTestBedForTest(executingPlayer.world!!, it) }
        )
    }

    fun teardown() = testBeds.values.forEach {
        TestBedConstructor.teardownBed(it)
        runner.teardownTests()
    }

    private fun getTestBedForTest(world: World, testIdentifier: String): TestBed {
        if (testBeds.containsKey(testIdentifier))
            return testBeds[testIdentifier]!!
        val newTestBed = TestBed(
            world, BoundingBox(
                getOriginForTestBed(testBeds.size, configuration),
                configuration.testBedWidth,
                configuration.testBedHeight,
                configuration.testBedDepth
            )
        )
        testBeds[testIdentifier] = newTestBed
        TestBedConstructor.constructBed(newTestBed)
        return newTestBed
    }

    private fun getOriginForTestBed(testBedIndex: Int, configuration: TestExecutionConfiguration): Vector3i {
        val row = floor(testBedIndex.toDouble() / configuration.testBedsPerRow).toInt()
        val column = testBedIndex % configuration.testBedsPerRow
        return Vector3i(
            row * (configuration.testBedWidth + configuration.testBedSpacing),
            configuration.testPlaneHeight,
            column * (configuration.testBedDepth + configuration.testBedSpacing)
        )
    }

    private fun getBlockIdForTestStatus(status: TestStatus): String {
        return when (status) {
            TestStatus.Skipped -> "Rock_Crystal_Purple_Medium"
            TestStatus.Succeeded -> "Rock_Crystal_Green_Medium"
            TestStatus.Failed -> "Rock_Crystal_Red_Medium"
        }
    }
}