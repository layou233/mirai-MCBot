package layou233.mcbot.hypixel.apiRequester

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.lang.Exception
import java.util.concurrent.TimeUnit

val client = OkHttpClient.Builder()
    .connectTimeout(10000, TimeUnit.MILLISECONDS)
    .readTimeout(3000, TimeUnit.MILLISECONDS)
    .addInterceptor {
        return@addInterceptor it.proceed(
            it.request().newBuilder()
                .addHeader(
                    "User-Agent",
                    ""
                )
                .build()
        )
    }
    .build()

fun sb_money(id: String): Int {
    val response: Response =
        client.newCall(Request.Builder().url("http://sky.shiiyu.moe/api/v2/coins/$id").build()).execute()
    var money: Int = 0
    val back: String = response.body!!.string()
    val finding: List<String> = listOf("purse", "bank")
    val indexes: List<Int> = listOf(7, 6)
    for (ind in finding.indices) {
        val found = Regex("""${finding.elementAt(ind)}\":.*?(?=[,.}])""").findAll(back)
        for (i in found) money += i.value.substring(indexes.elementAt(ind)).toInt()
    }
    response.close()
    return money
}