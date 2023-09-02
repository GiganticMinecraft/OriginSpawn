package click.seichi.originspawn

import click.seichi.originspawn.domain.MiscSettingsPersistence
import click.seichi.originspawn.domain.SpawnPointPersistence
import click.seichi.originspawn.infra.MiscSettingsPersistenceImpl
import click.seichi.originspawn.infra.SpawnPointPersistenceImpl
import click.seichi.originspawn.presenter.commands.Command
import click.seichi.originspawn.presenter.listeners.PlayerJoinListener
import click.seichi.originspawn.presenter.shared.ServerLogger
import org.bukkit.plugin.java.JavaPlugin

class OriginSpawn : JavaPlugin() {
    companion object {
        lateinit var SPAWN_POINT_PERSISTENCE: SpawnPointPersistence
            private set
        lateinit var MISC_SETTINGS: MiscSettingsPersistence
            private set
    }

    override fun onEnable() {
        SPAWN_POINT_PERSISTENCE = SpawnPointPersistenceImpl(this)
        SPAWN_POINT_PERSISTENCE.get()
        MISC_SETTINGS = MiscSettingsPersistenceImpl(this)
        MISC_SETTINGS.get()

        getCommand("originspawn").executor = Command
        server.pluginManager.registerEvents(PlayerJoinListener, this)

        ServerLogger.info("OriginSpawn has been enabled.")
    }
}