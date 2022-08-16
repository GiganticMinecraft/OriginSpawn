package click.seichi.originspawn.presenter.commands

import click.seichi.originspawn.OriginSpawn.Companion.SPAWN_POINT_PERSISTENCE
import click.seichi.originspawn.presenter.format
import click.seichi.originspawn.presenter.shared.MessageSender
import click.seichi.originspawn.presenter.shared.ServerLogger
import click.seichi.originspawn.presenter.toBukkitLocation
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause

object Spawn : CommandExecutor("", "設定されたスポーン地点にテレポートします。", setOf("originspawn.spawn")) {
    override fun execute(sender: Player) {
        val messageSender = MessageSender(sender)
        if (!hasPermissions(sender)) {
            messageSender.severe("このコマンドを実行する権限がありません。")
            return
        }

        val spawnPoint = SPAWN_POINT_PERSISTENCE.get()
        val location = spawnPoint.toBukkitLocation() ?: run {
            val errorMsg = "スポーン地点の設定を読み込めませんでした。"
            messageSender.severe(errorMsg)
            ServerLogger.severe(errorMsg, spawnPoint.format())
            return
        }

        messageSender.info("サーバーのスポーン地点にテレポートしました。")
        sender.teleport(location, TeleportCause.PLUGIN)
    }
}