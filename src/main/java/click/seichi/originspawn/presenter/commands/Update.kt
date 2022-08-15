package click.seichi.originspawn.presenter.commands

import click.seichi.originspawn.OriginSpawn.Companion.SPAWN_POINT_PERSISTENCE
import click.seichi.originspawn.presenter.format
import click.seichi.originspawn.presenter.shared.MessageSender
import click.seichi.originspawn.presenter.shared.ServerLogger
import click.seichi.originspawn.presenter.toSpawnPoint
import org.bukkit.entity.Player

object Update : CommandExecutor("update", "スポーン地点の設定を現在の座標に更新します。", setOf("originspawn.admin")) {
    override fun executor(sender: Player) {
        val messageSender = MessageSender(sender)
        if (!hasPermissions(sender)) {
            messageSender.severe("このコマンドを実行する権限がありません。")
            return
        }

        val spawnPoint = sender.location.toSpawnPoint()
        SPAWN_POINT_PERSISTENCE.save(spawnPoint)

        val updatedMessage = "スポーン地点の設定を更新しました。"
        messageSender.info(updatedMessage)
        ServerLogger.info(updatedMessage, SPAWN_POINT_PERSISTENCE.get().format())

        Reload.executor(sender)
    }
}