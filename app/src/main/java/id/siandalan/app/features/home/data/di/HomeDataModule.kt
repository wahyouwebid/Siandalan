package id.siandalan.app.features.home.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.siandalan.app.core.sessions.Sessions
import id.siandalan.app.features.home.data.HomeApiService
import id.siandalan.app.features.home.data.HomeDataRepository
import id.siandalan.app.features.home.domain.HomeRepository
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class HomeDataModule {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): HomeApiService {
        return retrofit.create(HomeApiService::class.java)
    }


    @Provides
    fun provideDataRepository(
        apiService: HomeApiService,
        sessions: Sessions
    ): HomeRepository {
        return HomeDataRepository(apiService, sessions)
    }

}