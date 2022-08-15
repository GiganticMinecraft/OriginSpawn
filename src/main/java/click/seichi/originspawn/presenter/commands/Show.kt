package click.seichi.originspawn.presenter.commands

import click.seichi.originspawn.OriginSpawn.Companion.SPAWN_POINT_PERSISTENCE
import click.seichi.originspawn.presenter.format
import click.seichi.originspawn.presenter.shared.MessageSender
import org.bukkit.entity.Player

object Show : CommandExecutor("show", "設定されているスポーン地点を表示します。", setOf("originspawn.spawn")) {
    override fun executor(sender: Player) {
        val messageSender = MessageSender(sender)
        if (!hasPermissions(sender)) {
            messageSender.severe("このコマンドを実行する権限がありません。")
            return
        }

        val spawnPoint = SPAWN_POINT_PERSISTENCE.get()
        messageSender.info("以下の地点にテレポート地点が設定されています。", spawnPoint.format())
    }
}