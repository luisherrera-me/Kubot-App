package com.kuby.kubot.di

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.kuby.kubot.data.remote.KtorApi
import com.kuby.kubot.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.JavaNetCookieJar
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.net.CookieManager
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideCookieManager(): CookieManager {
        return CookieManager()
    }

    @Provides
    @Singleton
    fun provideHttpClient(cookieManager: CookieManager): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .cookieJar(JavaNetCookieJar(cookieManager))
            .addInterceptor { chain ->
                val request = chain.request()
                val response = chain.proceed(request)

                // Aquí puedes imprimir las cookies después de recibir la respuesta
                val cookies = cookieManager.cookieStore.cookies
                for (cookie in cookies) {
                    Log.e("CookieInfo", "Name: ${cookie.name}, Value: ${cookie.value}")
                }

                response
            }
            .build()
    }

    /*
    @Provides
    @Singleton
    fun provideHttpClientViewCookie(cookieManager: CookieManager): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .cookieJar(JavaNetCookieJar(cookieManager))
            .addInterceptor { chain ->
                val request = chain.request()
                val response = chain.proceed(request)

                // Aquí puedes imprimir las cookies después de recibir la respuesta
                val cookies = cookieManager.cookieStore.cookies
                for (cookie in cookies) {
                    Log.e("CookieInfo", "Name: ${cookie.name}, Value: ${cookie.value}")
                }

                response
            }
            .build()
    }
     */

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideKtorApi(retrofit: Retrofit): KtorApi {
        return retrofit.create(KtorApi::class.java)
    }

}