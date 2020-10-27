package layou233.mcbot.hypixel.apiRequester

import layou233.mcbot.httpClient.client
import okhttp3.Request
import java.net.SocketTimeoutException


fun sb_money(id: String): Int {
    try {
        val response =
            client.newCall(Request.Builder().url("http://sky.shiiyu.moe/api/v2/coins/$id").build()).execute()
        var money: Int = 0
        val back: String = response.body!!.string()
        if (back == "{\"error\":\"Player has no SkyBlock profiles.\"}") return -1
        else if (back == "{\"error\":\"Failed resolving username.\"}") return -2
        else {
/*        val finding: List<String> = listOf("purse", "bank")
        val indexes: List<Int> = listOf(7, 6)*/
            for (ind in listOf("purse", "bank").indices) {
                val found = Regex("""${listOf("purse", "bank").elementAt(ind)}\":.*?(?=[,.}])""").findAll(back)
                for (i in found) money += i.value.substring(listOf(7, 6).elementAt(ind)).toInt()
            }
            response.close()
            return money
        }
    } catch (e: SocketTimeoutException) {
        return -3
    }
}