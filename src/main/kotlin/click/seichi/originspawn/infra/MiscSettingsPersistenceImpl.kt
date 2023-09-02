package click.seichi.originspawn.infra

import click.seichi.originspawn.domain.MiscSettings
import click.seichi.originspawn.domain.MiscSettingsPersistence
import org.bukkit.plugin.java.JavaPlugin

private const val ALWAYS_FORCE_PLAYERS_SPAWN = "always-force-players-spawn"

class MiscSettingsPersistenceImpl(private val plugin: JavaPlugin): MiscSettingsPersistence {
    private lateinit var miscSettings: MiscSettings

    override fun get(): MiscSettings {
        if (!::miscSettings.isInitialized) {
            val alwaysForcePlayersSpawn = plugin.config.getBoolean(ALWAYS_FORCE_PLAYERS_SPAWN, false)

            miscSettings = MiscSettings(alwaysForcePlayersSpawn)
        }

        return miscSettings
    }
}