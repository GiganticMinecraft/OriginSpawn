package click.seichi.originspawn.presenter.commands

import click.seichi.originspawn.presenter.shared.MessageSender
import org.bukkit.entity.Player

object Help : CommandExecutor("help", "OriginSpawnのコマンドヘルプを表示します。") {
    override fun executor(sender: Player) {
        val messageSender = MessageSender(sender)

        messageSender.info(
            "===== How to use /originspawn =====",
            *commandsList().flatMap { setOf("/originspawn ${it.commandStr}", "    ${it.help}") }.toTypedArray()
        )
    }
}