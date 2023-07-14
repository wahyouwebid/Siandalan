package id.siandalan.app.features.login.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.siandalan.app.features.login.data.api.LoginApiService
import id.siandalan.app.features.login.data.repository.LoginDataRepository
import id.siandalan.app.features.login.domain.repository.LoginRepository
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
        apiService: LoginApiService
    ): LoginRepository {
        return LoginDataRepository(apiService)
    }

}