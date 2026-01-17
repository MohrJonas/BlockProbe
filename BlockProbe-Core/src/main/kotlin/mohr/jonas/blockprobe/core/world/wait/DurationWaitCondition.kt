package mohr.jonas.blockprobe.core.world.wait

import java.time.Duration

class DurationWaitCondition(val duration: Duration) : WaitCondition {

    private val completeMillis = System.currentTimeMillis() + duration.toMillis()

    override fun isComplete() = System.currentTimeMillis() >= completeMillis
}