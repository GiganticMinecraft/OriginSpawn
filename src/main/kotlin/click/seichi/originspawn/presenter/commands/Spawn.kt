package click.seichi.originspawn.presenter.commands

import click.seichi.originspawn.OriginSpawn.Companion.SPAWN_POINT_PERSISTENCE
import click.seichi.originspawn.presenter.format
import click.seichi.originspawn.presenter.shared.MessageSender
import click.seichi.originspawn.presenter.shared.ServerLogger
import click.seichi.originspawn.presenter.shared.Teleporter
import click.seichi.originspawn.presenter.toBukkitLocation
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause

object Spawn : CommandExecutor("", "設定されたスポーン地点にテレポートします。", setOf("originspawn.spawn")) {
    override fun execute(sender: Player) = Teleporter.run(sender)
}