package id.siandalan.app.features.request.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.siandalan.app.features.request.domain.RequestInteractor
import id.siandalan.app.features.request.domain.RequestRepository
import id.siandalan.app.features.request.domain.RequestUseCase
import io.reactivex.rxjava3.disposables.CompositeDisposable

@InstallIn(SingletonComponent::class)
@Module
class HomeDomainModule {

    @Provides
    fun provideInteractor(
        repository: RequestRepository,
        disposable: CompositeDisposable,
    ): RequestUseCase {
        return RequestInteractor(repository, disposable)
    }

}