package click.seichi.originspawn.presenter.shared

import click.seichi.originspawn.presenter.shared.LoggerLevel.*
import click.seichi.originspawn.presenter.shared.ServerLogger.log
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender

/**
 * [java.util.logging.Level]のうち一般的に有効であろうと思われるロギングレベルを表現する
 */
enum class LoggerLevel {
    INFO, WARN, SEVERE
}

interface Logger {
    /**
     * [LoggerLevel]で[messages]を出力する
     * @param level ロギングレベル。デフォルトは[LoggerLevel.INFO]。
     * param messages 出力したいメッセージ。
     */
    fun log(level: LoggerLevel = INFO, vararg messages: String) = when (level) {
        INFO -> info(*messages)
        WARN -> warn(*messages)
        SEVERE -> severe(*messages)
    }

    /**
     * [log]を[LoggerLevel.INFO]で実行するシンタックスシュガー。
     */
    fun info(vararg messages: String)

    /**
     * [log]を[LoggerLevel.WARN]で実行するシンタックスシュガー。
     */
    fun warn(vararg messages: String)

    /**
     * [log]を[LoggerLevel.SEVERE]で実行するシンタックスシュガー。
     */
    fun severe(vararg messages: String)
}

/**
 * [org.bukkit.command.CommandSender.sendMessage]をラップしている
 */
class MessageSender(private val sender: CommandSender) : Logger {
    private fun sendColoredMessage(color: ChatColor, vararg messages: String) =
        messages.map { "${color}$it" }.forEach { sender.sendMessage(it) }

    override fun info(vararg messages: String) = sendColoredMessage(ChatColor.AQUA, *messages)

    override fun warn(vararg messages: String) = sendColoredMessage(ChatColor.YELLOW, *messages)

    override fun severe(vararg messages: String) = sendColoredMessage(ChatColor.RED, *messages)
}

/**
 * [org.bukkit.Server.getLogger]をラップしている
 */
object ServerLogger : Logger {
    private val logger = Bukkit.getServer().logger

    private const val PREFIX = "[OriginSpawn]"
    private fun addPrefix(messages: Array<out String>) = messages.map { "$PREFIX $it" }

    override fun info(vararg messages: String) = addPrefix(messages).forEach { logger.info(it) }

    override fun warn(vararg messages: String) = addPrefix(messages).forEach { logger.warning(it) }

    override fun severe(vararg messages: String) = addPrefix(messages).forEach { logger.severe(it) }
}