package id.siandalan.app.features.home.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.siandalan.app.features.home.data.api.ApiService
import id.siandalan.app.features.home.data.repository.HomeDataRepository
import id.siandalan.app.features.home.domain.repository.HomeRepository

@InstallIn(SingletonComponent::class)
@Module
class HomeDataModule {

    @Provides
    fun provideDataRepository(
        apiService: ApiService
    ): HomeRepository {
        return HomeDataRepository(apiService)
    }

}