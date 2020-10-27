package layou233.mcbot

import layou233.mcbot.hypixel.apiRequester.*
import layou233.mcbot.mojang.getSkin.skin
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
        version = "0.2.0"
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
                            if (saying.substring(2, 7) == "money") {
                                val playerId: String = saying.substring(7)
                                val money: Int = sb_money(playerId)
                                when (money) {
                                    -1 -> quoteReply("$playerId 没有Skyblock存档！")
                                    -2 -> quoteReply("玩家 $playerId 无Hypixel数据或不存在！")
                                    -3 -> quoteReply("网络超时，请重试.")
                                    else -> quoteReply("$playerId 共拥有 $money coins")
                                }
                            }
                        }
                    }
                    if (saying.length > 4) {
                        if (saying.substring(0, 4) == "skin") {
                            val playerId: String = saying.substring(4)
                            val skinAddr: String? = skin(playerId)
                            if (skinAddr == null) quoteReply("未找到该玩家或服务器出错")
                            else {
                                quoteReply(skinAddr)
                            }
                        }
                    }
                }
                is TempMessageEvent -> reply("为防止部分离奇问题出现，请先添加好友再使用功能！")
            }
        }
    }
}