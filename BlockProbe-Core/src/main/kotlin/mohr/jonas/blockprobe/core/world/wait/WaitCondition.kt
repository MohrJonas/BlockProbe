package mohr.jonas.blockprobe.core.world.wait

fun interface WaitCondition {
    fun isComplete(): Boolean
}