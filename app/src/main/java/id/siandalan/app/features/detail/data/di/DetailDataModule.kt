package id.siandalan.app.features.detail.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.siandalan.app.core.sessions.Sessions
import id.siandalan.app.features.detail.data.DetailApiService
import id.siandalan.app.features.detail.data.DetailDataRepository
import id.siandalan.app.features.detail.domain.DetailRepository
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DetailDataModule {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): DetailApiService {
        return retrofit.create(DetailApiService::class.java)
    }


    @Provides
    fun provideDataRepository(
        apiService: DetailApiService,
        sessions: Sessions
    ): DetailRepository {
        return DetailDataRepository(apiService, sessions)
    }

}