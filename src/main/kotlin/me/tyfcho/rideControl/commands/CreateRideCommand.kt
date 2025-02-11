package me.tyfcho.rideControl.commands

import cloud.commandframework.Command
import cloud.commandframework.arguments.standard.StringArgument
import cloud.commandframework.kotlin.extension.command
import org.bukkit.command.CommandSender

/**
 * Command to create a new ride.
 */
object CreateRideCommand {
    /**
     * Creates the command for creating a new ride.
     *
     * @return The command for creating a new ride.
     */
    fun createCommand(): Command<CommandSender> {
        return command("ridecontrol create ride") {
            argument(StringArgument.of("attractionName"))
            argument(StringArgument.of("amountOfCircuits"))
            handler { context ->
                val attractionName = context.get<String>("attractionName")
                val amountOfCircuits = context.get<String>("amountOfCircuits").toInt()
                // Logic for creating a ride
            }
        }
    }
}