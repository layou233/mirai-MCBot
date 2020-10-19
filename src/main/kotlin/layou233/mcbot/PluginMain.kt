package layou233.mcbot

import kotlinx.coroutines.plus
import layou233.mcbot.hypixel.apiRequester.*
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.Listener
import net.mamoe.mirai.event.events.*
import net.mamoe.mirai.event.subscribeAlways
import net.mamoe.mirai.message.GroupMessageEvent
import net.mamoe.mirai.message.TempMessageEvent
import net.mamoe.mirai.utils.info

object PluginMain : KotlinPlugin(
    JvmPluginDescription(
        id = "layou233.mcbot",
        version = "0.1.0"
    )
) {
    var initialSubscription: Listener<BotEvent>? = null
    override fun onEnable() {
        logger.info { "MCBot Plugin loaded" }
        initialSubscription = subscribeAlways {
            when (this) {
                is GroupMessageEvent -> {
//                    val whom: String = this.sender.toString().substring(7, (this.sender.toString().length - 1))
                    val saying = this.message.contentToString().replace("\\s".toRegex(), "");
//                    logger.info { saying }
                    if (saying.length > 1) {
                        if (saying.substring(0, 2) == "sb") {
                            if (saying.substring(2).indexOf("money") == 0) {
                                val playerId: String = saying.substring(7)
                                quoteReply("$playerId 共拥有 ${sb_money(playerId).toString()} coins")
                            }
                        }
                    }

                }

                is TempMessageEvent -> reply("为防止部分离奇问题出现，请先添加好友再使用功能！")
            }
        }
    }
}