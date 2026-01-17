package mohr.jonas.blockprobe.samplemod

import com.hypixel.hytale.server.core.command.system.CommandContext
import com.hypixel.hytale.server.core.command.system.CommandManager
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase
import com.hypixel.hytale.server.core.entity.entities.Player
import com.hypixel.hytale.server.core.plugin.JavaPlugin
import com.hypixel.hytale.server.core.plugin.JavaPluginInit
import mohr.jonas.blockprobe.core.testing.TestExecutionConfiguration
import mohr.jonas.blockprobe.core.testing.TestExecutionContext
import mohr.jonas.blockprobe.junit6.JUnit6TestRunner
import mohr.jonas.blockprobe.junit6.PackageTestDiscoverer

class Main(init: JavaPluginInit) : JavaPlugin(init) {
    override fun setup() {
        var testExecutionContext: TestExecutionContext? = null
        CommandManager.get().register(object : CommandBase("run-tests", "run-tests") {
            override fun executeSync(commandContext: CommandContext) {
                val runner = JUnit6TestRunner()
                runner.registerAutoDiscoveredTests(PackageTestDiscoverer("mohr.jonas.blockprobe.samplemod"))
                testExecutionContext = TestExecutionContext(runner, TestExecutionConfiguration())
                testExecutionContext.execute(commandContext.sender() as Player)
            }
        })
        CommandManager.get().register(object : CommandBase("teardown-tests", "teardown-tests") {
            override fun executeSync(commandContext: CommandContext) {
                testExecutionContext?.teardown()
            }
        })
    }
}