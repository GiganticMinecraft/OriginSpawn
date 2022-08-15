package click.seichi.originspawn

data class LocX(val value: Double)

data class LocY(val value: Double) {
    init {
        if (value < 0.0) throw IllegalStateException("")
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
