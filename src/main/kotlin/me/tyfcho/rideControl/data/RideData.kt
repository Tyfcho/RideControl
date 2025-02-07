package me.tyfcho.rideControl.data

data class RideData(
    val name: String,
    val circuits: MutableList<CircuitData>
)
