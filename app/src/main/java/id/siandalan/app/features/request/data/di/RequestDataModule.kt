package id.siandalan.app.features.request.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.siandalan.app.features.home.data.api.HomeApiService
import id.siandalan.app.features.home.data.repository.HomeDataRepository
import id.siandalan.app.features.home.domain.repository.HomeRepository
import id.siandalan.app.features.request.data.api.RequestApiService
import id.siandalan.app.features.request.data.repository.RequestDataRepository
import id.siandalan.app.features.request.domain.repository.RequestRepository
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RequestDataModule {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): RequestApiService {
        return retrofit.create(RequestApiService::class.java)
    }


    @Provides
    fun provideDataRepository(
        apiService: RequestApiService
    ): RequestRepository {
        return RequestDataRepository(apiService)
    }

}