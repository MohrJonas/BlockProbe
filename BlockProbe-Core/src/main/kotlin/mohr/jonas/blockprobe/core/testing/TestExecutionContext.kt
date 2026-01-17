package mohr.jonas.blockprobe.core.testing

import com.hypixel.hytale.server.core.entity.entities.Player
import com.hypixel.hytale.server.core.universe.world.World
import mohr.jonas.blockprobe.core.data.BoundingBox
import mohr.jonas.blockprobe.core.data.Position
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
                TestBedConstructor.setBedBorder(
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
                getOriginForTestBed(testBeds.size, configuration).add(1, 0, 1),
                configuration.testBedWidth,
                configuration.testBedHeight,
                configuration.testBedDepth
            ),
            BoundingBox(
                getOriginForTestBed(testBeds.size, configuration),
                configuration.testBedWidth + 1,
                configuration.testBedHeight,
                configuration.testBedDepth + 1
            )
        )
        testBeds[testIdentifier] = newTestBed
        TestBedConstructor.constructBed(newTestBed)
        return newTestBed
    }

    private fun getOriginForTestBed(testBedIndex: Int, configuration: TestExecutionConfiguration): Position {
        val row = floor(testBedIndex.toDouble() / configuration.testBedsPerRow).toInt()
        val column = testBedIndex % configuration.testBedsPerRow
        return Position(
            row * (configuration.testBedWidth + configuration.testBedSpacing + 2),
            configuration.testPlaneHeight,
            column * (configuration.testBedDepth + configuration.testBedSpacing + 2)
        )
    }

    private fun getBlockIdForTestStatus(status: TestStatus): String {
        return when (status) {
            TestStatus.Skipped -> "Cloth_Block_Wool_Yellow"
            TestStatus.Succeeded -> "Cloth_Block_Wool_Green"
            TestStatus.Failed -> "Cloth_Block_Wool_Red"
        }
    }
}