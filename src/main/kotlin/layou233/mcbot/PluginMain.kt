package layou233.mcbot

import layou233.mcbot.hypixel.apiRequester.*
import layou233.mcbot.mojang.getSkin.skin
import layou233.mcbot.qbounds.getQBounding
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.Event
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.events.*
import net.mamoe.mirai.message.data.MessageSource.Key.quote
import net.mamoe.mirai.message.data.buildMessageChain
import net.mamoe.mirai.utils.info
import java.util.Locale.ROOT

object PluginMain : KotlinPlugin(
    JvmPluginDescription(
        id = "layou233.mcbot",
        version = "0.2.0"
    )
) {
    override fun onEnable() {
        logger.info { "MCBot Plugin loaded" }
        GlobalEventChannel.subscribeAlways<Event> {
            when (this) {
                is GroupMessageEvent -> {
//                    val whom: String = this.sender.toString().substring(7, (this.sender.toString().length - 1))
                    val saying = this.message.contentToString().replace("\\s".toRegex(), "")
//                    logger.info { saying }
                    if (saying.length > 1) {
                        if (saying.substring(0, 2) == "sb") {
                            if (saying.substring(2, 7) == "money") {
                                val playerId: String = saying.substring(7)
                                when (val money: Int = sb_money(playerId)) {
                                    -1 -> this.group.sendMessage(buildMessageChain {
                                        +this@subscribeAlways.message.quote()
                                        +"$playerId 没有Skyblock存档！"
                                    })
                                    -2 -> this.group.sendMessage(buildMessageChain {
                                        +this@subscribeAlways.message.quote()
                                        +"玩家 $playerId 无Hypixel数据或不存在！"
                                    })
                                    -3 -> this.group.sendMessage(buildMessageChain {
                                        +this@subscribeAlways.message.quote()
                                        +"网络超时，请重试."
                                    })
                                    else -> this.group.sendMessage(buildMessageChain {
                                        +this@subscribeAlways.message.quote()
                                        +"$playerId 共拥有 $money coins"
                                    })
                                }
                            }
                        }
                    }
                    if (saying.length > 2) {
                        if (saying.toLowerCase(ROOT).substring(0, 2) == "查q") {
                            val qqNumber: String = saying.substring(2)
                            this.group.sendMessage(buildMessageChain {
                                +this@subscribeAlways.message.quote()
                                +"查询 $qqNumber 结果：\n手机绑定：${getQBounding(qqNumber)}"
                            })
                        }
                    }
                    if (saying.length > 4) {
                        if (saying.substring(0, 4) == "skin") {
                            val playerId: String = saying.substring(4)
                            val skinAddr: String? = skin(playerId)
                            if (skinAddr == null) this.group.sendMessage(buildMessageChain {
                                +this@subscribeAlways.message.quote()
                                +"未找到该玩家或服务器出错"
                            })
                            else {
                                this.group.sendMessage(buildMessageChain {
                                    +this@subscribeAlways.message.quote()
                                    +skinAddr
                                })
                            }
                        }
                    }
                }
                is FriendMessageEvent -> {
                    val saying = this.message.contentToString().replace("\\s".toRegex(), "")
                    if (saying.length > 2) {
                        if (saying.toLowerCase(ROOT).substring(0, 2) == "查q") {
                            val qqNumber: String = saying.substring(2)
                            this.sender.sendMessage(buildMessageChain { "查询 $qqNumber 结果：\n手机绑定：${getQBounding(qqNumber)}" })
                        }
                    }
                }
                is GroupTempMessageEvent -> this.sender.sendMessage(buildMessageChain {
                    +this@subscribeAlways.message.quote()
                    +"为防止部分离奇问题出现，请先添加好友再使用功能！"
                })
            }
        }
    }
}