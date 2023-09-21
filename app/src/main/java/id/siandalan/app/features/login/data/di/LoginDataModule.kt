package id.siandalan.app.features.login.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.siandalan.app.core.sessions.Sessions
import id.siandalan.app.features.login.data.LoginApiService
import id.siandalan.app.features.login.data.LoginDataRepository
import id.siandalan.app.features.login.domain.LoginRepository
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class LoginDataModule {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): LoginApiService {
        return retrofit.create(LoginApiService::class.java)
    }


    @Provides
    fun provideDataRepository(
        apiService: LoginApiService,
        sessions: Sessions
    ): LoginRepository {
        return LoginDataRepository(apiService, sessions)
    }

}