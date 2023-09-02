package click.seichi.originspawn.presenter.listeners

import click.seichi.originspawn.OriginSpawn.Companion.MISC_SETTINGS
import click.seichi.originspawn.presenter.shared.Teleporter
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

/**
 *
 */
object PlayerJoinListener: Listener {
    @EventHandler
    fun forcePlayerSpawnOnJoin(event: PlayerJoinEvent) {
        if (MISC_SETTINGS.get().alwaysForcePlayersSpawn) {
            Teleporter.run(event.player)
        }
    }
}