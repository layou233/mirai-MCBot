package layou233.mcbot.mojang.apiRequester

import layou233.mcbot.httpClient.client
import okhttp3.Request
import java.net.SocketTimeoutException
import java.util.Base64.getDecoder

fun uuid(id: String): String? {
    try {
        val response =
            client.newCall(Request.Builder().url("https://api.mojang.com/users/profiles/minecraft/$id").build())
                .execute()
        val back: String = response.body!!.string()
        response.close()
        return back.substring(17 + id.length, back.length - 2)
    } catch (e: SocketTimeoutException) {
        return null
    }
}

fun resource(uuid: String?): String? {
    if (uuid==null) return null
    else{
        try {
            val response =
                client.newCall(Request.Builder().url("https://sessionserver.mojang.com/session/minecraft/profile/$uuid")
                    .build())
                    .execute()
            val back: String = response.body!!.string().replace("\\s".toRegex(), "")
            response.close()
            val res: String = Regex("""value\":\".*=+""").find(back)!!.value.substring(8)
            return String(getDecoder().decode(res)).replace("\\s".toRegex(), "")
        } catch (e: SocketTimeoutException) {
            return null
        }}

}