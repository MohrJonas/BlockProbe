package mohr.jonas.blockprobe.junit6

import org.junit.jupiter.api.extension.ExtendWith

@Target(AnnotationTarget.CLASS)
@ExtendWith(WorldInteractionContextResolver::class)
@ExtendWith(CallbackNotifyingTestWatcher::class)
@ExtendWith(AssertionContextCreator::class)
annotation class BlockProbeTest