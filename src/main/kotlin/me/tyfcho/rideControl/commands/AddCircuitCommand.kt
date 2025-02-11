package me.tyfcho.rideControl.commands

import cloud.commandframework.Command
import cloud.commandframework.arguments.standard.StringArgument
import cloud.commandframework.kotlin.extension.command
import org.bukkit.command.CommandSender

/**
 * Command to add circuits to a ride.
 */
object AddCircuitCommand {
    /**
     * Creates the command for adding circuits to a ride.
     *
     * @return The command for adding circuits to a ride.
     */
    fun createCommand(): Command<CommandSender> {
        return command("ridecontrol create circuit") {
            argument(StringArgument.of("attractionName"))
            argument(StringArgument.of("amountToAdd"))
            handler { context ->
                val attractionName = context.get<String>("attractionName")
                val amountToAdd = context.get<String>("amountToAdd").toInt()
                // Logic for adding a circuit
            }
        }
    }
}