package layou233.mcbot.qbounds

import layou233.mcbot.httpClient.client
import okhttp3.Request

fun getQBounding(qqNumber: String): String {
    val back = client.newCall(Request.Builder().url("https://api.smokeclient.cn/qb-api/?qq=$qqNumber").build())
        .execute().body!!.string()
    return back.substring(back.indexOf("mobile") + 9, back.length - 2).replace("ul","|无数据或出错| ")
}