package id.siandalan.app.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.siandalan.app.core.sessions.Sessions
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SessionsModule {
    @Provides
    @Singleton
    fun provideSessions(@ApplicationContext context: Context): Sessions = Sessions(context)
}