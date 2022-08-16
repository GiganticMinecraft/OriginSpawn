package click.seichi.originspawn.presenter.commands

import click.seichi.originspawn.presenter.shared.MessageSender
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player
import kotlin.collections.Set

object Command : TabExecutor {
    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>
    ): MutableList<String> = mutableListOf()

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        val messageSender = MessageSender(sender)

        if (sender !is Player) {
            messageSender.severe("このコマンドはゲーム内からのみ実行できます。")
            return true
        }

        val commandStr = args.getOrNull(0) ?: ""
        commandsList().find { it.commandStr.lowercase() == commandStr }?.execute(sender) ?: run {
            messageSender.severe("指定されたコマンドは存在しません。", "コマンドの使い方については、/originspawn help を参照してください。")
        }

        return true
    }
}

abstract class CommandExecutor(
    val commandStr: String,
    val help: String,
    private val permissions: Set<String> = emptySet(),
) {
    abstract fun execute(sender: Player)
    fun hasPermissions(sender: Player) = permissions.all { sender.hasPermission(it) }
}

fun commandsList() = setOf(
    Spawn,
    Show,
    Update,
    Reload,
    Help
)