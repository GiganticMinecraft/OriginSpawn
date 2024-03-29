package click.seichi.originspawn.domain

data class LocX(val value: Double)

data class LocY(val value: Double) {
    init {
        if (value < 0.0) throw IllegalStateException("Location Y must be 0.0 or more.")
    }
}

data class LocZ(val value: Double)

data class Yaw(val value: Float)

data class Pitch(val value: Float)

data class SpawnPoint(
    val worldName: String,
    val x: LocX,
    val y: LocY,
    val z: LocZ,
    val yaw: Yaw,
    val pitch: Pitch
)
