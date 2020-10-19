package layou233.mcbot.mojang.apiRequester

import okhttp3.OkHttpClient
import okhttp3.Request
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
/*
fun uuid(id: String): String? {
    TODO("Have not start yet")
    return try
        val response =
            client.newCall(Request.Builder().url("https://api.mojang.com/users/profiles/minecraft/$id").build())
                .execute()
        val found: String = Regex("\\\":.*?(?=[,.}])").find(response.toString()).toString()
        response.close()
        found
    } catch (e: Exception) {
    return null
}
}*/