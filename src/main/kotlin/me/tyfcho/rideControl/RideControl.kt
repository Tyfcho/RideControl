package tyfcho.rideControl

import cloud.commandframework.CommandManager
import cloud.commandframework.bukkit.BukkitCommandManager
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin
import tyfcho.rideControl.commands.*

class RideControl : JavaPlugin() {
    private lateinit var commandManager: CommandManager<CommandSender>

    override fun onEnable() {
        // Initialize command manager
        commandManager = BukkitCommandManager(this)

        // Register commands
        commandManager.command(CreateRideCommand.createCommand())
        commandManager.command(AddCircuitCommand.createCommand())
        commandManager.command(RenameCircuitCommand.createCommand())
        commandManager.command(PanelRideCommand.createCommand())
        commandManager.command(StatusRideCommand.createCommand())
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}