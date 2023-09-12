package id.siandalan.app.di

import android.content.Context
import android.content.Intent
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.siandalan.app.BuildConfig
import id.siandalan.app.core.cookies.WebViewCookieJar
import id.siandalan.app.core.sessions.Sessions
import id.siandalan.app.features.login.presentation.LoginActivity
import io.reactivex.rxjava3.disposables.CompositeDisposable
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Provides
    @Singleton
    fun providesHttpClient(
        interceptor: HttpLoggingInterceptor,
        chuckerInterceptor: ChuckerInterceptor,
        apiKey: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            retryOnConnectionFailure(true)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
            addInterceptor(interceptor)
            addInterceptor(chuckerInterceptor)
            addInterceptor(apiKey)
            cookieJar(WebViewCookieJar())
        }.build()
    }

    @Provides
    @Singleton
    fun providesHttpAdapter(clients: OkHttpClient): Retrofit {
        return Retrofit.Builder().apply {
            client(clients)
            baseUrl(BuildConfig.baseUrl)
            addConverterFactory(GsonConverterFactory.create())
            addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        }.build()
    }

    @Provides
    @Singleton
    fun providesChucker(@ApplicationContext context: Context): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context)
            .collector(ChuckerCollector(context))
            .maxContentLength(250000L)
            .redactHeaders(emptySet())
            .alwaysReadResponseBody(false)
            .build()
    }

    @Provides
    @Singleton
    fun providesApiKey(@ApplicationContext context: Context): Interceptor = Interceptor { chain ->
        var request: Request = chain.request()
        val session = Sessions(context)
        val url: HttpUrl = request.url.newBuilder()
            .build()
        request = request.newBuilder().url(url).build()

        val response = chain.proceed(request)
        if (response.code == 401) {
            val intent = Intent(context, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
            session.putBoolean(Sessions.isLogin, false)
        }

        return@Interceptor response
    }

    @Provides
    @Singleton
    fun provideDisposable(): CompositeDisposable = CompositeDisposable()
}