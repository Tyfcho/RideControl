package me.tyfcho.rideControl.data

data class CircuitData(
    val id: Int,
    val name: String,
    val inputs: List<String>,
    val outputs: List<String>,
    val logic: String
)
