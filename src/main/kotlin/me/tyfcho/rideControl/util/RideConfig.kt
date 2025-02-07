package me.tyfcho.rideControl.util

import me.tyfcho.rideControl.data.CircuitData
import me.tyfcho.rideControl.data.RideData
import org.yaml.snakeyaml.Yaml
import java.io.File

object RideConfig {
    private val yaml = Yaml()

    fun loadRide(file: File): RideData {
        val data = yaml.load<Map<String, Any>>(file.readText())
        val name = data["name"] as String
        val circuits = (data["circuits"] as List<Map<String, Any>>).map {
            CircuitData(
                it["id"] as Int,
                it["name"] as String,
                it["inputs"] as List<String>,
                it["outputs"] as List<String>,
                it["logic"] as String
            )
        }.toMutableList()
        return RideData(name, circuits)
    }

    fun saveRide(file: File, ride: RideData) {
        val data = mapOf(
            "name" to ride.name,
            "circuits" to ride.circuits.map {
                mapOf(
                    "id" to it.id,
                    "name" to it.name,
                    "inputs" to it.inputs,
                    "outputs" to it.outputs,
                    "logic" to it.logic
                )
            }
        )
        file.writeText(yaml.dump(data))
    }
}