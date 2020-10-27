package layou233.mcbot.mojang.getSkin

import layou233.mcbot.mojang.apiRequester.resource
import layou233.mcbot.mojang.apiRequester.uuid

fun skin(id: String): String? {
    val res: String? = resource(uuid(id))
    return if (res == null) null
    else {
        Regex("""http.*[0-z]""").find(res)!!.value
        //Example for Regex: {"url":"http://textures.minecraft.net/texture/a1b2c3d4e5"}}}
    }
}
