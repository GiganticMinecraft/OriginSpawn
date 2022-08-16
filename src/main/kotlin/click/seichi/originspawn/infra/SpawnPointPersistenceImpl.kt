package click.seichi.originspawn.infra

import click.seichi.originspawn.*
import click.seichi.originspawn.domain.SpawnPointPersistence
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.plugin.java.JavaPlugin

private enum class ConfigItems(val key: String) {
    WORLD_NAME("world-name"), X("loc-x"), Y("loc-y"), Z("loc-z"), YAW("loc-yaw"), PITCH("loc-pitch")
}

class SpawnPointPersistenceImpl(private val plugin: JavaPlugin): SpawnPointPersistence {
    private lateinit var spawnPoint: SpawnPoint

    override fun get() = spawnPoint

    private fun areAllConfigItemsSet(config: FileConfiguration) {
        ConfigItems.values().filterNot { config.isSet(it.key) }.map { it.key }.let {
            if (it.isNotEmpty()) throw IllegalStateException(
                "There are no values below in config.yml: ${it.joinToString(", ")}"
            )
        }
    }

    override fun load() {
        plugin.saveDefaultConfig()
        plugin.reloadConfig()

        val config = plugin.config
        areAllConfigItemsSet(config)

        val worldName = config.getString(ConfigItems.WORLD_NAME.key, "world")
        val x = config.getDouble(ConfigItems.X.key)
        val y = config.getDouble(ConfigItems.Y.key, 64.0)
        val z = config.getDouble(ConfigItems.Z.key)
        val yaw = config.getDouble(ConfigItems.YAW.key).toFloat()
        val pitch = config.getDouble(ConfigItems.PITCH.key).toFloat()

        spawnPoint = SpawnPoint(worldName, LocX(x), LocY(y), LocZ(z), Yaw(yaw), Pitch(pitch))
    }

    override fun save(spawnPoint: SpawnPoint) {
        mapOf(
            ConfigItems.WORLD_NAME to spawnPoint.worldName,
            ConfigItems.X to spawnPoint.x.value,
            ConfigItems.Y to spawnPoint.y.value,
            ConfigItems.Z to spawnPoint.z.value,
            ConfigItems.YAW to spawnPoint.yaw.value,
            ConfigItems.PITCH to spawnPoint.pitch.value
        ).forEach { (item, value)  ->
            plugin.config.set(item.key, value)
        }

        plugin.saveConfig()
    }
}