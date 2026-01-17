package mohr.jonas.blockprobe.junit6

import org.junit.jupiter.engine.JupiterTestEngine
import org.junit.platform.engine.*
import org.junit.platform.engine.TestEngine
import org.junit.platform.engine.support.store.Namespace

class TestEngine : TestEngine {
    private val storeEntries = mutableMapOf<Any, Any>()
    private val backingEngine = JupiterTestEngine()

    override fun getId() = "blockprobe-jupiter"

    override fun discover(discoveryRequest: EngineDiscoveryRequest, uniqueId: UniqueId): TestDescriptor =
        backingEngine.discover(discoveryRequest, uniqueId)

    override fun execute(request: ExecutionRequest) {
        storeEntries.forEach { (key: Any, value: Any) -> request.store.put(Namespace.GLOBAL, key, value) }
        backingEngine.execute(request)
    }

    fun addStoreEntry(key: Any, value: Any): mohr.jonas.blockprobe.junit6.TestEngine {
        storeEntries[key] = value
        return this
    }
}