package click.seichi.originspawn.presenter.commands

import click.seichi.originspawn.OriginSpawn.Companion.SPAWN_POINT_PERSISTENCE
import click.seichi.originspawn.presenter.shared.MessageSender
import org.bukkit.entity.Player

object Reload : CommandExecutor("reload", "スポーン地点の設定を再読込します。", setOf("originspawn.admin")) {
    override fun executor(sender: Player) {
        val messageSender = MessageSender(sender)
        if (!hasPermissions(sender)) {
            messageSender.severe("このコマンドを実行する権限がありません。")
            return
        }

        SPAWN_POINT_PERSISTENCE.load()
        messageSender.info("スポーン地点の設定を再読込しました。")
    }
}