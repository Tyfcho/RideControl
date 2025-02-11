package me.tyfcho.rideControl.commands

import cloud.commandframework.Command
import cloud.commandframework.arguments.standard.StringArgument
import cloud.commandframework.kotlin.extension.command
import org.bukkit.command.CommandSender

/**
 * Command to show the panel for a ride.
 */
object PanelRideCommand {
    /**
     * Creates the command for showing the panel for a ride.
     *
     * @return The command for showing the panel for a ride.
     */
    fun createCommand(): Command<CommandSender> {
        return command("ridecontrol panel ride") {
            argument(StringArgument.of("attractionName"))
            handler { context ->
                val attractionName = context.get<String>("attractionName")
                // Logic for showing the ride panel
            }
        }
    }
}