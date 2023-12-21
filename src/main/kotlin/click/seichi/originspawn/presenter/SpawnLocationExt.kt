package click.seichi.originspawn.presenter

import click.seichi.originspawn.domain.*
import org.bukkit.Bukkit
import org.bukkit.Location

fun Location.toSpawnPoint() =
    world?.let { SpawnPoint(it.name, LocX(x), LocY(y), LocZ(z), Yaw(yaw), Pitch(pitch)) }

fun SpawnPoint.toBukkitLocation() = Bukkit.getWorld(worldName)?.let { world ->
    Location(world, x.value, y.value, z.value, yaw.value, pitch.value)
}

fun SpawnPoint.format() = "${worldName}: ${x.value} ${y.value} ${z.value} (${yaw.value}, ${pitch.value})"