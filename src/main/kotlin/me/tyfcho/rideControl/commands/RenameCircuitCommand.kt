package me.tyfcho.rideControl.commands

import cloud.commandframework.Command
import cloud.commandframework.arguments.standard.StringArgument
import cloud.commandframework.kotlin.extension.command
import org.bukkit.command.CommandSender

/**
 * Command to rename a circuit in a ride.
 */
object RenameCircuitCommand {
    /**
     * Creates the command for renaming a circuit in a ride.
     *
     * @return The command for renaming a circuit in a ride.
     */
    fun createCommand(): Command<CommandSender> {
        return command("ridecontrol edit circuit rename") {
            argument(StringArgument.of("attractionName"))
            argument(StringArgument.of("newCircuitName"))
            handler { context ->
                val attractionName = context.get<String>("attractionName")
                val newCircuitName = context.get<String>("newCircuitName")
                // Logic for renaming a circuit
            }
        }
    }
}