package mohr.jonas.blockprobe.junit6

import mohr.jonas.blockprobe.core.testing.TestDiscoverer
import org.reflections.Reflections

class PackageTestDiscoverer(private val packageName: String) : TestDiscoverer {
    override fun discoverTests(): Array<Class<*>> {
        val reflections = Reflections(packageName)
        return reflections.getTypesAnnotatedWith(BlockProbeTest::class.java).toTypedArray()
    }
}