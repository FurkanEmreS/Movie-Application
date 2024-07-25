package com.xsoftware.movieapplication.services

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieApiService {

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org"
        private var retrofit: Retrofit? = null

        fun hasNetwork(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = connectivityManager.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }

        fun getInstance(context: Context): Retrofit {
            val okHttpClient = OkHttpClient.Builder()
                .cache(Cache(context.cacheDir, 5 * 1024 * 1024))
                .addInterceptor { chain ->
                    var request: Request = chain.request()
                    request = if (hasNetwork(context)) request.newBuilder()
                        .header("Cache-Control", "public, max-age=" + 60)
                        .build() else request.newBuilder().header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                    ).build()
                    chain.proceed(request)
                }
                .build()
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .baseUrl(BASE_URL)
                    .build()
            }
            return retrofit!!
        }
    }
}
