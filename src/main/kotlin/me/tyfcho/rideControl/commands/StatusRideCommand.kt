package me.tyfcho.rideControl.commands

import cloud.commandframework.Command
import cloud.commandframework.arguments.standard.StringArgument
import cloud.commandframework.kotlin.extension.command
import me.tyfcho.rideControl.util.CommandUtils
import org.bukkit.command.CommandSender
import org.yaml.snakeyaml.Yaml
import java.io.File

/**
 * Command to change the status of a ride.
 */
object StatusRideCommand {
    private val yaml = Yaml()

    /**
     * Creates the command for changing the status of a ride.
     *
     * @return The command for changing the status of a ride.
     */
    fun createCommand(): Command<CommandSender> {
        return command("ridecontrol status ride") {
            argument(StringArgument.of("attractionName"))
            argument(StringArgument.of("status"))
            handler { context ->
                val attractionName = context.get<String>("attractionName")
                val status = context.get<String>("status")

                // Validate the status
                if (!CommandUtils.isValidStatus(status)) {
                    context.sender.sendMessage("Invalid status. Valid statuses are: open, closed, maintenance.")
                    return@handler
                }

                // Load the status file
                val statusFile = File("~/rides/status.yml")
                val statusData = if (statusFile.exists()) {
                    yaml.load<Map<String, String>>(statusFile.readText())
                } else {
                    mutableMapOf()
                }

                // Update the status
                statusData[attractionName] = status
                statusFile.writeText(yaml.dump(statusData))

                // Notify the sender
                context.sender.sendMessage("Status of $attractionName set to $status.")
            }
        }
    }
}