package me.tyfcho.rideControl.util

import org.yaml.snakeyaml.Yaml
import java.io.File

/**
 * Data class representing a circuit.
 */
data class CircuitData(
    val id: Int,
    val name: String,
    val inputs: List<String>,
    val outputs: List<String>,
    val logic: String
)

/**
 * Data class representing a ride.
 */
data class RideData(
    val name: String,
    val circuits: List<CircuitData>
)

/**
 * Utility object for loading and saving ride configurations.
 */
object RideConfig {
    private val yaml = Yaml()

    /**
     * Loads a ride configuration from a file.
     *
     * @param attractionName The name of the attraction.
     * @return The loaded ride data.
     */
    fun loadRide(attractionName: String): RideData {
        val file = File("~/rides/$attractionName/$attractionName.yml")
        val data = yaml.load<Map<String, Any>>(file.readText())
        val name = data["name"] as String
        val circuits = (data["circuits"] as List<Map<String, Any>>).map {
            CircuitData(
                id = it["id"] as Int,
                name = it["name"] as String,
                inputs = (it["inputs"] as String).split(", "),
                outputs = (it["outputs"] as String).split(", "),
                logic = it["logic"] as String
            )
        }
        return RideData(name, circuits)
    }

    /**
     * Saves a ride configuration to a file.
     *
     * @param attractionName The name of the attraction.
     * @param ride The ride data to save.
     */
    fun saveRide(attractionName: String, ride: RideData) {
        val file = File("~/rides/$attractionName/$attractionName.yml")
        val data = mapOf(
            "name" to ride.name,
            "circuits" to ride.circuits.map {
                mapOf(
                    "id" to it.id,
                    "name" to it.name,
                    "inputs" to it.inputs.joinToString(", "),
                    "outputs" to it.outputs.joinToString(", "),
                    "logic" to it.logic
                )
            }
        )
        file.writeText(yaml.dump(data))
    }

    /**
     * Loads a circuit configuration from a file.
     *
     * @param attractionName The name of the attraction.
     * @param circuitName The name of the circuit.
     * @return The loaded circuit data.
     */
    fun loadCircuit(attractionName: String, circuitName: String): CircuitData {
        val file = File("~/rides/$attractionName/$circuitName/$circuitName.yml")
        val data = yaml.load<Map<String, Any>>(file.readText())
        return CircuitData(
            id = data["id"] as Int,
            name = data["name"] as String,
            inputs = (data["inputs"] as String).split(", "),
            outputs = (data["outputs"] as String).split(", "),
            logic = data["logic"] as String
        )
    }

    /**
     * Saves a circuit configuration to a file.
     *
     * @param attractionName The name of the attraction.
     * @param circuit The circuit data to save.
     */
    fun saveCircuit(attractionName: String, circuit: CircuitData) {
        val file = File("~/rides/$attractionName/${circuit.name}/${circuit.name}.yml")
        val data = mapOf(
            "id" to circuit.id,
            "name" to circuit.name,
            "inputs" to circuit.inputs.joinToString(", "),
            "outputs" to circuit.outputs.joinToString(", "),
            "logic" to circuit.logic
        )
        file.writeText(yaml.dump(data))
    }
}