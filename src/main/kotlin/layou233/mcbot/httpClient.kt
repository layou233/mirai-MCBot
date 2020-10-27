package layou233.mcbot.httpClient

import okhttp3.OkHttpClient
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