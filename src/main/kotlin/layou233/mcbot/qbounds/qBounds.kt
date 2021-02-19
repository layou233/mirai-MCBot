package layou233.mcbot.qbounds

import layou233.mcbot.httpClient.client
import okhttp3.Request
import java.io.IOException

fun getQBounding(qqNumber: String): String {
    val back: String = client.newCall(Request.Builder().url("https://api.smokeclient.cn/qb-api/?qq=$qqNumber").build())
        .execute().body!!.string()
    val mobile: String = Regex("mobile\": \"([0-9]*\")|(null)").find(back)?.value ?: throw IOException("Bad Response")
    return mobile.substring(10, mobile.length - 1).replace("ul", "|无数据或出错|")
}