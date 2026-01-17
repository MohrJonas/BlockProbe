package mohr.jonas.blockprobe.core.testing

interface TestDiscoverer {
    fun discoverTests(): Array<Class<*>>
}