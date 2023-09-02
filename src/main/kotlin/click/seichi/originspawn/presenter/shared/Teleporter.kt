package click.seichi.originspawn.presenter.shared

import click.seichi.originspawn.OriginSpawn
import click.seichi.originspawn.presenter.commands.Spawn
import click.seichi.originspawn.presenter.format
import click.seichi.originspawn.presenter.toBukkitLocation
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerTeleportEvent

object Teleporter {
    fun run(player: Player) {
        val messageSender = MessageSender(player)
        if (!Spawn.hasPermissions(player)) {
            messageSender.severe("このコマンドを実行する権限がありません。")
            return
        }

        val spawnPoint = OriginSpawn.SPAWN_POINT_PERSISTENCE.get()
        val location = spawnPoint.toBukkitLocation() ?: run {
            val errorMsg = "スポーン地点の設定を読み込めませんでした。"
            messageSender.severe(errorMsg)
            ServerLogger.severe(errorMsg, spawnPoint.format())
            return
        }

        messageSender.info("サーバーのスポーン地点にテレポートしました。")
        player.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN)
    }
}