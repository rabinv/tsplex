package `in`.rab.tsplex

import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File

abstract class LexikonClient() {
    companion object {
        @Volatile private var INSTANCE: OkHttpClient? = null

        fun getInstance(context: Context): OkHttpClient =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildClient(context).also { INSTANCE = it }
                }

        private fun buildClient(context: Context) =
                OkHttpClient.Builder()
                        .addNetworkInterceptor { chain ->
                            val request = chain.request()

                            if (request.url().toString().endsWith("mp4")) {
                                chain.proceed(request)
                            } else {
                                // The server sets Cache-Control: no-cache on pages, so we need to
                                // override it to get some caching.  But we can't use a large age
                                // since the video URLs can change.
                                chain.proceed(request)
                                        .newBuilder()
                                        .header("Cache-Control", "max-age=600")
                                        .build()
                            }
                        }
                        .cache(Cache(File(context.cacheDir, "okhttp"), 100 * 1024 * 1024))
                        .build()
    }
}