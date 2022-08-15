package click.seichi.originspawn

import click.seichi.originspawn.domain.SpawnPointPersistence
import click.seichi.originspawn.infra.SpawnPointPersistenceImpl
import click.seichi.originspawn.presenter.commands.Command
import click.seichi.originspawn.presenter.shared.ServerLogger
import org.bukkit.plugin.java.JavaPlugin

class OriginSpawn : JavaPlugin() {
    companion object {
        lateinit var SPAWN_POINT_PERSISTENCE: SpawnPointPersistence
            private set
    }

    override fun onEnable() {
        SPAWN_POINT_PERSISTENCE = SpawnPointPersistenceImpl(this)
        SPAWN_POINT_PERSISTENCE.load()

        getCommand("originspawn").executor = Command

        ServerLogger.info("OriginSpawn has been enabled.")
    }
}